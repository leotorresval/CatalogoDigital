package catalogodigital.service;

import catalogodigital.model.Product;
import catalogodigital.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks y las inyecciones de dependencias
    }

    @Test
    void testSave() {
        Product product = new Product();
        product.setName("Laptop");

        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.save(product);

        assertNotNull(savedProduct);
        assertEquals("Laptop", savedProduct.getName());
        verify(productRepository, times(1)).save(product); // Verifica que se llame al método save() del repositorio
    }

    @Test
    void testFindAll() {
        Product product1 = new Product();
        product1.setName("Laptop");

        Product product2 = new Product();
        product2.setName("Phone");

        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Laptop", result.get(0).getName());
        assertEquals("Phone", result.get(1).getName());
        verify(productRepository, times(1)).findAll(); // Verifica que se llame al método findAll() del repositorio
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Tablet");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> foundProduct = productService.findById(1L);

        assertTrue(foundProduct.isPresent());
        assertEquals("Tablet", foundProduct.get().getName());
        verify(productRepository, times(1)).findById(1L); // Verifica que se llame al método findById() del repositorio
    }

    @Test
    void testDelete() {
        Product product = new Product();
        product.setName("Camera");

        doNothing().when(productRepository).delete(product);

        productService.delete(product);

        verify(productRepository, times(1)).delete(product); // Verifica que se llame al método delete() del repositorio
    }

    @Test
    void testDeleteById() {
        doNothing().when(productRepository).deleteById(1L);

        productService.deleteById(1L);

        verify(productRepository, times(1)).deleteById(1L); // Verifica que se llame al método deleteById() del repositorio
    }

}