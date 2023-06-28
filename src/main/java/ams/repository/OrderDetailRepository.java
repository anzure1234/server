package ams.repository;

import ams.model.entity.OrderDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends BaseRepository<OrderDetail, Long>{

    List<OrderDetail> findByOrderIdAndDeletedFalse(Long id);
}
