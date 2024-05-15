package com.alex.APISECURITY.controller;


import com.alex.APISECURITY.controller.dto.AuthResponse;
import com.alex.APISECURITY.controller.dto.LoginRequest;
import com.alex.APISECURITY.service.UserDetailServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class AuthenticationController {

    private UserDetailServiceImpl userDetailsService;

    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest userRequest){
        return new ResponseEntity<>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK)
    }


}
