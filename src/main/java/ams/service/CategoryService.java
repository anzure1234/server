package ams.service;

import ams.model.entity.Category;

import java.util.Optional;

public interface CategoryService extends BaseService<Category,Long>{
    Optional<Category> findCategoryByName(String categoryName);
}
