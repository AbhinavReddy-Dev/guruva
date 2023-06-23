package dev.abhinavreddy.guruva.user;

import dev.abhinavreddy.guruva.reqres.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

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

    @PostMapping("/create")
    public ResponseEntity<ResponseBody> createUser(@RequestBody User user) throws Exception {
        System.out.println("Inside create user: " + user);

        User newUser = userService.createUser(user);
        ResponseBody responseBody = new ResponseBody("User created successfully!", false, HttpStatus.OK, newUser);
        return ResponseEntity.ok(responseBody);
    }

//    update user
    @PutMapping("/update")
    public ResponseEntity<ResponseBody> updateUser(@RequestBody User user) throws Exception {
        System.out.println("Inside update user: " + user);

        User newUser = userService.updateUser(user);
        ResponseBody responseBody = new ResponseBody("User updated successfully!", false, HttpStatus.OK, newUser);
        return ResponseEntity.ok(responseBody);
    }
}
