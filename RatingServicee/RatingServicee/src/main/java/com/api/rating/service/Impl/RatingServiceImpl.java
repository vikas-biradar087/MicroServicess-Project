package com.api.rating.service.Impl;

import com.api.rating.entites.Rating;
import com.api.rating.payload.RatingDto;
import com.api.rating.repository.RatingRepository;
import com.api.rating.service.RatingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {

    private RatingRepository ratingRepository;
    private ModelMapper modelMapper;

    public RatingServiceImpl(RatingRepository ratingRepository,ModelMapper modelMapper) {
        this.ratingRepository = ratingRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public RatingDto createRating(RatingDto ratingDto) {


        String ratingRandomId = UUID.randomUUID().toString();
        ratingDto.setRatingId(ratingRandomId);
        // convert dto to entity
        Rating rating=mapToEntity(ratingDto);
        Rating newRating = ratingRepository.save(rating);

        RatingDto ratingResponse=mapToDTO(newRating);
        return ratingResponse;

    }

    //convert dto to entity

    private Rating mapToEntity(RatingDto ratingDto){
        Rating rating = modelMapper.map(ratingDto, Rating.class);
        return rating;
    }
    // convert entity to dto
    private RatingDto mapToDTO(Rating rating){
        RatingDto ratingDto = modelMapper.map(rating, RatingDto.class);
        return ratingDto;
    }

    @Override
    public List<RatingDto> getAllRating() {
        List<Rating> ratingList = ratingRepository.findAll();
        List<RatingDto> collect = ratingList.stream().map(x -> mapToDTO(x)).collect(Collectors.toList());
        return collect;

    }

    @Override
    public List<RatingDto> getRatingByUserId(String userId) {
        List<Rating> byUserId = ratingRepository.findByUserId(userId);
        List<RatingDto> ratingDtoList = byUserId.stream().map(p -> mapToDTO(p)).collect(Collectors.toList());
        return ratingDtoList;

    }
    @Override
    public List<RatingDto> getRatingByHotelId(String hotelId) {

        List<Rating> byHotelId = ratingRepository.findByHotelId(hotelId);
        List<RatingDto> hotelDtoList = byHotelId.stream().map(a -> mapToDTO(a)).collect(Collectors.toList());
        return hotelDtoList;
    }
}
