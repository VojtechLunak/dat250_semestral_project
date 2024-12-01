package hvl.aggregator.service;

import hvl.aggregator.model.PollData;
import hvl.aggregator.repository.PollRepository;
import org.springframework.stereotype.Service;

@Service
public class PollDataService {

    private final PollRepository pollRepository;

    public PollDataService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public void processPollData(String data) {
        PollData poll = new PollData(data);
        System.out.println("SERVICE - Poll data processed : " + poll);
        pollRepository.save(poll);
    }
}