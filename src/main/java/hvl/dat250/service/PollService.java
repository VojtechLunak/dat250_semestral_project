package hvl.dat250.service;

import hvl.dat250.model.Poll;
import hvl.dat250.model.VoteOption;
import hvl.dat250.repository.PollRepository;
import hvl.dat250.repository.VoteOptionRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class PollService {

    private final PollRepository pollRepository;

    private final VoteOptionRepository voteOptionRepository;

    public PollService(PollRepository pollRepository, VoteOptionRepository voteOptionRepository) {
        this.pollRepository = pollRepository;
        this.voteOptionRepository = voteOptionRepository;
    }

    public Poll createPoll(Poll poll) {
        if (poll.getId() == null) {
            poll.setId(UUID.randomUUID().toString());
        }
        if (poll.getPublishedAt() == null) {
            poll.setPublishedAt(Instant.now());
        }

        Set<VoteOption> savedOptions = new HashSet<>();
        if (poll.getVoteOptions() != null) {
            for (VoteOption option : poll.getVoteOptions()) {
                if (option.getId() == null) {
                    option.setId(UUID.randomUUID().toString());
                }
                List<String> ids = option.getPollIds();
                if (ids == null) {
                    ids = new ArrayList<>();
                    option.setPollIds(ids);
                }
                ids.add(poll.getId());
                savedOptions.add(voteOptionRepository.save(option));
            }
        }
        poll.setVoteOptions(savedOptions);
        poll.getVoteOptions().removeIf(option -> option.getId() == null);

        return pollRepository.save(poll);
    }

    public Optional<Poll> getPollById(String id) {
        return pollRepository.findById(id);
    }

    public Iterable<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    public Poll updatePoll(String id, Poll pollDetails) {
        Optional<Poll> existingPoll = pollRepository.findById(id);
        if (existingPoll.isPresent()) {
            Poll poll = existingPoll.get();
            poll.setQuestion(pollDetails.getQuestion());
            poll.setPublishedAt(pollDetails.getPublishedAt());
            poll.setValidUntil(pollDetails.getValidUntil());
            poll.setVoteOptions(pollDetails.getVoteOptions());
            poll.setVotes(pollDetails.getVotes());
            return pollRepository.save(poll);
        } else {
            throw new RuntimeException("Poll not found with id: " + id);
        }
    }

    public void deletePoll(String id) {
        pollRepository.deleteById(id);
    }
}
