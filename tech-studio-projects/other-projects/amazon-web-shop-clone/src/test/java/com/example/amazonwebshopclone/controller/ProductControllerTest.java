package com.example.amazonwebshopclone.controller;

import com.example.amazonwebshopclone.model.Product;
import com.example.amazonwebshopclone.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testGetProductById() throws Exception {
        Product product = new Product(1L, "Product Name", "Description", 100.0, "imageUrl");
        // when(productService.getProductById(1L)).thenReturn(product);

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Product Name"))
                .andExpect(jsonPath("$.description").value("Description"));
    }

    @Test
    void testAddProduct() throws Exception {
        Product product = new Product(1L, "Product Name", "Description", 100.0, "imageUrl");
        when(productService.addProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/products")
                        .contentType("application/json")
                        .content("{\"name\":\"Product Name\",\"description\":\"Description\",\"price\":100.0,\"imageUrl\":\"imageUrl\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Product Name"));
    }
}
