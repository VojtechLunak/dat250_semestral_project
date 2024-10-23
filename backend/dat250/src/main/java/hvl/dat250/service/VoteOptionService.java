package hvl.dat250.service;

import hvl.dat250.model.VoteOption;
import hvl.dat250.repository.VoteOptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VoteOptionService {
    private final VoteOptionRepository voteOptionRepository;

    public VoteOptionService(VoteOptionRepository voteOptionRepository) {
        this.voteOptionRepository = voteOptionRepository;
    }

    public List<VoteOption> getAllVoteOptions() {
        return voteOptionRepository.findAll();
    }

    public VoteOption createVoteOption(VoteOption voteOption) {
        voteOption.setId(UUID.randomUUID().toString());
        return voteOptionRepository.save(voteOption);
    }

    public VoteOption getVoteOption(String id) {
        return voteOptionRepository.findById(id).orElse(null);
    }

    public VoteOption updateVoteOption(String id, VoteOption voteOption) {
        voteOption.setId(id);
        return voteOptionRepository.save(voteOption);
    }

    public void deleteVoteOption(String id) {
        voteOptionRepository.deleteById(id);
    }
}
