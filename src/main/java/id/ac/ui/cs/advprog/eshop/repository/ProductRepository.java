package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        if (product.getProductId() == null) {
            UUID uuid = UUID.randomUUID();
            product.setProductId(uuid.toString());
        }
        productData.add(product);
        return product;
    }

    public Product findByID(String id) {
        for (Product products : productData) {
            if (products.getProductId().equals(id)) return products;
        }
        return null;
    }

    public Product edit(Product editedProduct) {
        String id = editedProduct.getProductId();
        Product formerProduct = findByID(id);
        formerProduct.setProductName(editedProduct.getProductName());
        formerProduct.setProductQuantity(editedProduct.getProductQuantity());
        return formerProduct;
    }

    public int delete(String id) {
        Product toBeDeleted = findByID(id);
        productData.remove(toBeDeleted);
        return 0;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }
}
