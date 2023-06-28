package ams.service;

import ams.model.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService extends BaseService<Order, Long>{

    Optional<Order> findByUserId(Long id);
}
