package com.api.hotel.HotelServicee.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super(" Resource Not Found Exception !!!");
    }

    public ResourceNotFoundException(String s){
        super((s));

    }

}