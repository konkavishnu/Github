package com.example.firstapi.Services;

import com.example.firstapi.Exceptions.ProductNotExistException;
import com.example.firstapi.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long id) throws ProductNotExistException;

    List<Product> getAllProducts();

    Product addNewProduct(Product product);

    Product updateProduct(Long id , Product product);

    Product replaceProduct(Long id, Product product);
}
