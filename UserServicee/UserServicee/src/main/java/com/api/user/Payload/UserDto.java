package com.api.user.Payload;

import com.api.user.Entity.Rating;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String userId;
    private String name;
    private String email;
    private String about;

    @Transient
    private List<Rating> ratings=new ArrayList<>();

}
