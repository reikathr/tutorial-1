package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Setter @Getter
public class Product {
    private String productId;
    private String productName;
    private int productQuantity;
}
