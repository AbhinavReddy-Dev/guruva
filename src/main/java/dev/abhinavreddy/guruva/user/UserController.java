package dev.abhinavreddy.guruva.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
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
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody User user){
        System.out.println("Inside create user: " + user);
        Map<String, Object> newUser = userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }
}
