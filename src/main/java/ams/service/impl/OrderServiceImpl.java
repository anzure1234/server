package ams.service.impl;

import ams.model.entity.Order;
import ams.repository.OrderRepository;
import ams.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, Long, OrderRepository> implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<Order> findByUserId(Long id) {
        return orderRepository.findByUserIdAndDeletedFalse(id);
    }
}
