package ams.repository;

import ams.model.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long> {
    Optional<Category> findByCategoryNameAndDeletedFalse(String categoryName);


}
