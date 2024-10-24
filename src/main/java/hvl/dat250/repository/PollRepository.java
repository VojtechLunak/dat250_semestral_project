package hvl.dat250.repository;

import hvl.dat250.model.Poll;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PollRepository extends Neo4jRepository<Poll, String> {
}
