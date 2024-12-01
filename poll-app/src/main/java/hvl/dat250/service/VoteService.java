package hvl.dat250.service;

import hvl.dat250.model.Poll;
import hvl.dat250.model.User;
import hvl.dat250.model.Vote;
import hvl.dat250.repository.PollRepository;
import hvl.dat250.repository.UserRepository;
import hvl.dat250.repository.VoteOptionRepository;
import hvl.dat250.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final PollRepository pollRepository;
    private final VoteOptionRepository voteOptionRepository;

    public VoteService(VoteRepository voteRepository, UserRepository userRepository, PollRepository pollRepository, VoteOptionRepository voteOptionRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.pollRepository = pollRepository;
        this.voteOptionRepository = voteOptionRepository;
    }

    public Vote createVote(Vote vote) {
        if (vote.getUser() != null) {
            User user = userRepository.findById(vote.getUser().getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found with username: " + vote.getUser().getUsername()));
            vote.setUser(user);
        }
        if (vote.getId() == null) {
            vote.setId(UUID.randomUUID().toString());
        }

if(vote.getVoteOption() != null) {
    vote.setVoteOption(vote.getVoteOption());
if(vote.getVoteOption().getPollIds() != null) {
    String pollId = vote.getVoteOption().getPollIds().getFirst();
    Optional<Poll> optionalPoll = pollRepository.findById(pollId);
    if (optionalPoll.isPresent()) {
        Poll poll = optionalPoll.get();
        if (poll.getVotes() == null) {
            poll.setVotes(new HashSet<>());
        }
        poll.getVotes().add(vote);
        pollRepository.save(poll);
    } else {
        throw new RuntimeException("Poll not found with ID: " + pollId);
    }
}
}
        return voteRepository.save(vote);
    }


    public Optional<Vote> getVoteById(String id) {
        return voteRepository.findById(id);
    }

    public Iterable<Vote> getAllVotes() {
        return voteRepository.findAll();
    }

    public Vote updateVote(String id, Vote voteDetails) {
        Optional<Vote> existingVoteOpt = voteRepository.findById(id);
        if (existingVoteOpt.isPresent()) {
            Vote existingVote = existingVoteOpt.get();

            existingVote.setPublishedAt(voteDetails.getPublishedAt());
            existingVote.setVoteOption(voteDetails.getVoteOption());

            if (voteDetails.getUser() != null) {
                User user = userRepository.findById(voteDetails.getUser().getUsername())
                        .orElseThrow(() -> new RuntimeException("User not found with username: " + voteDetails.getUser().getUsername()));
                existingVote.setUser(user);
            }

            return voteRepository.save(existingVote);
        } else {
            throw new RuntimeException("Vote not found with id: " + id);
        }
    }

    public void deleteVote(String id) {
        voteRepository.deleteById(id);
    }
}
