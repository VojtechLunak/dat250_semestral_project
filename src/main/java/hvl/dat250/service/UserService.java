package hvl.dat250.service;

import hvl.dat250.model.User;
import hvl.dat250.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findById(username);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(String username, User userDetails) {
        Optional<User> existingUser = userRepository.findById(username);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setEmail(userDetails.getEmail());
            user.setPollsCreated(userDetails.getPollsCreated());
            user.setVotes(userDetails.getVotes());
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with username: " + username);
        }
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }
}
