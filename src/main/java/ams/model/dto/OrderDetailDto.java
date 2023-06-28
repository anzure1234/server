package ams.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailDto {

    private Long orderDetailId;

    private Integer quantity;

    private Double price;

    private Long orderId;

}
