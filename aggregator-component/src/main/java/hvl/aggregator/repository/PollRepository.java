package hvl.aggregator.repository;

import hvl.aggregator.model.PollData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PollRepository extends MongoRepository<PollData, String> {
}