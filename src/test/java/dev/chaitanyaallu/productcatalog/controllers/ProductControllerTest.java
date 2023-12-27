package dev.chaitanyaallu.productcatalog.controllers;


import dev.chaitanyaallu.productcatalog.dtos.GenericProductDto;
import dev.chaitanyaallu.productcatalog.exceptions.NotFoundException;
import dev.chaitanyaallu.productcatalog.services.FakeStoreProductService;
import dev.chaitanyaallu.productcatalog.services.ProductService;
import dev.chaitanyaallu.productcatalog.thirdpartyclients.productservices.fakestore.FakeStoreProductServiceClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
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
    @Test
    //@DisplayName("2+2==4")
    void testTwoPlusTwoEqualsTrue() throws NotFoundException {
        assert (4==2+2);
        assertEquals(4,2+2,"2+2 is not 4");
        assertTrue(-1+-1==-2,"adding two negatives is not correct");

        //assertNull(fakeStoreProductServiceClient.getProductById(101L));

        assertThrows(NotFoundException.class,()->fakeStoreProductServiceClient.getProductById(101L));

    }
    @Test
    void additionShouldBeCorrect(){
        assert 1+1==2;
        assert 3+4==7;
        assert 4+4==8;
    }
    @Test
    void returnsNullWhenProductDoesnotExist() throws NotFoundException {
        GenericProductDto genericProductDto = productController.getProductById("1");
        when(productService.getProductById("121")).thenReturn(null);
        assertNull(genericProductDto);
    }
}
