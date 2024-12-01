package hvl.dat250.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
public class User {
    @Id
    private String username;
    private String email;
    private String password;
    private String role;

    @Relationship(type = "CREATED")
    private Set<Poll> pollsCreated = new HashSet<>();

    @Relationship(type = "VOTED")
    private Set<Vote> votes = new HashSet<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Poll> getPollsCreated() {
        return pollsCreated;
    }

    public void setPollsCreated(Set<Poll> pollsCreated) {
        this.pollsCreated = pollsCreated;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }
}
