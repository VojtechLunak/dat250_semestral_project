package hvl.dat250.controller;

import hvl.dat250.model.VoteOption;
import hvl.dat250.service.VoteOptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/vote-options")
public class VoteOptionController {
    private final VoteOptionService voteOptionService;

    public VoteOptionController(VoteOptionService voteOptionService) {
        this.voteOptionService = voteOptionService;
    }

    @GetMapping
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public Iterable<VoteOption> getAllVoteOptions() {
        return voteOptionService.getAllVoteOptions();
    }

    @PostMapping
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public VoteOption createVoteOption(@RequestBody VoteOption voteOption) {
        return voteOptionService.createVoteOption(voteOption);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public ResponseEntity<Optional<VoteOption>> getVoteOption(@PathVariable String id) {
        Optional<VoteOption> voteOption = voteOptionService.getVoteOptionById(id);
        return voteOption.isPresent() ? ResponseEntity.ok(voteOption) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public VoteOption updateVoteOption(@PathVariable String id, @RequestBody VoteOption voteOption) {
        return voteOptionService.updateVoteOption(id, voteOption);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public ResponseEntity<Void> deleteVoteOption(@PathVariable String id) {
        voteOptionService.deleteVoteOption(id);
        return ResponseEntity.noContent().build();
    }
}
