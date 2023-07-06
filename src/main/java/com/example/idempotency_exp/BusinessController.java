package com.example.idempotency_exp;

import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BusinessController {

    @Autowired
    private TokenService tokenService;

    List<User> users = new ArrayList<>();

    @GetMapping("/get/token")
    public ResponseEntity<String> getToken(@RequestBody User user){
        String token = tokenService.createToken(user);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @AutoIdempotent
    @PostMapping("/test/post")
    public ResponseEntity<User> testPost(@RequestBody User user) throws Exception{
        try{
            users.add(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }

    }
}
