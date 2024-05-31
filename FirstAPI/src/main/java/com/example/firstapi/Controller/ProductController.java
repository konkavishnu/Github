package com.example.firstapi.Controller;

import com.example.firstapi.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping()
    public List<Product> getAllProducts(){
       return new ArrayList<>();
    }

    @GetMapping("/{id}") // to get details of a particular object
    public Product getProductById(@PathVariable ("id") Long id){
        return new Product();
    }

    @PostMapping() // to create or Save a new object
    public Product addNewProduct(@RequestBody Product product){
        Product P = new Product();
        P.setTitle("A new product");
        return P;
    }

    @PatchMapping("/{id}") // to update only the attributes of a object
    public Product updateProduct(@PathVariable ("id") Long id, @RequestBody Product product){
        return new Product();
    }

    @PutMapping("/{id}") // to replace the full Object
    public Product replaceProduct(@PathVariable ("id") Long id , @RequestBody Product product){
        return new Product();
    }

    @DeleteMapping("/{id}") // to remove a full object
    public void deleteProduct(@PathVariable ("id") Long id){

    }

}
