package ge.odvali.ebookstore.controllers;

import ge.odvali.ebookstore.entities.User;
import ge.odvali.ebookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity save(@RequestBody User user){
            return userService.save(user);
    }
}
