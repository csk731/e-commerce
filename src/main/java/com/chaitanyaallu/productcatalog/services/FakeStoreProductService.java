package com.chaitanyaallu.productcatalog.services;

import com.chaitanyaallu.productcatalog.dtos.FakeStoreProductDto;
import com.chaitanyaallu.productcatalog.dtos.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private RestTemplateBuilder restTemplateBuilder;

    private String getProductRequestURL="https://fakestoreapi.com/products/{id}";
    private String createProductRequestURL="https://fakestoreapi.com/products";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product){
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<GenericProductDto> responseEntity=restTemplate.postForEntity(createProductRequestURL,product, GenericProductDto.class);
        return responseEntity.getBody();
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        RestTemplate restTemplate=restTemplateBuilder.build();
        List<GenericProductDto> listOfAllProducts=new ArrayList<>();
        //ResponseEntity<GenericProductDto[]> responseEntity=restTemplate.getForEntity("https://fakestoreapi.com/products", GenericProductDto[].class);
        ResponseEntity<List> responseEntity=restTemplate.getForEntity("https://fakestoreapi.com/products", List.class);
        //List<GenericProductDto> products=new ArrayList<>(Arrays.asList(responseEntity.getBody()));
        return responseEntity.getBody();
        //return null/*products*/;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        //restTemplate.delete("https://fakestoreapi.com/products/{id}",id);
        ResponseEntity<GenericProductDto> responseEntity=restTemplate.exchange("https://fakestoreapi.com/products/{id}", HttpMethod.DELETE,null,GenericProductDto.class,id);
        return responseEntity.getBody();
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto product) {
        RestTemplate restTemplate=new RestTemplate();
        //restTemplate.put("https://fakestoreapi.com/products/{id}",null,id);
        //ResponseEntity<GenericProductDto> responseEntity=restTemplate.exchange("https://fakestoreapi.com/products/{id}",HttpMethod.PUT,product,GenericProductDto.class,id);
        //return responseEntity.getBody();
        return null;
    }

    @Override
    public GenericProductDto getProductById(Long id) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity=restTemplate.getForEntity(getProductRequestURL, FakeStoreProductDto.class,id);

        responseEntity.getStatusCode();

        FakeStoreProductDto fakeStoreProductDto=responseEntity.getBody();
        GenericProductDto genericProductDto=new GenericProductDto();
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());

        return genericProductDto;
    }
}
