package hvl.dat250.repository;

import hvl.dat250.model.Poll;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface PollRepository extends Neo4jRepository<Poll, String> {
    @Query("MATCH (p:Poll)-[r]->(n) WHERE p.id = $id RETURN p, collect(r), collect(n)")
    Optional<Poll> findByIdWithRelationships(@Param("id") String id);

}
