package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productRepository.create(product)).thenReturn(product);
        Product createdProduct = productService.create(product);

        assertEquals("Sampo Cap Bambang", createdProduct.getProductName());
        assertEquals(100, createdProduct.getProductQuantity());
    }

    @Test
    void testFindAll() {
        List<Product> productList = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);

        productList.add(product1);
        productList.add(product2);

        Iterator<Product> iterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(iterator);
        List<Product> productsFound = productService.findAll();

        assertEquals(productList.size(), productsFound.size());
        for (int i = 0; i < productList.size(); i++) {
            assertEquals(productList.get(i), productsFound.get(i));
        }
    }

    @Test
    void testFindByID() {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productRepository.findByID(productId)).thenReturn(product);
        Product productFound = productService.findByID(productId);
        assertEquals(product, productFound);
        verify(productRepository, times(1)).findByID(productId);
    }

    @Test
    void testEdit() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productRepository.edit(product)).thenReturn(product);
        Product editedProduct = productService.edit(product);

        assertEquals("Sampo Cap Bambang", editedProduct.getProductName());
        assertEquals(100, editedProduct.getProductQuantity());
    }
    @Test
    void testDelete() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        when(productRepository.delete(product.getProductId())).thenReturn(0);
        productService.delete(product.getProductId());

        Product deletedProduct = productRepository.findByID("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNull(deletedProduct);
        verify(productRepository, times(1)).delete(product.getProductId());
    }
}
