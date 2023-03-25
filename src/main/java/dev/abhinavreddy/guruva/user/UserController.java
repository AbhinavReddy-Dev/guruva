package dev.abhinavreddy.guruva.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    final
    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String test() {
        return "Hello World";
    }


    @GetMapping("/{username}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable String username) {
            Optional<User> user = userRepository.findByUsername(username);
            if(user.isPresent()) {
//                get variable type of user gender
//                System.out.println(user.get().getGender().getGender().getClass());
//                System.out.println(user.get().getGender().getClass());
                return ResponseEntity.ok(user);
            }
            else return ResponseEntity.notFound().build();
    }
//&authSource=admin&authMechanism=SCRAM-SHA-1
//    @PostMapping("/auth/signup")
//    public void signup(@RequestBody User user) {
//        userRepository.save(user);
//    }

}
