package hvl.dat250.service;

import hvl.dat250.model.Vote;
import hvl.dat250.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote createVote(Vote vote) {
        return voteRepository.save(vote);
    }

    public Optional<Vote> getVoteById(String id) {
        return voteRepository.findById(id);
    }

    public Iterable<Vote> getAllVotes() {
        return voteRepository.findAll();
    }

    public Vote updateVote(String id, Vote voteDetails) {
        Optional<Vote> existingVote = voteRepository.findById(id);
        if (existingVote.isPresent()) {
            Vote vote = existingVote.get();
            vote.setPublishedAt(voteDetails.getPublishedAt());
            vote.setVoteOption(voteDetails.getVoteOption());
            vote.setUser(voteDetails.getUser());
            return voteRepository.save(vote);
        } else {
            throw new RuntimeException("Vote not found with id: " + id);
        }
    }

    public void deleteVote(String id) {
        voteRepository.deleteById(id);
    }
}
