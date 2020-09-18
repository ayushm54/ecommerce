package com.am.ecommerce.service;

import com.am.ecommerce.dao.UserDao;
import com.am.ecommerce.dto.UserDto;
import com.am.ecommerce.model.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public UserData getUserByEmail(String email) {
        System.out.println("Email : "+ email);
        Optional<UserData> userData = this.userDao.findById(email);
        System.out.println("UserData : "+ userData.get());
        return userData.isPresent() ? userData.get() : null;
    }

    public UserData saveUserData(UserData userData){
        return this.userDao.save(userData);
    }
    public UserData updateUserData(UserDto userDetails){
        UserData dataInDb = this.userDao.getOne(userDetails.getEmail());
        dataInDb.setFirstName(userDetails.getFirstName());
        dataInDb.setLastName(userDetails.getLastName());
        dataInDb.setMobile(userDetails.getMobile());
        dataInDb.setAvatar(null != userDetails.getAvatar() ? userDetails.getAvatar() : dataInDb.getAvatar());
        return this.userDao.save(dataInDb);
    }

}
