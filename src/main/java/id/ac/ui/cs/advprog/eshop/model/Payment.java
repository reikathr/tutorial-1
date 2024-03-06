package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Builder
@Getter
public class Payment {
    String id;
    String method;
    Map<String, String> paymentData;
    Order order;
    @Setter
    String status;

    public Payment(String id, Order order, String method, Map<String, String> paymentData){

    }

    public Payment(String id, Order order, String method, Map<String, String> paymentData, String status){
        
    }

}