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
public class Product extends BaseEntity {

    private String productName;

    private String productDescription;

    private Double price;

    private String quantityInStock;

    private String image;

    @ManyToOne
    private Category category;

    @ManyToOne
    private OrderDetail orderDetail;

    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItemList;

}
