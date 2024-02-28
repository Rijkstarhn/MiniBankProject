package com.eazybytes.cards.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {
    @GetMapping("/fetch")
    public String fetchCardDetails() {
        return "Fetch a card!";
    }
}
