package com.api.user.Payload;

import com.api.user.Entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingDto {

    private String ratingId;
    private String userId;
    private String  hotelId;
    private int rating;

    private String feedback;

    private Hotel hotel;
}
