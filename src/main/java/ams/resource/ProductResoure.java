package ams.resource;

import ams.model.dto.BaseResponseDto;
import ams.model.dto.ProductDisplayDto;
import ams.model.dto.ProductDto;
import ams.model.entity.Category;
import ams.model.entity.Product;
import ams.security.SecurityUtil;
import ams.service.CategoryService;
import ams.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Service
@RestController
@RequestMapping("/api/product")
public class ProductResoure extends BaseResource {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductResoure(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }




    @GetMapping("/{id}")
    public ResponseEntity<ProductDisplayDto> findById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findOneOpt(id);
        if (productOptional.isPresent()) {
            ProductDisplayDto productDisplayDtoDto = new ProductDisplayDto();
            BeanUtils.copyProperties(productOptional.get(), productDisplayDtoDto);
            return ResponseEntity.ok(productDisplayDtoDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponseDto> createProduct(@RequestBody ProductDto productDto) {
        if (!SecurityUtil.isSystemAdmin()) {
            return badRequest("Product.create.notSystemAdmin");
        }
        Product product = new Product();
        Optional<Category> categoryOptional = categoryService.findCategoryByName(productDto.getCategoryName());
        if (categoryOptional.isPresent()) {
            product.setCategory(categoryOptional.get());
            BeanUtils.copyProperties(productDto, product);
            productService.create(product);
        }
        return success("Product.create.success");
    }

    @PutMapping("/{productId}")
    public ResponseEntity<BaseResponseDto> updateProduct(@PathVariable Long productId,@RequestBody ProductDto productDto) {
        if (!SecurityUtil.isSystemAdmin()) {
            return badRequest("Product.update.notSystemAdmin");
        }
        Product product = new Product();
        Optional<Product> productOptional = productService.findOneOpt(productId);
        if (productOptional.isPresent()) {
            product = productOptional.get();
        }
        BeanUtils.copyProperties(productDto, product);
        productService.update(product);
        return success("Product.update.success");
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<BaseResponseDto> deleteProduct(@PathVariable Long productId) {
        if (!SecurityUtil.isSystemAdmin()) {
            return badRequest("Product.delete.notSystemAdmin");
        }
        productService.delete(productId);
        return success("Product.delete.success");
    }

}
