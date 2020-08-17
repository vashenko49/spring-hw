package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api0/oauth2")
public class OAuth2 {

    @GetMapping("callback/google")
    private String google(Principal s) {

        System.out.println(s);


        return "1";
    }
}
