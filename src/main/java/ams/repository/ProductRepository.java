package ams.repository;

import ams.model.entity.Product;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {


}
