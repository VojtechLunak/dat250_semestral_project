package hvl.dat250.service;

import hvl.dat250.model.VoteOption;
import hvl.dat250.repository.VoteOptionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoteOptionService {

    private final VoteOptionRepository voteOptionRepository;

    public VoteOptionService(VoteOptionRepository voteOptionRepository) {
        this.voteOptionRepository = voteOptionRepository;
    }

    public VoteOption createVoteOption(VoteOption voteOption) {
        return voteOptionRepository.save(voteOption);
    }

    public Optional<VoteOption> getVoteOptionById(String id) {
        return voteOptionRepository.findById(id);
    }

    public Iterable<VoteOption> getAllVoteOptions() {
        return voteOptionRepository.findAll();
    }

    public VoteOption updateVoteOption(String id, VoteOption voteOptionDetails) {
        Optional<VoteOption> existingVoteOption = voteOptionRepository.findById(id);
        if (existingVoteOption.isPresent()) {
            VoteOption voteOption = existingVoteOption.get();
            voteOption.setCaption(voteOptionDetails.getCaption());
            voteOption.setPresentationOrder(voteOptionDetails.getPresentationOrder());
            voteOption.setPoll(voteOptionDetails.getPoll());
            return voteOptionRepository.save(voteOption);
        } else {
            throw new RuntimeException("VoteOption not found with id: " + id);
        }
    }

    public void deleteVoteOption(String id) {
        voteOptionRepository.deleteById(id);
    }
}
