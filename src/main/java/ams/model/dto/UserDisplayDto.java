package ams.model.dto;

import ams.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDisplayDto {

    private Long userId;

//    private String account;
//    private String password;

    private UserRole role;

    private String email;

    private Integer phoneNumber;

    private String address;
}
