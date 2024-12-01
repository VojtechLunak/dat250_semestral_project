package hvl.dat250.controller;

import hvl.dat250.model.Poll;
import hvl.dat250.service.PollService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:80"}, allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/polls")
public class PollController {
    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping
    public ResponseEntity<Poll> createPoll(@RequestBody Poll poll) {
        Poll createdPoll = pollService.createPoll(poll);
        return new ResponseEntity<>(createdPoll, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public Iterable<Poll> getAllPolls() {
        return pollService.getAllPolls();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public ResponseEntity<Poll> getPoll(@PathVariable String id) {
        Optional<Poll> poll = pollService.getPollById(id);
        return poll.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public ResponseEntity<Poll> updatePoll(@PathVariable String id, @RequestBody Poll updatedPoll) {
        Poll poll = pollService.updatePoll(id, updatedPoll);
        return ResponseEntity.ok(poll);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public ResponseEntity<Void> deletePoll(@PathVariable String id) {
        pollService.deletePoll(id);
        return ResponseEntity.noContent().build();
    }
}
