package ams.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {
    @Id
    private String productId;

    private String productName;

    private String productDescription;

    private String price;

    private String quantityInStock;

    private String image;

    @ManyToOne
    private Category category;

    @ManyToOne
    private OrderDetail orderDetail;

}
