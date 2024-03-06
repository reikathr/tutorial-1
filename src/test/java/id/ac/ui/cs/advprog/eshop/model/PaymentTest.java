package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    Map<String, String> paymentData;

    List<Order> orders;

    List<Product> products;

    @BeforeEach
    void setUp(){
        this.products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductQuantity(2);
        product1.setProductName("Sampo Cap Bambang");
        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductQuantity(1);
        product2.setProductName("Sampo Cap Usep");
        this.products.add(product1);
        this.products.add(product2);

        this.orders = new ArrayList<>();

        Order order1 = new Order("bhd63cch-9b32-47bg-729c-dy28jd378191", products, 1708560000L, "Gojo Satoru");
        Order order2 = new Order("bdc571ac-2gd3-240c-28b2-728dsb654cdf", products, 1710200000L, "Geto Suguru");
        orders.add(order1);
        orders.add(order2);
    }

    @Test
    void testCreatePaymentVoucherSuccess(){
        paymentData.put("voucherCode", "ESHOP1234XYZ5678");
        Payment payment1 = new Payment("bhd63cch-9b32-47bg-729c-f12dfh68fh12", orders.get(1), "VOUCHER", paymentData);
        assertSame(this.orders.get(1), payment1.getOrder());
        assertEquals(paymentData, payment1.getPaymentData());
        assertEquals("bhd63cch-9b32-47bg-729c-f12dfh68fh12", payment1.getId());
        assertEquals("VOUCHER", payment1.getMethod());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentVoucher16CharFail(){
        paymentData.put("voucherCode", "ESHOP1234XYZ567");
        assertThrows(IllegalArgumentException.class, ()-> {new Payment("bhd63cch-9b32-47bg-729c-f12dfh68fh12", orders.get(1),
                "VOUCHER", paymentData);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentVoucherESHOPPrefixFail(){
        paymentData.put("voucherCode", "ESHOO1234XYZ5678");
        assertThrows(IllegalArgumentException.class, ()-> {new Payment("bhd63cch-9b32-47bg-729c-f12dfh68fh12", orders.get(1),
                "VOUCHER", paymentData);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentVoucherFail8Numerical(){
        paymentData.put("voucherCode", "ESHOP123WXYZ5678");
        assertThrows(IllegalArgumentException.class, ()-> {new Payment("bhd63cch-9b32-47bg-729c-f12dfh68fh12", orders.get(1),
                "VOUCHER", paymentData);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentBankSuccess(){
        paymentData.put("bankName", "BNI");
        paymentData.put("referenceCode", "1234567890");
        Payment payment1 = new Payment("bhd63cch-9b32-47bg-729c-f12dfh68fh12", orders.get(0), "BANK", paymentData);
        assertSame(this.orders.get(1), payment1.getOrder());
        assertEquals(paymentData, payment1.getPaymentData());
        assertEquals("bhd63cch-9b32-47bg-729c-f12dfh68fh12", payment1.getId());
        assertEquals("BANK", payment1.getMethod());
    }

    @Test
    void testCreatePaymentFailBankName(){
        paymentData.put("bankName", "");
        paymentData.put("referenceCode", "0");
        assertThrows(IllegalArgumentException.class, ()-> {new Payment("bhd63cch-9b32-47bg-729c-f12dfh68fh12", orders.get(1),
                "BANK", paymentData);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentFailReferenceCode(){
        paymentData.put("bankName", "aBank");
        paymentData.put("referenceCode", "");
        assertThrows(IllegalArgumentException.class, ()-> {new Payment("bhd63cch-9b32-47bg-729c-f12dfh68fh12", orders.get(1),
                "BANK", paymentData);
        });
        paymentData.clear();
    }
}