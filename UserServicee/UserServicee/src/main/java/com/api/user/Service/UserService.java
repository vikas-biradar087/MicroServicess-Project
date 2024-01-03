package com.api.user.Service;

import com.api.user.Payload.UserDto;

import java.util.List;

public interface UserService {

    //create user
    UserDto createUser(UserDto userDto);

    UserDto getUserById(String userId);

    List<UserDto> getAllUser();
}
