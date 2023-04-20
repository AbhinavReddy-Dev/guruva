package dev.abhinavreddy.guruva.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        user.setUserToken(generateNewToken(user.getUsername()));
        return userRepository.save(user);
    }

    public Optional<User> getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> updateUser(User user) {
        return userRepository.findById(user.getId()).map(u -> {
            u.setFullName(user.getFullName());
            u.setUsermode( user.getUsermode());
            u.setExperience(user.getExperience());
            u.setPhoto(user.getPhoto());
            u.setGender(user.getGender());
            u.setEmail(user.getEmail());
            u.setLanguagesSpoken(user.getLanguagesSpoken());
            u.setExternalLinks(user.getExternalLinks());
            u.setCountry(user.getCountry());
            u.setUpdatedAt(user.getUpdatedAt());
            return userRepository.save(u);
        });
    }

    public Optional<User> updateUserSkills(User user){
        return userRepository.findById(user.getId()).map(u -> {
            u.setSkills(user.getSkills());
            u.setUpdatedAt(user.getUpdatedAt());
            return userRepository.save(u);
        });
    }

    public Optional<User> updateMentorRating(User user){
        return userRepository.findById(user.getId()).map(u -> {
            u.setMentorRating(user.getMentorRating());
            u.setUpdatedAt(user.getUpdatedAt());
            return userRepository.save(u);
        });
    }

    public Optional<User> updateMenteeRating(User user){
        return userRepository.findById(user.getId()).map(u -> {
            u.setMenteeRating(user.getMenteeRating());
            u.setUpdatedAt(user.getUpdatedAt());
            return userRepository.save(u);
        });
    }

    public void updateUserToken(String username) {
        String token = generateNewToken(username);
        userRepository.findByUsername(username).map(u -> {
            u.setUserToken(token);
            return userRepository.save(u);
        });
    }

    public void updateUserPassword(String username, String password) {
        userRepository.findByUsername(username).map(u -> {
            u.setPassword(password);
            return userRepository.save(u);
        });
    }

    public String generateNewToken(String username) {
        StringBuilder token = new StringBuilder();
        for(int i = 0; i < 10; i++) {
            token.append((char) (Math.random() * 26 + 'a'));
        }
        return token.toString();
    }

}
