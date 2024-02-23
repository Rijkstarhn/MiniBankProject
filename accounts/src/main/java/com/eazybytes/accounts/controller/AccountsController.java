package com.eazybytes.accounts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountsController {
    @GetMapping("/")
    public String hello() {
        return "Hello World!";
    }
}
