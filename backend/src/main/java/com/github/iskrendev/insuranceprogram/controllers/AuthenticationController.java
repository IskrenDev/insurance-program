package com.github.iskrendev.insuranceprogram.controllers;

import com.github.iskrendev.insuranceprogram.models.AppUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @GetMapping("/me")
    public AppUser getMe() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof DefaultOAuth2User defaultOAuth2User) {
            return AppUser.builder()
                    .id(Integer.parseInt(defaultOAuth2User.getAttributes().get("id").toString()))
                    .login(defaultOAuth2User.getAttributes().get("login").toString())
                    .build();
        }
        throw new IllegalArgumentException("No user logged in");
    }
}