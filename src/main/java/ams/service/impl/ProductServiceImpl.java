package ams.service.impl;

import ams.model.entity.Product;
import ams.repository.ProductRepository;
import ams.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product,Long,ProductRepository> implements ProductService {


}
