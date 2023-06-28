//package ams.resource;
//
//import ams.exception.ResourceNotFoundException;
//import java.util.List;
//
//import ams.model.dto.BaseResponseDto;
//import ams.model.dto.OrderDisplayDto;
//import ams.model.dto.UserOrderDisplayDto;
//import ams.model.dto.UserOrderFormDto;
//import ams.model.entity.Order;
//import ams.model.entity.User;
//import ams.service.OrderService;
//import ams.service.UserService;
//import jakarta.validation.Valid;
//import org.springframework.beans.BeanUtils;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/user")
//public class UserResource extends BaseResource{
//
//    private final UserService userService;
//
//    private final OrderService orderService;
//
//    public UserResource(UserService userService, OrderService orderService) {
//        this.userService = userService;
//        this.orderService = orderService;
//    }
//
//    @GetMapping("/find/{userId}")
//    public ResponseEntity<UserOrderDisplayDto> showAllUserOrderByUserId(@PathVariable Long userId) {
//       Optional<User> userOpt = userService.findOneOpt(userId);
//       User user = userOpt.orElseThrow(ResourceNotFoundException::new);
//
//       List<Order> orderList = orderService.findAllByUserId(userId);
//
//       UserOrderDisplayDto userOrderDisplayDto = new UserOrderDisplayDto();
//
//        BeanUtils.copyProperties(user, userOrderDisplayDto.userDisplayDto);
//        userOrderDisplayDto.userDisplayDto.setUserId(user.getId());
//
//        List<OrderDisplayDto> orderDisplayDtoList = orderList.stream()
//                .map(order -> {
//                    OrderDisplayDto orderDisplayDto = new OrderDisplayDto();
//                    BeanUtils.copyProperties(order, orderDisplayDto);
//                    orderDisplayDto.setOrderId(order.getId());
//                    orderDisplayDto.setUserId(user.getId());
//                    return orderDisplayDto;
//                }).toList();
//
//        userOrderDisplayDto.setOrderDisplayDto(orderDisplayDtoList);
//
//        return ResponseEntity.ok(userOrderDisplayDto);
//    }
//
//    public ResponseEntity<BaseResponseDto> create(@RequestBody @Valid UserOrderFormDto userOrderFormDto){
//
//    }
//}
