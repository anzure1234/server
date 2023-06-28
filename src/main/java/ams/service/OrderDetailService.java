package ams.service;

import ams.model.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService extends BaseService<OrderDetail, Long>{

    List<OrderDetail> findAllByOrderId(Long id);
}
