package hvl.dat250.controller;

import hvl.dat250.model.Vote;
import hvl.dat250.service.VoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/votes")
public class VoteController {
    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping
    public List<Vote> getAllVotes() {
        return voteService.getAllVotes();
    }

    @PostMapping
    public Vote createVote(@RequestBody Vote vote) {
        return voteService.createVote(vote);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vote> getVote(@PathVariable String id) {
        Vote vote = voteService.getVote(id);
        return vote != null ? ResponseEntity.ok(vote) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public Vote updateVote(@PathVariable String id, @RequestBody Vote vote) {
        return voteService.updateVote(id, vote);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVote(@PathVariable String id) {
        voteService.deleteVote(id);
        return ResponseEntity.noContent().build();
    }
}
