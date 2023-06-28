package ams.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailDisplayDto {

    private Long orderDetailId;

    private Integer quantity;

    private Double price;

    private Long orderId;
}
