package ams.resource;
import ams.exception.ResourceNotFoundException;

import ams.model.dto.*;
import ams.model.entity.Order;
import ams.model.entity.OrderDetail;
import ams.service.OrderDetailService;
import ams.service.OrderService;
import ams.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order")
public class OrderResource extends BaseResource{

    private final UserService userService;

    private final OrderService orderService;

    private final OrderDetailService orderDetailService;

    public OrderResource(UserService userService, OrderService orderService, OrderDetailService orderDetailService) {
        this.userService = userService;
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/find/{orderId}")
    public ResponseEntity<BaseResponseDto> showAllByOrderId(@PathVariable Long orderId) {
        Optional<Order> orderOpt = orderService.findOneOpt(orderId);
        Order order = orderOpt.orElseThrow(ResourceNotFoundException::new);
        OrderDisplayDto orderDisplayDto = new OrderDisplayDto();

        List<OrderDetail> orderDetailList = orderDetailService.findAllByOrderId(orderId);

        OrderAndOrderDetailDisplayDto orderAndOrderDetailDisplayDto = new OrderAndOrderDetailDisplayDto();

        BeanUtils.copyProperties(order, orderDisplayDto);
        orderDisplayDto.setOrderId(order.getId());

        orderAndOrderDetailDisplayDto.setOrderDisplayDto(orderDisplayDto);

        List<OrderDetailDisplayDto> orderDetailDisplayDtoList = orderDetailList.stream()
                .map(orderDetail -> {
                    OrderDetailDisplayDto orderDetailDisplayDto = new OrderDetailDisplayDto();
                    BeanUtils.copyProperties(orderDetail, orderDetailDisplayDto);
                    orderDetailDisplayDto.setOrderDetailId(orderDetail.getId());
                    orderDetailDisplayDto.setOrderId(order.getId());
                    return orderDetailDisplayDto;
                }).toList();
        orderAndOrderDetailDisplayDto.setOrderDetailDisplayDto(orderDetailDisplayDtoList);

        return success("Show order and order detail successfully");
    }
    @PostMapping
    public ResponseEntity<BaseResponseDto> create(@RequestBody @Valid OrderAndOrderDetailDto orderAndOrderDetailDto) {
        Order order = new Order();
        BeanUtils.copyProperties(orderAndOrderDetailDto.orderDto, order);

        List<OrderDetail> orderDetailList = orderAndOrderDetailDto.orderDetailDto.stream()
                .map(orderDetails -> {
                    OrderDetail orderDetailDto = new OrderDetail();
                    BeanUtils.copyProperties(orderDetails, orderDetailDto);
                    return orderDetailDto;
                }).toList();
        BeanUtils.copyProperties(orderAndOrderDetailDto.orderDetailDto, order);

        orderService.create(order);

        for(OrderDetail orderDetail : orderDetailList){
            orderDetail.setOrder(order);
        }

        orderDetailService.createOrUpdate(orderDetailList);

        return success("Order created successfully");
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<BaseResponseDto> update(@PathVariable("orderId") Long orderId, @RequestBody @Valid OrderAndOrderDetailDto orderAndOrderDetailDto){

        Optional<Order> orderOpt = orderService.findOneOpt(orderId);
        Order order = orderOpt.orElseThrow(ResourceNotFoundException::new);

        BeanUtils.copyProperties(orderAndOrderDetailDto.orderDto, order);
        order.setId(orderId);
        orderService.update(order);

        List<OrderDetail> orderDetailList = orderAndOrderDetailDto.orderDetailDto.stream()
                .map(orderDetails -> {
                    OrderDetail orderDetailDto = new OrderDetail();
                    BeanUtils.copyProperties(orderDetails, orderDetailDto);
                    return orderDetailDto;
                }).toList();

        // Check if orderDetailOld is not exist in newOrderDetail then delete
        List<OrderDetail> orderDetailListOld = orderDetailService.findAllByOrderId(orderId);

        BeanUtils.copyProperties(orderAndOrderDetailDto.orderDetailDto, order);

        boolean isExist;

        for(OrderDetail orderDetailOld : orderDetailListOld){
            isExist = false;
            for(OrderDetail orderDetailNew : orderDetailList){
                if(Objects.equals(orderDetailOld.getId(), orderDetailNew.getId())){
                    isExist = true;
                    break;
                }
            }
            if(!isExist){
                orderDetailService.delete(orderDetailOld.getId());
            }
        }

        orderDetailService.createOrUpdate(orderDetailList);

        return success("Order updated successfully");
    }

    public ResponseEntity<BaseResponseDto> deleteAll(@RequestParam("orderIds") List<Long> orderIds){

        for(Long orderId : orderIds){
            Optional<Order> orderOpt = orderService.findOneOpt(orderId);
            Order order = orderOpt.orElseThrow(ResourceNotFoundException::new);
            order.setDeleted(true);
            orderService.update(order);
        }

        return success("Order deleted successfully");
    }





}
