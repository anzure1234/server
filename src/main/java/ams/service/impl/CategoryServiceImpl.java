package ams.service.impl;

import ams.model.entity.Category;
import ams.repository.CategoryRepository;
import ams.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category,Long, CategoryRepository> implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Category> findCategoryByName(String categoryName) {
        return categoryRepository.findByCategoryNameAndDeletedFalse(categoryName);
    }
}
