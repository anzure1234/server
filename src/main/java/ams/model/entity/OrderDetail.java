package ams.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class OrderDetail {
    @Id
    private String orderDetailId;

    private Integer quantity;

    private Integer price;

    @ManyToOne
    private Order order;

    @OneToMany(mappedBy = "orderDetail")
    private List<Product> product;
}
