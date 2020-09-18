package com.am.ecommerce.controller;

import com.am.ecommerce.dto.UserDto;
import com.am.ecommerce.model.UserData;
import com.am.ecommerce.security.JwtTokenUtil;
import com.am.ecommerce.security.JwtUserDetailsService;
import com.am.ecommerce.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    public UserService userService;
    @Autowired
    JwtUserDetailsService jwtUserService;
    @Autowired
    JwtTokenUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;


    public UserDto loadUserData(String authorization) {

        String username = null;
        String jwtToken = null;
        jwtToken = authorization.substring(7);
        try {
            username = jwtUtil.getUsernameFromToken(jwtToken);
        } catch (IllegalArgumentException e) {
            System.out.println("Unable to get JWT Token");
        } catch (ExpiredJwtException e) {
            System.out.println("JWT Token has expired");
        }

        UserData data = userService.getUserByEmail(username);
        UserDto response = new UserDto();
        response.setEmail(data.getEmail());
        response.setFirstName(data.getFirstName());
        response.setLastName(data.getLastName());
        response.setUserRole(data.getUserRole());
        response.setAvatar(data.getAvatar());
        response.setMobile(data.getMobile());
        return response;
    }

    public UserDto registerUser(UserDto userDetails) {
        System.out.println("userDetails in controller: " + userDetails);
        UserData userData = jwtUserService.save(userDetails);
        userDetails.setUserRole(userData.getUserRole());
        userDetails.setUserPwd(null);
        final UserDetails userDetailsFromDb = jwtUserService.loadUserByUsername(userData.getEmail());

        userDetails.setToken(jwtUtil.generateToken(userDetailsFromDb));
        return userDetails;
    }

    public ResponseEntity<UserDto> authenticateUser(UserDto userDetails) {
        UserDto response = new UserDto();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDetails.getEmail(), userDetails.getUserPwd()));
        } catch (DisabledException e) {
            response.setLoginSuccess(false);
            response.setErrorMessage("User is disabled!. Kindly contact administrator.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (BadCredentialsException e) {
            response.setLoginSuccess(false);
            response.setErrorMessage("Invalid Credentials.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        final UserDetails userDetailsFromDb = jwtUserService.loadUserByUsername(userDetails.getEmail());
        response.setEmail(userDetails.getEmail());
        response.setToken(jwtUtil.generateToken(userDetailsFromDb));
        response.setUserRole(userDetailsFromDb.getAuthorities().stream().
                map(grantedAuthority -> grantedAuthority.getAuthority()).reduce("", String::concat));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    public UserDto updateUserDetails(String authorization, UserDto userDetails) {
        String username = null;
        String jwtToken = null;
        jwtToken = authorization.substring(7);
        try {
            username = jwtUtil.getUsernameFromToken(jwtToken);
        } catch (IllegalArgumentException e) {
            System.out.println("Unable to get JWT Token");
        } catch (ExpiredJwtException e) {
            System.out.println("JWT Token has expired");
        }
        userDetails.setEmail(username);
        this.userService.updateUserData(userDetails);
        return userDetails;
    }

}
