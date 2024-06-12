package com.example.firstapi.Services;

import com.example.firstapi.Exceptions.ProductNotExistException;
import com.example.firstapi.models.Product;
import com.example.firstapi.repositories.CategoryRepository;
import com.example.firstapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
public class selfProductService implements ProductService{


    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public selfProductService(ProductRepository productRepository , CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Product getSingleProduct(Long id) throws ProductNotExistException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product addNewProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }
}
