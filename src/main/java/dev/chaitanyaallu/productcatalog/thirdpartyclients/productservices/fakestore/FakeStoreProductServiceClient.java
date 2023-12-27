package dev.chaitanyaallu.productcatalog.thirdpartyclients.productservices.fakestore;

import dev.chaitanyaallu.productcatalog.dtos.GenericProductDto;
import dev.chaitanyaallu.productcatalog.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
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
public class FakeStoreProductServiceClient {

//    @Value("${fakestore.api.url}")
//    private String fakeStoreApiUrl;
//
//    @Value("${fakestore.api.paths.product}")
//    private String fakeStoreProductsApiPath;
    private RestTemplateBuilder restTemplateBuilder;
    private String specificProductRequestURL;
    private String generalProductRequestURL;

    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder, @Value("${fakestore.api.url}") String fakeStoreApiUrl, @Value("${fakestore.api.paths.product}") String fakeStoreProductsApiPath) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.specificProductRequestURL=fakeStoreApiUrl+fakeStoreProductsApiPath+"/{id}";
        this.generalProductRequestURL=fakeStoreApiUrl+fakeStoreProductsApiPath;
    }
    public FakeStoreProductDto createProduct(GenericProductDto product){
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity=restTemplate.postForEntity(generalProductRequestURL,product, FakeStoreProductDto.class);
        return responseEntity.getBody();
    }
    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate=restTemplateBuilder.build();
        List<FakeStoreProductDto> listOfAllProducts=new ArrayList<>();
        ResponseEntity<FakeStoreProductDto[]> responseEntity=restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        for (FakeStoreProductDto fakeStoreProductDto:responseEntity.getBody()){
            listOfAllProducts.add(fakeStoreProductDto);
        }
        return listOfAllProducts;

        //ResponseEntity<List> responseEntity=restTemplate.getForEntity(generalProductRequestURL, List.class);
        //List<GenericProductDto> products=new ArrayList<>(Arrays.asList(responseEntity.getBody()));
        //return responseEntity.getBody();
    }
    public FakeStoreProductDto deleteProductById(Long id) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        //restTemplate.delete("https://fakestoreapi.com/products/{id}",id);
        //ResponseEntity<GenericProductDto> responseEntity=restTemplate.exchange("https://fakestoreapi.com/products/{id}", HttpMethod.DELETE,null,GenericProductDto.class,id);
        //return responseEntity.getBody();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> genericProductDto=restTemplate.execute(specificProductRequestURL, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        return genericProductDto.getBody();
    }
    public FakeStoreProductDto updateProductById(Long id, GenericProductDto product) {
        RestTemplate restTemplate=new RestTemplate();
        //restTemplate.put("https://fakestoreapi.com/products/{id}",null,id);
        //ResponseEntity<GenericProductDto> responseEntity=restTemplate.exchange("https://fakestoreapi.com/products/{id}",HttpMethod.PUT,product,GenericProductDto.class,id);
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity=restTemplate.execute(specificProductRequestURL, HttpMethod.PUT, requestCallback, responseExtractor, id);
        return responseEntity.getBody();
    }
    public FakeStoreProductDto getProductById(Long id) throws NotFoundException{
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity=restTemplate.getForEntity(specificProductRequestURL, FakeStoreProductDto.class,id);

        //responseEntity.getStatusCode();
        FakeStoreProductDto fakeStoreProductDto=responseEntity.getBody();

        if(fakeStoreProductDto==null){
            //throw new NotFoundException("Product with id: "+ id + " does not exist");
            return null;
        }

        return fakeStoreProductDto;
    }
}
