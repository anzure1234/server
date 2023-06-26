package ams.model.dto;

import ams.model.entity.Product;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDto extends BaseResponseDto{
    private String categoryName;
}
