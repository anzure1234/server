package ams.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderAndOrderDetailDisplayDto {

    public OrderDisplayDto orderDisplayDto = new OrderDisplayDto();

    public List<OrderDetailDisplayDto> orderDetailDisplayDto = new ArrayList<>();
}
