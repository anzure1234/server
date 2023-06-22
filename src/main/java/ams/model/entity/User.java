package ams.model.entity;

import ams.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class User extends BaseEntity {
    private String account;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String email;

    private Integer phoneNumber;

    private String address;

    @OneToMany(mappedBy = "user")
    private List<Order> orderList;

    @OneToOne
    private Cart cart;

}
