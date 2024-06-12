package com.example.firstapi.Controller;

import com.example.firstapi.Exceptions.ProductNotExistException;
import com.example.firstapi.Services.ProductService;
import com.example.firstapi.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(@Qualifier("selfProductService") ProductService productService) { //--> Since there is 2 implementation with "ProductService" we are telling the controller to take a particular implementation by using @Qualifier.
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity <List<Product>> getAllProducts(){
        ResponseEntity<List<Product>> response = new ResponseEntity<>(productService.getAllProducts(), HttpStatus.NOT_FOUND);
       return response;
    }

    @GetMapping("/{id}") // to get details of a particular object
    public ResponseEntity<Product> getProductById(@PathVariable ("id") Long id) throws ProductNotExistException {
        return new ResponseEntity<>(productService.getSingleProduct(id),HttpStatus.OK);
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
    public ResponseEntity<Void> deleteProduct(@PathVariable ("id") Long id){
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @ExceptionHandler(ProductNotExistException.class) //--> We can also throw exception in controller level , but if we do that this will handle the exception rather the one in controller advice.
    public ResponseEntity<Void> handleProductNotExistException(){
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

}
