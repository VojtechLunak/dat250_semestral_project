package hvl.dat250.controller;

import hvl.dat250.model.Vote;
import hvl.dat250.service.VoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/votes")
public class VoteController {
    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public Iterable<Vote> getAllVotes() {
        return voteService.getAllVotes();
    }

    @PostMapping
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public Vote createVote(@RequestBody Vote vote) {
        return voteService.createVote(vote);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public ResponseEntity<Optional<Vote>> getVote(@PathVariable String id) {
        Optional<Vote> vote = voteService.getVoteById(id);
        return vote.isPresent() ? ResponseEntity.ok(vote) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public Vote updateVote(@PathVariable String id, @RequestBody Vote vote) {
        return voteService.updateVote(id, vote);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public ResponseEntity<Void> deleteVote(@PathVariable String id) {
        voteService.deleteVote(id);
        return ResponseEntity.noContent().build();
    }
}
