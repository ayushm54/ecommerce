package com.am.ecommerce.dao;

import com.am.ecommerce.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserData, String> {

}
