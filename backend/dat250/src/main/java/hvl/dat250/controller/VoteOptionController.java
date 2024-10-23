package hvl.dat250.controller;

import hvl.dat250.model.VoteOption;
import hvl.dat250.service.VoteOptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/vote-options")
public class VoteOptionController {
    private final VoteOptionService voteOptionService;

    public VoteOptionController(VoteOptionService voteOptionService) {
        this.voteOptionService = voteOptionService;
    }

    @GetMapping
    public List<VoteOption> getAllVoteOptions() {
        return voteOptionService.getAllVoteOptions();
    }

    @PostMapping
    public VoteOption createVoteOption(@RequestBody VoteOption voteOption) {
        return voteOptionService.createVoteOption(voteOption);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoteOption> getVoteOption(@PathVariable String id) {
        VoteOption voteOption = voteOptionService.getVoteOption(id);
        return voteOption != null ? ResponseEntity.ok(voteOption) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public VoteOption updateVoteOption(@PathVariable String id, @RequestBody VoteOption voteOption) {
        return voteOptionService.updateVoteOption(id, voteOption);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoteOption(@PathVariable String id) {
        voteOptionService.deleteVoteOption(id);
        return ResponseEntity.noContent().build();
    }
}
