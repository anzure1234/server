package ams.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Category extends BaseEntity {
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Product> productList;
}
