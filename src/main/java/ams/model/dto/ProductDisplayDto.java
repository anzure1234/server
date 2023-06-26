package ams.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDisplayDto {
    private String productName;

    private String productDescription;

    private Double price;

    private String quantityInStock;

    private String image;

    private String categoryName;
}
