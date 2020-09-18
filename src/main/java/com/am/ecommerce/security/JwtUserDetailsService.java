package com.am.ecommerce.security;

import com.am.ecommerce.dto.UserDto;
import com.am.ecommerce.model.UserData;
import com.am.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserData userData = this.userService.getUserByEmail(userId);
        if(userData==null){
            throw new UsernameNotFoundException("User not found with username: " + userId);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userData.getUserRole();
            }
        });
        return new User(userData.getEmail(), userData.getUserPwd(),
                authorities);
    }

    public UserData save(UserDto userDetails){
        System.out.println("userDetails : "+userDetails);
        UserData userData = new UserData();
        userData.setEmail(userDetails.getEmail());
        userData.setUserPwd(bcryptEncoder.encode(userDetails.getUserPwd()));
        userData.setUserRole("CUSTOMER");
        userData.setFirstName(userDetails.getFirstName());
        userData.setLastName(userDetails.getLastName());
        return this.userService.saveUserData(userData);
    }

}
