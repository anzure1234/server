package ams.repository;

import ams.model.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends BaseRepository<Order, Long> {

    Optional<Order> findByUserIdAndDeletedFalse(Long id);


}
