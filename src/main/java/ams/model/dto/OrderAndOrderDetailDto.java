package ams.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderAndOrderDetailDto {

    public OrderDto orderDto = new OrderDto();

    public List<OrderDetailDto> orderDetailDto = new ArrayList<>();
}
