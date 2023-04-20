package dev.abhinavreddy.guruva.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String test() {
        return "Hello World";
    }

    @GetMapping("/{username}")
    public ResponseEntity<Boolean> findUser(@PathVariable String username) {
            Optional<User> user = userService.getUser(username);
            if(user.isPresent()) {
                return ResponseEntity.ok(true);
            }
            else return ResponseEntity.notFound().build();
    }

}
