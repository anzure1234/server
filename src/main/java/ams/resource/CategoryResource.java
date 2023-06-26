package ams.resource;

import ams.model.dto.BaseResponseDto;
import ams.model.dto.CategoryDto;
import ams.model.entity.Category;
import ams.security.SecurityUtil;
import ams.service.CategoryService;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;



@Service
@RestController
@RequestMapping("/api/category")
public class CategoryResource extends BaseResource{
    private final CategoryService categoryService;


    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping
        public ResponseEntity<BaseResponseDto> showAll(){
        categoryService.findAll();
        return success("Category.showAll.success");
    }


    @PostMapping("/create")
    public ResponseEntity<BaseResponseDto> createCategory(@RequestBody CategoryDto categoryDto){
        if (!SecurityUtil.isSystemAdmin()){
            return badRequest("Category.create.notSystemAdmin");
        }
        Category category = new Category();
        BeanUtils.copyProperties(categoryDto, category);
        categoryService.create(category);
        return success("Category.create.success");
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<BaseResponseDto> updateCategory(@PathVariable Long categoryId, CategoryDto categoryDto){
        if (!SecurityUtil.isSystemAdmin()){
            return badRequest("Category.update.notSystemAdmin");
        }
        Optional<Category> categoryOptional = categoryService.findOneOpt(categoryId);
        Category category = categoryOptional.orElseThrow();
        BeanUtils.copyProperties(categoryDto, category);
        categoryService.update(category);
        return success("Category.update.success");
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<BaseResponseDto> deleteCategory(@PathVariable Long categoryId){
        if (!SecurityUtil.isSystemAdmin()){
            return badRequest("Category.delete.notSystemAdmin");
        }
        Optional<Category> categoryOptional = categoryService.findOneOpt(categoryId);
        if(categoryOptional.isPresent()){
            categoryService.delete(categoryId);
        }
        return success("Category.delete.success");
    }




}
