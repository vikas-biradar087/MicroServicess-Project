package com.api.user.ServiceImpl;

import com.api.user.Entity.Hotel;
import com.api.user.Entity.Rating;
import com.api.user.Entity.User;
import com.api.user.Exception.ResourceNotFoundException;
import com.api.user.Payload.UserDto;
import com.api.user.Repository.UserRepository;
import com.api.user.Service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    private RestTemplate restTemplate;

    private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository,ModelMapper modelMapper,RestTemplate restTemplate) {

        this.userRepository = userRepository;
        this.modelMapper=modelMapper;
        this.restTemplate=restTemplate;
    }



    @Override
    public UserDto createUser(UserDto userDto) {

        String randomUserId = UUID.randomUUID().toString();
        userDto.setUserId(randomUserId);

        //Convert Dto to Entity
        User user=mapToEntity(userDto);
        User newUser = userRepository.save(user);


        //Convert Entity to dto
        UserDto userResponse = mapToDTO(newUser);
        return userResponse;
    }

    //Convert Dto to entity
    private User mapToEntity(UserDto userDto){
        User user = modelMapper.map(userDto,User.class);
        return user;
    }

    //Convert Entity to dto
    private UserDto mapToDTO(User user){
        UserDto userDto = modelMapper.map(user,UserDto.class);
        return  userDto;
    }

    @Override
    public UserDto getUserById(String userId) {
        //get user from database with the help user repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !!"+userId));
        // fetch rating the above user from rating service
        //http://localhost:8082/RatingApi/user/f89acda7-1b58-4d29-900d-2def97909a57
        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/RatingApi/user/"+user.getUserId(), Rating[].class);
        logger.info("{}",ratingsOfUser);


        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingsList = ratings.stream().map(rating -> {

            //api call to hotel service to get the hotel
            //http://localhost:8081/HotelApi/1f80564d-b679-4b5a-bab3-7c54142dee2f
            //http://localhost:8081/HotelApi/1f80564d-b679-4b5a-bab3-7c54142dee2f
//            System.out.println(rating.getHotelId());
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/HotelApi/"+rating.getHotelId(), Hotel.class);

            Hotel hotel=forEntity.getBody();

            logger.info("response status code: {}",forEntity.getStatusCode());

            //set the hotel to rating
            rating.setHotel(hotel);

            //return the rating
            return rating;

        }).collect(Collectors.toList());

        user.setRatings(ratingsList);
        return mapToDTO(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDto> collect = users.stream().map(x -> mapToDTO(x)).collect(Collectors.toList());
        return collect;
    }
}
