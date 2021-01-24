package ge.odvali.ebookstore.controllers;

import ge.odvali.ebookstore.entities.User;
import ge.odvali.ebookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity save(@RequestBody User user){
            return userService.save(user);
    }

    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }
}
