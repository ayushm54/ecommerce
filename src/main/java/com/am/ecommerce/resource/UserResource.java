package com.am.ecommerce.resource;

import com.am.ecommerce.controller.UserController;
import com.am.ecommerce.dto.UserDto;
import com.am.ecommerce.model.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private UserController userController;

    @RequestMapping(path = "/load", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> loadUserData(@RequestHeader("Authorization") String authorization){
        return new ResponseEntity<>(this.userController.loadUserData(authorization), HttpStatus.OK);
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDetails){
        return ResponseEntity.ok(userController.registerUser(userDetails));
    }

    @RequestMapping(path = "/authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> authenticateUser(@RequestBody UserDto userDetails) throws Exception{
        return userController.authenticateUser(userDetails);
    }

    @RequestMapping(path = "/updateUserDetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateUserDetails(@RequestHeader("Authorization") String authorization, @RequestBody UserDto userDetails) throws Exception{
        return ResponseEntity.ok(userController.updateUserDetails(authorization, userDetails));
    }
}
