package com.eazybytes.cards.controller;

import com.eazybytes.cards.dto.CardsDto;
import com.eazybytes.cards.dto.ResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CardsController {
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@Valid @RequestParam
                                                  @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                  String mobileNumber) {
        return null;
    }

    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestParam
                                                     @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                     String mobileNumber) {
        return null;
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCardDetails(@Valid @RequestBody CardsDto cardsDto) {
        return null;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCardDetails(@RequestParam
                                                         @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                         String mobileNumber) {
        return null;
    }
}
