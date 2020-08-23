package com.cyan.Gabriel.api;

import com.cyan.Gabriel.model.User;
import com.cyan.Gabriel.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private static final long EXPIRATION_TIME = 60_000_000;
    private static final String SECRET = "banana";

    @Autowired
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value="/login")
    @ResponseBody
    public LoginResponse login(@RequestBody User user) throws ServletException {

        User authUser = userService.findUserByEmail(user.getEmail());

        if(authUser == null) {
            throw new ServletException("Usuario nao encontrado!");
        }

        if(!authUser.getPassword().equals(user.getPassword())) {
            throw new ServletException("Senha invalida!");
        }

        String token = Jwts.builder()
                .setSubject(authUser.getEmail())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        return new LoginResponse(token);
    }

    private class LoginResponse {
        private String token;
        public LoginResponse(String token){
            this.token = token;
        }
        public String getToken(){
            return token;
        }
    }

}
