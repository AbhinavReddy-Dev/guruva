package dev.abhinavreddy.guruva.user;

import dev.abhinavreddy.guruva.config.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/") // ✅
    public String test() {
        return "Hello World";
    }

    @GetMapping("/{username}") // ✅
    public ResponseEntity<ResponseBody> getUser(@PathVariable String username) {
            User user = userService.getUser(username);
           ResponseBody responseBody = new ResponseBody("User found!", false, HttpStatus.OK, user);
              return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/create") // ✅
    public ResponseEntity<ResponseBody> createUser(@RequestBody User user) throws Exception {
        System.out.println("Inside create user: " + user);

        User newUser = userService.createUser(user);
        ResponseBody responseBody = new ResponseBody("User created successfully!", false, HttpStatus.OK, newUser);
        return ResponseEntity.ok(responseBody);
    }

//    update user
    @PatchMapping("/update") // ✅
    public ResponseEntity<ResponseBody> updateUser(@RequestBody User user) throws Exception {
        System.out.println("Inside controller: " + user);
        User newUser = userService.updateUser(user);
        ResponseBody responseBody = new ResponseBody("User updated successfully!", false, HttpStatus.OK, newUser);
        return ResponseEntity.ok(responseBody);
    }

    @PatchMapping("/update/username") // ✅
    public ResponseEntity<ResponseBody> updateUsername(@RequestBody Map<String, String> usernames) throws Exception {
        System.out.println("Inside controller: " + usernames);
        User newUser = userService.updateUsername(usernames.get("oldUsername"), usernames.get("newUsername"));
        ResponseBody responseBody = new ResponseBody("Username updated successfully!", false, HttpStatus.OK, newUser);
        return ResponseEntity.ok(responseBody);
    }

    @PatchMapping("/update/password") // ✅
    public ResponseEntity<ResponseBody> updatePassword(@RequestBody Map<String, String> passwords) throws Exception {
        System.out.println("Inside controller: " + passwords);
        userService.updatePassword(passwords.get("username"), passwords.get("newPassword"));
        ResponseBody responseBody = new ResponseBody("Password updated successfully!", false, HttpStatus.OK, null);
        return ResponseEntity.ok(responseBody);
    }
}
