package com.api.hotel.HotelServicee.Controller;

import com.api.hotel.HotelServicee.Payload.HotelDto;
import com.api.hotel.HotelServicee.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/HotelApi")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelDto> createHotel(@RequestBody HotelDto hotelDto){
        HotelDto hotel = hotelService.createHotel(hotelDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel);
    }

    @GetMapping
    public ResponseEntity<List<HotelDto>> getAllHotel(){
        List<HotelDto> allHotel = hotelService.fetchAllHotel();
        return ResponseEntity.ok(allHotel);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable("hotelId") String hotelId){
        HotelDto hotelById = hotelService.getHotelById(hotelId);
        return ResponseEntity.ok(hotelById);
    }

}
