package com.api.user.Controller;

import com.api.user.Payload.UserDto;
import com.api.user.Service.UserService;
import com.api.user.ServiceImpl.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/UserApi")
public class UserController {

    private UserService userService;

    private Logger logger= LoggerFactory.getLogger(UserController.class);


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto user = userService.createUser(userDto);
//        return new ResponseEntity<>(user, HttpStatus.CREATED);
       return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") String userId){
        logger.info("Get Single User Handler : UserController");
        UserDto userDto = userService.getUserById(userId);
//        return ResponseEntity.ok(userDto);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }


    // creating fall back method for circuit breaker

    public ResponseEntity<UserDto> ratingHotelFallback(String userId,Exception ex){
        logger.info("Fallback is executed because service is down :",ex.getMessage());
        UserDto userDto = UserDto.builder()
                .email("vikas@gmail.com")
                .name("vikas")
                .about("This is user created dummy because some services down")
                .userId("124576")
                .build();
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);

    }
}
