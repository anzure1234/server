package ams.model.entity;

import ams.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "orders")
@Getter
@Setter
public class Order extends BaseEntity {

    private LocalDate orderDate;

    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetailList;

}
