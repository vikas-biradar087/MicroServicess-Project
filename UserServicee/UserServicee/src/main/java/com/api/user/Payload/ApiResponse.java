package com.api.user.Payload;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiResponse {

    private String message;
    private Boolean success;

    private HttpStatus status;
}
