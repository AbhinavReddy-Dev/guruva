package dev.abhinavreddy.guruva.user;

import dev.abhinavreddy.guruva.reqres.ResponseBody;
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
//        try{
            User newUser = userService.createUser(user);
            ResponseBody responseBody = new ResponseBody("User created successfully", false,200, newUser);
            return ResponseEntity.ok(responseBody);
//        } catch (org.springframework.dao.IncorrectResultSizeDataAccessException e) {
//          throw new Exception("User already exists");
//        }
    }
}
