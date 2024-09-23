package catalogodigital.dto;

import catalogodigital.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductDtoTest {
    @Test
    void testProductSettersAndGetters() {
        ProductDto product = new ProductDto();
        product.setName("Laptop");
        product.setDescription("A powerful gaming laptop");
        product.setPrice(1200.99);
        product.setCost(900.50);
        product.setStock(10);
        product.setImagePath("/images/laptop.png");
        assertEquals("A powerful gaming laptop",product.getDescription());
        assertEquals(1200.99,product.getPrice());
        assertEquals(900.5,product.getCost());
        assertEquals(10,product.getStock());
        assertEquals("/images/laptop.png",product.getImagePath());
    }
}