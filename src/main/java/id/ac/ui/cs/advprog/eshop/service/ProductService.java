package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public Product findByID(String id);
    public Product edit(Product editedProduct);
    public List<Product> findAll();
}
