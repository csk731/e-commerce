package dev.chaitanyaallu.productcatalog.thirdpartyclients.productservices.fakestore;

import dev.chaitanyaallu.productcatalog.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FakeStoreProductServiceClientTest {
//    private RestTemplateBuilder restTemplateBuilder;
//    @Test
//    public FakeStoreProductDto testGetProductById(Long id){
//        RestTemplate restTemplate=restTemplateBuilder.build();
//        ResponseEntity<FakeStoreProductDto> responseEntity=restTemplate.getForEntity(,FakeStoreProductDto.class);
//        assertNull();
//    }
}