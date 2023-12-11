package com.chaitanyaallu.productcatalog.thirdpartyclients.productservices.fakestore;

import com.chaitanyaallu.productcatalog.dtos.FakeStoreProductDto;
import com.chaitanyaallu.productcatalog.dtos.GenericProductDto;
import com.chaitanyaallu.productcatalog.exceptions.NotFoundException;
import com.chaitanyaallu.productcatalog.thirdpartyclients.productservices.ThirdPartyProductServiceClient;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper over Fake Store API
 */

@Component("fakeStoreProductServiceClient")
public class FakeStoreProductServiceClient implements ThirdPartyProductServiceClient {
    private RestTemplateBuilder restTemplateBuilder;
    private String specificProductRequestURL="https://fakestoreapi.com/products/{id}";
    private String generalProductRequestURL="https://fakestoreapi.com/products";

    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product){
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity=restTemplate.postForEntity(generalProductRequestURL,product, FakeStoreProductDto.class);
        return getGenericProductDto(responseEntity.getBody());
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        RestTemplate restTemplate=restTemplateBuilder.build();
        List<GenericProductDto> listOfAllProducts=new ArrayList<>();
        ResponseEntity<FakeStoreProductDto[]> responseEntity=restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        for (FakeStoreProductDto fakeStoreProductDto:responseEntity.getBody()){
            listOfAllProducts.add(getGenericProductDto(fakeStoreProductDto));
        }
        return listOfAllProducts;

        //ResponseEntity<List> responseEntity=restTemplate.getForEntity(generalProductRequestURL, List.class);
        //List<GenericProductDto> products=new ArrayList<>(Arrays.asList(responseEntity.getBody()));
        //return responseEntity.getBody();
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        //restTemplate.delete("https://fakestoreapi.com/products/{id}",id);
        //ResponseEntity<GenericProductDto> responseEntity=restTemplate.exchange("https://fakestoreapi.com/products/{id}", HttpMethod.DELETE,null,GenericProductDto.class,id);
        //return responseEntity.getBody();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> genericProductDto=restTemplate.execute(specificProductRequestURL, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        return getGenericProductDto(genericProductDto.getBody());
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto product) {
        RestTemplate restTemplate=new RestTemplate();
        //restTemplate.put("https://fakestoreapi.com/products/{id}",null,id);
        //ResponseEntity<GenericProductDto> responseEntity=restTemplate.exchange("https://fakestoreapi.com/products/{id}",HttpMethod.PUT,product,GenericProductDto.class,id);
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity=restTemplate.execute(specificProductRequestURL, HttpMethod.PUT, requestCallback, responseExtractor, id);
        return getGenericProductDto(responseEntity.getBody());
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException{
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity=restTemplate.getForEntity(specificProductRequestURL, FakeStoreProductDto.class,id);

        //responseEntity.getStatusCode();
        FakeStoreProductDto fakeStoreProductDto=responseEntity.getBody();

        if(fakeStoreProductDto==null){
            throw new NotFoundException("Product with id: "+ id + " does not exist");
        }

        return getGenericProductDto(fakeStoreProductDto);
    }

    private GenericProductDto getGenericProductDto(FakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto genericProductDto=new GenericProductDto();
        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        return genericProductDto;
    }
}
