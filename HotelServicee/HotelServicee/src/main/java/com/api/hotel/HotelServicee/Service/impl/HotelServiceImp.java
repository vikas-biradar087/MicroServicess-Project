package com.api.hotel.HotelServicee.Service.impl;

import com.api.hotel.HotelServicee.Payload.HotelDto;
import com.api.hotel.HotelServicee.Repository.HotelRepository;
import com.api.hotel.HotelServicee.Exception.ResourceNotFoundException;
import com.api.hotel.HotelServicee.Service.HotelService;
import com.api.hotel.HotelServicee.entity.Hotel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HotelServiceImp implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    private ModelMapper modelMapper;

    public HotelServiceImp(HotelRepository hotelRepository, ModelMapper modelMapper) {
        this.hotelRepository = hotelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public HotelDto createHotel(HotelDto hotelDto) {

        String randomHotelId = UUID.randomUUID().toString();
        hotelDto.setHotelId(randomHotelId);

        // dto to entity
        Hotel hotel=mapToEntity(hotelDto);
        Hotel newHotel = hotelRepository.save(hotel);

        // entity to dto

        HotelDto hotelResponse=mapToDTO(newHotel);
        return hotelResponse;
    }

    //Convert Dto to Entity
    private Hotel mapToEntity(HotelDto hotelDto){
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        return hotel;
    }

    //Convert  Entity to dto
    private HotelDto mapToDTO(Hotel hotel){
        HotelDto hotelDto = modelMapper.map(hotel, HotelDto.class);
        return hotelDto;
    }


    @Override
    public List<HotelDto> fetchAllHotel() {
        List<Hotel> hotelList = hotelRepository.findAll();
        List<HotelDto> collect = hotelList.stream().map(x -> mapToDTO(x)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public HotelDto getHotelById(String hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel with given id is not found on server" + hotelId));
        return mapToDTO(hotel);
    }
}
