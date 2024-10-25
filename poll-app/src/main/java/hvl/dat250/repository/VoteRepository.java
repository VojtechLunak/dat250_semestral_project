package hvl.dat250.repository;

import hvl.dat250.model.Vote;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface VoteRepository extends Neo4jRepository<Vote, String> {
}
