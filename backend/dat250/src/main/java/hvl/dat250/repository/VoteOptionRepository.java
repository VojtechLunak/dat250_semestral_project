package hvl.dat250.repository;

import hvl.dat250.model.VoteOption;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface VoteOptionRepository extends Neo4jRepository<VoteOption, String> {
}

