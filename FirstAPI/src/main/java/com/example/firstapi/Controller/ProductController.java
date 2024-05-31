package com.example.firstapi.Controller;

import com.example.firstapi.Services.ProductService;
import com.example.firstapi.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getAllProducts(){
       return productService.getAllProducts();
    }

    @GetMapping("/{id}") // to get details of a particular object
    public Product getProductById(@PathVariable ("id") Long id){
        return productService.getSingleProduct(id);
    }

    @PostMapping() // to create or Save a new object
    public Product addNewProduct(@RequestBody Product product){
        return productService.addNewProduct(product);
    }

    @PatchMapping("/{id}") // to update only the attributes of an object
    public Product updateProduct(@PathVariable ("id") Long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }

    @PutMapping("/{id}") // to replace the full Object
    public Product replaceProduct(@PathVariable ("id") Long id , @RequestBody Product product){
        return productService.replaceProduct(id, product);
    }

    @DeleteMapping("/{id}") // to remove a full object
    public void deleteProduct(@PathVariable ("id") Long id){

    }

}
