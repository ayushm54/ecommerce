package com.am.ecommerce.model;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@ToString
@Entity
@Table(name = "user")
public class UserData {
    @Id
    @Column(name = "email")
    private String email;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="user_pwd")
    private String userPwd;

    @Column(name="user_role")
    private String userRole;

    @Column(name="mobile")
    private String mobile;

    @Column(name="avatar")
    private String avatar;
}
