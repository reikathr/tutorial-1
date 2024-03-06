package id.ac.ui.cs.advprog.eshop.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Product> products;
    List<Payment> payments;
    Order order;

    @BeforeEach
    void setup() {
        paymentRepository = new PaymentRepository();

        payments = new ArrayList<>();
        products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("e45d7d21-fd29-4533-a569-abbe0819579a");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        Product product2 = new Product();
        product2.setProductId("8a76b99c-a0b3-46d2-a688-4c1831b21119");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        products.add(product2);

        order = new Order("dbd4aff4-9a7f-4603-92c2-eaf529271cc9", 
            products, 1708560000L, "Safira Sudrajat");

        Payment payment1 = new Payment(
            "bhd63cch-9b32-47bg-729c-f12dfh68fh12",
            order,
            "",
            null
        ),
        payment2 = new Payment(
            "bdc571ac-2gd3-240c-28b2-728dsb654cdd",
            order,
            "",
            null
        );
        payments.add(payment1);
        payments.add(payment2);

        Map<String, String> voucherPaymentData = new HashMap<>();
        voucherPaymentData.put("voucherCode", "ESHOP1234XYZ5678");
        Payment voucherPayment = new Payment(
            "c0f81308-9911-40c5-8da4-fa3194485aa1",
            order,
            PaymentMethod.VOUCHER.getValue(),
            voucherPaymentData
        );

        Map<String, String> bankPaymentData = new HashMap<>();
        bankPaymentData.put("bankName", "BNI");
        bankPaymentData.put("referenceCode", "1234567890");
        Payment bankPayment = new Payment(
            "d0f81308-9911-40c5-8da4-fa3194485aa1",
            order,
            PaymentMethod.BANK.getValue(),
            bankPaymentData
        );

        payments.add(voucherPayment);
        payments.add(bankPayment);
    }

    @Test
    void testSaveCreate() {
        Payment payment = payments.get(1);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getOrder(), findResult.getOrder());
        assertSame(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(PaymentStatus.WAITING_PAYMENT.getValue(), payment.getStatus());
    }

    @Test
    void testSaveCreateVoucher() {
        Payment payment = payments.get(2);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payments.get(2).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getOrder(), findResult.getOrder());
        assertSame(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(PaymentStatus.WAITING_PAYMENT.getValue(), payment.getStatus());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
    }

    @Test
    void testSaveCreateBank() {
        Payment payment = payments.get(3);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payments.get(3).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getOrder(), findResult.getOrder());
        assertSame(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(PaymentStatus.WAITING_PAYMENT.getValue(), payment.getStatus());
        assertEquals(PaymentMethod.BANK.getValue(), payment.getMethod());
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findResult.getId());
        assertSame(payments.get(1).getPaymentData(), findResult.getPaymentData());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
        assertEquals(payments.get(1).getOrder(), findResult.getOrder());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        assertNull(paymentRepository.findById("zczc"));
    }

    @Test
    void testGetAllPayments() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        List<Payment> allPayments = paymentRepository.getAllPayments();
        assertEquals(4, allPayments.size());
    }
}