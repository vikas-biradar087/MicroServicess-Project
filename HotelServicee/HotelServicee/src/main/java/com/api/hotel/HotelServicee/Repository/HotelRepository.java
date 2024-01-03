package com.api.hotel.HotelServicee.Repository;

import com.api.hotel.HotelServicee.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,String> {
}
