package catalogodigital.rest;

import catalogodigital.model.Product;
import catalogodigital.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ProductRest.class)
class ProductRestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");

        when(productService.save(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/products/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/products/1"))
                .andExpect(jsonPath("$.name").value("Laptop"));

        verify(productService, times(1)).save(any(Product.class));
    }

    @Test
    void testFindAllProducts() throws Exception {
        Product product1 = new Product();
        product1.setName("Laptop");

        Product product2 = new Product();
        product2.setName("Phone");

        List<Product> products = Arrays.asList(product1, product2);

        when(productService.findAll()).thenReturn(products);

        mockMvc.perform(get("/products/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Laptop"))
                .andExpect(jsonPath("$[1].name").value("Phone"));

        verify(productService, times(1)).findAll();
    }

    @Test
    void testGetProductById() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Tablet");

        when(productService.findById(1L)).thenReturn(Optional.of(product));

        mockMvc.perform(get("/products/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Tablet"));

        verify(productService, times(1)).findById(1L);
    }

    @Test
    void testDeleteProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);

        when(productService.findById(1L)).thenReturn(Optional.of(product));
        doNothing().when(productService).deleteById(1L);

        mockMvc.perform(delete("/products/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(productService, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteProductNotFound() throws Exception {
        when(productService.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/products/delete/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("false"));

        verify(productService, times(1)).findById(1L);
        verify(productService, never()).deleteById(1L);
    }
}
