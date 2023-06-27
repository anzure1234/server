package ams.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDisplayDto {
    private String productName;

    private String productDescription;

    private Double price;

    private Integer quantityInStock;

    private String image;

}
