package com.api.rating.service;

import com.api.rating.entites.Rating;
import com.api.rating.payload.RatingDto;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface RatingService {

    public RatingDto createRating(RatingDto ratingDto);

    //get all ratings
    List<RatingDto> getAllRating();

    //get all by userid
    List<RatingDto> getRatingByUserId(String userId);

    //get all by hotel
    List<RatingDto> getRatingByHotelId(String hotelId);



}
