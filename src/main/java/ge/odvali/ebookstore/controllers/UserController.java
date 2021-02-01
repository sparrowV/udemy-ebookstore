package ge.odvali.ebookstore.controllers;

import ge.odvali.ebookstore.entities.BuyBookDTO;
import ge.odvali.ebookstore.entities.User;
import ge.odvali.ebookstore.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@SecurityRequirement(name = "jwt")
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

    @PostMapping("/buy-book")
    public ResponseEntity buyBook(@RequestBody BuyBookDTO buyBookDTO) {
        return userService.buyBook(buyBookDTO);
    }
}
