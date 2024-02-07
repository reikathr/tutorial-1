package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
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

    public Iterator<Product> findAll() {
        return productData.iterator();
    }
}
