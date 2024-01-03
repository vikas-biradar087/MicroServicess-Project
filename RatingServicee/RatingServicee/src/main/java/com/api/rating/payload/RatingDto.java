package com.api.rating.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RatingDto {

    private String ratingId;
    private String userId;
    private String  hotelId;
    private int rating;

    private String feedback;


}
