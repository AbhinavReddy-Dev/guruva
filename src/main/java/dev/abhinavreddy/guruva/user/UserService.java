package dev.abhinavreddy.guruva.user;

import dev.abhinavreddy.guruva.exceptions.UserAlreadyExists;
import dev.abhinavreddy.guruva.exceptions.UserNotFound;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) throws Exception {
        try {
            if (getUser(user.getUsername()).isPresent()) {
                throw new UserAlreadyExists("User already exists: " + user.getUsername());
            }
            user.setUserToken(generateNewToken(user.getUsername()));
            String password = user.getPassword();
            if (verifyPasswordStrength(password)) {
                // TODO: new exception
                throw new Exception("Invalid password");
            }
            // hash password with base64 and save
            String hashedPassword = new String(java.util.Base64.getEncoder().encode(password.getBytes()));
            user.setPassword(hashedPassword);
            return userRepository.save(user);
        }
        catch (Exception e) {
            throw new Exception("Error creating user");
        }
    }

    public Optional<User> getUser(String username) throws UserNotFound {
        try{
            return userRepository.findByUsername(username);
        }
        catch (Exception e) {
            throw new UserNotFound("User not found: " + username);
        }
    }

    public User updateUser(User user) throws Exception {
        try {
            User userInDB = userRepository.findById(user.getId()).orElseThrow(() -> new UserNotFound("User not found: " + user.getUsername()));
            userInDB.setEmail(user.getEmail());
            userInDB.setFullName(user.getFullName());
            userInDB.setGender(user.getGender());
            userInDB.setPhoto(user.getPhoto());
            userInDB.setLanguagesSpoken(user.getLanguagesSpoken());
            userInDB.setCountry(user.getCountry());
            userInDB.setExperience(user.getExperience());
            userInDB.setSkills(user.getSkills());
            return userRepository.save(userInDB);
        }
        catch (Exception e) {
            throw new Exception("Error updating user");
        }
    }

//    update username throwing exception if username already exists
    public User updateUsername(String username, String newUsername) throws Exception {
        try{
        if (getUser(newUsername).isPresent()) {
            throw new UserAlreadyExists("User already exists: " + newUsername);
        }
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFound("User not found: " + username));
        user.setUsername(newUsername);
        return userRepository.save(user);
        }
        catch (Exception e) {
            throw new Exception("Error updating username");
        }
    }
    public void updateUserToken(String username) throws Exception {
        try{
        String token = generateNewToken(username);
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFound("User not found: " + username));
        user.setUserToken(token);
        userRepository.save(user);
        }
        catch (Exception e) {
            throw new Exception("Error updating user token");
        }
    }

    public Boolean updateUserPassword(String username, String password) throws Exception {
        try {

        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFound("User not found: " + username));
        // throws exception if password is shorter than 8 characters or longer than 20 characters or does not contain a number or does not contain a special character or does not contain an uppercase letter or does not contain a lowercase letter or is the same as the old password
        if (verifyPasswordStrength(password) || password.equals(user.getPassword())) {
        // TODO: new exception
            throw new Exception("Invalid password");
        }
        // hash password with base64 and save
        String hashedPassword = new String(java.util.Base64.getEncoder().encode(password.getBytes()));
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return true;
    }
        catch (Exception e) {
            throw new Exception("Error updating user password");
        }
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

    public Boolean deleteUser(String username) throws UserNotFound {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFound("User not found: " + username));
        user.setIsDeleted(true);
        userRepository.save(user);
        return true;
    }

    public String generateNewToken(String username) {
        StringBuilder token = new StringBuilder();
        for(int i = 0; i < username.length(); i++) {
            token.append(username.charAt(i));
            token.append((char) (Math.random() * 26 + 'a'));
        }
        return token.toString();
    }

    public Boolean verifyPasswordStrength(String password){

        return password.length() < 8 || password.length() > 20 || !password.matches(".*\\d.*") || !password.matches(".*[!@#$%^&*].*") || !password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*");

    }
}
