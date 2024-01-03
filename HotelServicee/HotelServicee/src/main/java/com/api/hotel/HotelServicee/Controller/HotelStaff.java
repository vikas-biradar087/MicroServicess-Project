package com.api.hotel.HotelServicee.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/Staff")
public class HotelStaff {
    @GetMapping
    public ResponseEntity<List<String>> createStaff(){
        List<String> asList = Arrays.asList("Sachin", "Akash", "Ramesh");
        return new ResponseEntity<>(asList, HttpStatus.OK);
    }

}
