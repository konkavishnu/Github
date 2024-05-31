package com.example.firstapi.Services;

import com.example.firstapi.DTO.FakeStoreProductDTO;
import com.example.firstapi.models.Category;
import com.example.firstapi.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


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

    public List<Product> getAllProducts() {

        FakeStoreProductDTO [] response = restTemplate.getForObject
                ("https://fakestoreapi.com/products",FakeStoreProductDTO[].class);
        List<Product> answer = new ArrayList<>();
        for(FakeStoreProductDTO dto : response) {
            answer.add(convertFakeStroreProductDTOToProduct(dto));
        }
        return answer;
    }

    public Product addNewProduct(Product product) {
        FakeStoreProductDTO FakeStoreCreateDTO = new FakeStoreProductDTO();
        FakeStoreCreateDTO.setTitle(product.getTitle());
        FakeStoreCreateDTO.setId(product.getId());
        FakeStoreCreateDTO.setPrice(product.getPrice());
        FakeStoreCreateDTO.setDescription(product.getDescription());
        FakeStoreCreateDTO.setImage(product.getImageURL());
        FakeStoreCreateDTO.setCategory(product.getCategory().getName());
        FakeStoreProductDTO productDTO = restTemplate.postForObject("https://fakestoreapi.com/products" ,
                FakeStoreCreateDTO, FakeStoreProductDTO.class);

        return convertFakeStroreProductDTOToProduct(productDTO);
    }

    public Product updateProduct(Long id , Product product) {
        FakeStoreProductDTO FakeStoreUpdateDTO = new FakeStoreProductDTO();
        FakeStoreUpdateDTO.setTitle(product.getTitle());
        FakeStoreUpdateDTO.setPrice(product.getPrice());
        FakeStoreUpdateDTO.setDescription(product.getDescription());
        FakeStoreUpdateDTO.setImage(product.getImageURL());
        FakeStoreUpdateDTO.setCategory(product.getCategory().getName());
        FakeStoreProductDTO productDTO = restTemplate.patchForObject("https://fakestoreapi.com/products/"+ id,
                FakeStoreUpdateDTO , FakeStoreProductDTO.class);
        return convertFakeStroreProductDTOToProduct(productDTO);
    }

    @Override
    public Product replaceProduct(Long id , Product product) {
        //NOTE : the below code is obtained from internal implementation inside REST TEMPLATE class in the function "getPostObject()"
        RequestCallback requestCallback = restTemplate.httpEntityCallback(new FakeStoreProductDTO() , FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDTO.class , restTemplate.getMessageConverters());
        FakeStoreProductDTO response = restTemplate.execute("https://fakestoreapi.com/products/"+id , HttpMethod.PUT , requestCallback , responseExtractor , new Object[]{});
        return convertFakeStroreProductDTOToProduct(response);
    }


}
