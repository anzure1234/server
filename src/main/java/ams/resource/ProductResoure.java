package ams.resource;

import ams.constant.AppConstant;
import ams.model.dto.BaseResponseDto;
import ams.model.dto.ProductDisplayDto;
import ams.model.dto.ProductDto;
import ams.model.entity.Category;
import ams.model.entity.Product;
import ams.security.SecurityUtil;
import ams.service.CategoryService;
import ams.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping
    public ResponseEntity<BaseResponseDto> showAll(
            @RequestParam(required = false, defaultValue = AppConstant.DEFAULT_PAGE_STR) Integer page,
            @RequestParam(required = false, defaultValue = AppConstant.DEFAULT_PAGE_SIZE_STR) Integer size,
            @RequestParam(required = false, name = "sort", defaultValue = AppConstant.DEFAULT_SORT_FIELD) List<String> sorts,
            @RequestParam(required = false, name = "q") Optional<String> keywordOpt) {

        List<Sort.Order> orders = new ArrayList<>();
        for (String sortField : sorts) {
            boolean isDesc = sortField.startsWith("-");
            orders.add(isDesc ? Sort.Order.desc(sortField.substring(1)) : Sort.Order.asc(sortField));
        }

        Specification<Product> spec = (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("deleted"), false);
        if (keywordOpt.isPresent()) {
            Specification<Product> specByKeyWord = (root, query, criteriaBuilder) ->
                    criteriaBuilder.or(
                            criteriaBuilder.like(root.get("productName"), "%" + keywordOpt.get() + "%"),
                            criteriaBuilder.like(root.get("productCode"), "%" + keywordOpt.get() + "%")
                    );
            spec = spec.and(specByKeyWord);
        }
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(orders));
        Page<Product> productPage = productService.findAll(spec, pageRequest);
        List<ProductDisplayDto> productDisplayDtos = productPage.getContent().stream().map(product -> {
            ProductDisplayDto productDisplayDto = new ProductDisplayDto();
            BeanUtils.copyProperties(product, productDisplayDto);

            return productDisplayDto;
        }).collect(Collectors.toList());

        Page<ProductDisplayDto> result = new PageImpl<>(productDisplayDtos, pageRequest, productPage.getTotalElements());
        return success(result, "OK");
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
    public ResponseEntity<BaseResponseDto> updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto) {
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
