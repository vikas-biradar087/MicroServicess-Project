package com.api.rating.repository;

import com.api.rating.entites.Rating;
import com.api.rating.payload.RatingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating,String> {

//    @Query("SELECT new com.api.rating.payload.RatingDto(r.field1, r.field2, ...) FROM Rating r WHERE r.userId = :userId")
//    List<RatingDto> findByUserId(@Param("userId") String userId);
    List<Rating> findByUserId(String userId);

    List<Rating> findByHotelId(String hotelId);

//    @Query("SELECT new com.api.rating.payload.RatingDto(r.field1, r.field2, ...) FROM Rating r WHERE r.hotelId = :hotelId")
//    List<RatingDto> findByHotelId(@Param("hotelId") String hotelId);


}
