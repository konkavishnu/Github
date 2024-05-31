package com.example.firstapi.Services;

import com.example.firstapi.DTO.FakeStoreProductDTO;
import com.example.firstapi.models.Category;
import com.example.firstapi.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class FakestoreProductService implements ProductService{


    private RestTemplate restTemplate;

    @Autowired
    public FakestoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStroreProductDTOToProduct(FakeStoreProductDTO fakeStoreProductDTO) {
        Product product = new Product();
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setId(fakeStoreProductDTO.getId());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setImageURL(fakeStoreProductDTO.getImage());
        product.setCategory(new Category()); //-------------------> Since the "category" in our App is of type Object and not string which the fake_store will give in response
        product.getCategory().setName(fakeStoreProductDTO.getCategory());
        return product;
    }

    @Override
    public Product getSingleProduct(Long id) {
        FakeStoreProductDTO productDTO = restTemplate.getForObject("https://fakestoreapi.com/products/1"
                + id, FakeStoreProductDTO.class);
        return convertFakeStroreProductDTOToProduct(productDTO);
    }
}
