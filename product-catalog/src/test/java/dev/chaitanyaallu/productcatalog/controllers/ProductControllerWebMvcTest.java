package dev.chaitanyaallu.productcatalog.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.chaitanyaallu.productcatalog.dtos.GenericProductDto;
import dev.chaitanyaallu.productcatalog.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


// Not initializes the unnecessary beans, only initializes the beans required for the product controller.
@WebMvcTest(ProductController.class)
public class ProductControllerWebMvcTest {
    @Autowired
    private ProductController productController;
    @MockBean
    private ProductService productService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllProductsReturnsEmptyList() throws Exception {
        when(productService.getAllProducts())
                .thenReturn(new ArrayList<>());

        mockMvc.perform(get("/products"))
                .andExpect(status().is(204))
                .andExpect(content().string("[]")
                );
    }
    @Test
    void returnListOfProductsWhenProductsExist() throws Exception {
        List<GenericProductDto> genericProductDtos=new ArrayList<>(List.of(new GenericProductDto()));
        when(productService.getAllProducts())
                .thenReturn(genericProductDtos);

        mockMvc.perform(get("/products"))
                .andExpect(status().is(200))
                .andExpect(content().json(objectMapper.writeValueAsString(genericProductDtos)))
                .andExpect(jsonPath("$[0].id").exists());
    }

    @Test
    void createProductShouldCreateProduct() throws Exception {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setTitle("iPhone 15");
        genericProductDto.setDescription("Best in the Industry *");
        genericProductDto.setCategory("Apple Devices");
        genericProductDto.setPrice(79999);

        GenericProductDto genericProductDtoExpected = new GenericProductDto();
        genericProductDtoExpected.setId("1");
        genericProductDtoExpected.setTitle("iPhone 15");
        genericProductDtoExpected.setDescription("Best in the Industry *");
        genericProductDtoExpected.setCategory("Apple Devices");
        genericProductDtoExpected.setPrice(79999);

        when(productService.createProduct(any()))
                .thenReturn(genericProductDtoExpected);

        mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(genericProductDto))
                )
                .andExpect(content().string(objectMapper.writeValueAsString(genericProductDtoExpected)));
    }
}
