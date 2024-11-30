package hvl.dat250.security;

import hvl.dat250.model.LoginRequest;
import hvl.dat250.model.User;
import hvl.dat250.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody LoginRequest loginRequest) {

        if (userRepository.findByUsername(loginRequest.getUsername()) != null) {
            return ResponseEntity.status(400).body("Username already exists");
        }

        User newUser = new User();
        newUser.setUsername(loginRequest.getUsername());
        newUser.setPassword(loginRequest.getPassword());
        newUser.setRole("user");
        userRepository.save(newUser);

        String token = jwtUtil.generateToken(newUser.getUsername(), newUser.getRole());

        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());

        if (user == null || !user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        return ResponseEntity.ok(token);
    }

}
