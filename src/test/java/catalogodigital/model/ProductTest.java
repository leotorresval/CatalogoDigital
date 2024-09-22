package catalogodigital.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void getId() {
    }

    @Test
    void setId() {
    }

    @Test
    void getName() {
    }

    @Test
    void setName() {
    }

    @Test
    void testProductSettersAndGetters() {
        Product product = new Product();
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