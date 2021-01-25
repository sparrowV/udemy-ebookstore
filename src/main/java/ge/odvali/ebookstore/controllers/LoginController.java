package ge.odvali.ebookstore.controllers;

import ge.odvali.ebookstore.entities.LoginDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping
    public void fake(@RequestBody LoginDTO loginDTO) {
        //fake logins so that swagger displayes login url,but in real calls JwtAuthenticationFilter intercepts the requests
    }


}
