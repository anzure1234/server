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
public class OrderDetail extends BaseEntity {

    private Integer quantity;

    private Double price;

    @ManyToOne
    private Order order;

    @OneToMany(mappedBy = "orderDetail")
    private List<Product> product;
}
