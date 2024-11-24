package hvl.dat250.service;

import hvl.dat250.messaging.AggregatorMessagingService;
import hvl.dat250.model.Poll;
import hvl.dat250.repository.PollRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PollService {
    private final PollRepository pollRepository;
    private final AggregatorMessagingService aggregatorMessagingService;

    public PollService(PollRepository pollRepository, AggregatorMessagingService aggregatorMessagingService) {
        this.pollRepository = pollRepository;
        this.aggregatorMessagingService = aggregatorMessagingService;
    }

    public Poll createPoll(Poll poll) {
        poll.setPublishedAt(Instant.now());
        poll.setId(UUID.randomUUID().toString());
        aggregatorMessagingService.sendMessage(poll.getQuestion());
        return pollRepository.save(poll);
    }

    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    public Optional<Poll> getPollById(String id) {
        return pollRepository.findById(id);
    }

    public Poll updatePoll(String id, Poll updatedPoll) {
        Poll existingPoll = pollRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Poll not found with id: " + id));

        existingPoll.setQuestion(updatedPoll.getQuestion());
        existingPoll.setValidUntil(updatedPoll.getValidUntil());
        existingPoll.setVoteOptions(updatedPoll.getVoteOptions());

        return pollRepository.save(existingPoll);
    }

    public void deletePoll(String id) {
        Poll existingPoll = pollRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Poll not found with id: " + id));

        pollRepository.delete(existingPoll);
    }
}
