package com.alex.APISECURITY.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@PreAuthorize("denyAll()")
public class TestAuthController {


    @GetMapping("/hello")
    @PreAuthorize("permitAll()")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/hello-secured")
    @PreAuthorize("hasAnyAuthority('READ')")
    public String helloSecured(){
        return "Secured Hello World";
    }

    @GetMapping("/hello-secured2")
    @PreAuthorize("hasAnyAuthority('CREATE')")
    public String helloSecured2(){
        return "Secured 2 Hello World";
    }

    @GetMapping("/hello-developer")
    @PreAuthorize("hasAnyAuthority('DEVELOPER')")
    public String helloDeveloper(){
        return "Hello developer";
    }


}
