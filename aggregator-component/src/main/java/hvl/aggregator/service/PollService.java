// src/main/java/com/example/service/PollService.java
package hvl.aggregator.service;

import hvl.aggregator.model.PollData;
import hvl.aggregator.repository.PollRepository;
import org.springframework.stereotype.Service;

@Service
public class PollService {

    private final PollRepository pollRepository;

    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public void processPollData(String data) {
        PollData poll = new PollData(data);
        pollRepository.save(poll);
    }
}