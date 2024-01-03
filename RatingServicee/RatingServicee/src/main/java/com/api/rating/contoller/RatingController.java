package com.api.rating.contoller;

import com.api.rating.entites.Rating;
import com.api.rating.payload.RatingDto;
import com.api.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/RatingApi")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<RatingDto> createRating(@RequestBody RatingDto ratingDto){
        RatingDto rating = ratingService.createRating(ratingDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating);
    }
    @GetMapping
    public ResponseEntity<List<RatingDto>> fetchAllRating(){
        List<RatingDto> allRating = ratingService.getAllRating();
        return ResponseEntity.ok(allRating);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RatingDto>> getUserById(@PathVariable("userId") String userId){
        List<RatingDto> ratingByUserId = ratingService.getRatingByUserId(userId);
        return ResponseEntity.ok(ratingByUserId);
    }



    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<RatingDto>> getHotelById(@PathVariable("hotelId") String hotelId){
        List<RatingDto> ratingByHotelId = ratingService.getRatingByHotelId(hotelId);
        return ResponseEntity.ok(ratingByHotelId);
    }

}
