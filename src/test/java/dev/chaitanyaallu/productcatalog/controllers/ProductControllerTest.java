package dev.chaitanyaallu.productcatalog.controllers;


import dev.chaitanyaallu.productcatalog.dtos.GenericProductDto;
import dev.chaitanyaallu.productcatalog.exceptions.NotFoundException;
import dev.chaitanyaallu.productcatalog.services.ProductService;
import dev.chaitanyaallu.productcatalog.thirdpartyclients.productservices.fakestore.FakeStoreProductServiceClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;


    @Test
    void testOnePlusOneEqualsTrue(){
        System.out.println("True");
    }

    @Test //@DisplayName("2+2==4")
    void testTwoPlusTwoEqualsTrue() throws NotFoundException {
        //assert (4==2+2);
        assertEquals(4,2+2,"2+2 is not 4");
        //assertTrue(-1+-1==-2,"adding two negatives is not correct");

        //assertNull(fakeStoreProductServiceClient.getProductById(101L));

        //assertThrows(NotFoundException.class,()->fakeStoreProductServiceClient.getProductById(101L));

    }
    @Test
    void additionShouldBeCorrect(){
        assert 1+1==2;
        assert 3+4==7;
        assert 4+4==8;
    }
    @Test
    void returnsNullWhenProductDoesNotExist() throws NotFoundException {
        GenericProductDto genericProductDto = productController.getProductById("122");
        when(productService.getProductById(anyString())).thenReturn(null);
        //when(productService.getProductById("121")).thenCallRealMethod();
        assertNull(genericProductDto);
    }
    @Test
    void throwsExceptionWhenProductDoesNotExist() throws NotFoundException {
        //GenericProductDto genericProductDto = productController.getProductById("122");
        when(productService.getProductById(anyString())).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class,()->productController.getProductById("122"));
    }

    @Test
    void returnsProductWhenProductExists() throws NotFoundException {
        GenericProductDto genericProductDto1 = new GenericProductDto();
        when(productService.getProductById(anyString())).thenReturn(genericProductDto1);
        assertEquals(genericProductDto1,productController.getProductById("1"));
    }

    @Test
    void shouldReturnTitleWhenProductID1() throws NotFoundException {
        GenericProductDto genericProductDto1 = new GenericProductDto();
        genericProductDto1.setTitle("Title1");

        when(productService.getProductById("1")).thenReturn(genericProductDto1);

        GenericProductDto genericProductDto = productController.getProductById("1");

        assertEquals("Title1",genericProductDto.getTitle());
    }
}
