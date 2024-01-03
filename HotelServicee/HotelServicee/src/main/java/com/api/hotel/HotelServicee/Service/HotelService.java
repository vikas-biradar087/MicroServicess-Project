package com.api.hotel.HotelServicee.Service;

import com.api.hotel.HotelServicee.Payload.HotelDto;

import java.util.List;

public interface HotelService {

    HotelDto createHotel(HotelDto hotelDto);

    List<HotelDto> fetchAllHotel();

    HotelDto getHotelById(String hotelId);
}
