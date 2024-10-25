package hvl.dat250.service;

import hvl.dat250.model.Vote;
import hvl.dat250.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {
    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public List<Vote> getAllVotes() {
        return voteRepository.findAll();
    }

    public Vote createVote(Vote vote) {
        return voteRepository.save(vote);
    }

    public Vote getVote(String id) {
        return voteRepository.findById(id).orElse(null);
    }

    public Vote updateVote(String id, Vote vote) {
        vote.setId(id);
        return voteRepository.save(vote);
    }

    public void deleteVote(String id) {
        voteRepository.deleteById(id);
    }
}
