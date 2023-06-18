package dev.abhinavreddy.guruva.user;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) throws Exception {
        if (getUser(user.getUsername()).isPresent()) {
            throw new DuplicateKeyException("User already exists");
        }
        try {
            user.setUserToken(generateNewToken(user.getUsername()));
            return userRepository.save(user);
        }
        catch (Exception e) {
            throw new Exception("Error creating user");
        }
    }

    public Optional<User> getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> updateUser(User user) {
        return userRepository.findById(user.getId()).map(u -> {
            u.setFullName(user.getFullName());
//            u.setUsermode( user.getUsermode());
            u.setExperience(user.getExperience());
            u.setPhoto(user.getPhoto());
            u.setGender(user.getGender());
            u.setEmail(user.getEmail());
            u.setLanguagesSpoken(user.getLanguagesSpoken());
            u.setExternalLinks(user.getExternalLinks());
            u.setCountry(user.getCountry());
            return userRepository.save(u);
        });
    }

    public Optional<User> updateUserSkills(User user){
        return userRepository.findById(user.getId()).map(u -> {
            u.setSkills(user.getSkills());
            return userRepository.save(u);
        });
    }

    public Optional<User> updateMentorRating(User user){
        return userRepository.findById(user.getId()).map(u -> {
            u.setMentorRating(user.getMentorRating());
            return userRepository.save(u);
        });
    }

    public Optional<User> updateMenteeRating(User user){
        return userRepository.findById(user.getId()).map(u -> {
            u.setMenteeRating(user.getMenteeRating());
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
