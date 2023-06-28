package ams.service.impl;

import ams.model.entity.OrderDetail;
import ams.repository.OrderDetailRepository;
import ams.service.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail, Long, OrderDetailRepository> implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public List<OrderDetail> findAllByOrderId(Long id) {
        return orderDetailRepository.findByOrderIdAndDeletedFalse(id);
    }
}
