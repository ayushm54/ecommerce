package com.am.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class UserDto {
    private String email;
    private String firstName;
    private String lastName;
    private String userPwd;
    private String userRole;
    private String mobile;
    private String avatar;
    private String token;
    Boolean loginSuccess;
    String errorMessage;
}
