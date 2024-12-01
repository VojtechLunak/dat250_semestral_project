package hvl.dat250.controller;

import hvl.dat250.model.User;
import hvl.dat250.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public ResponseEntity<Optional<User>> getUser(@PathVariable String username) {
        Optional<User> user = userService.getUserByUsername(username);
        return user.isPresent() ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{username}")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public User updateUser(@PathVariable String username, @RequestBody User user) {
        return userService.updateUser(username, user);
    }

    @DeleteMapping("/{username}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }
}
