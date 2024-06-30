package com.amar.customerservice.Payments;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "payment-service")
public interface PaymentClient {

    @GetMapping("/payments")
    ResponseEntity<List<PaymentResponse>> getAllPayments();

    @GetMapping("/payments/{id}")
    ResponseEntity<PaymentResponse> getPaymentById(@PathVariable("id") Integer id);

    @PostMapping("/payments")
    static
    ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createPayment'");
    }

    @PutMapping("/payments/{id}")
    ResponseEntity<PaymentResponse> updatePayment(@PathVariable("id") Integer id, @RequestBody PaymentRequest paymentRequest);

    @DeleteMapping("/payments/{id}")
    ResponseEntity<Void> deletePayment(@PathVariable("id") Integer id);


    public class PaymentRequest {
        private String orderId;
        private double amount;
        

        public String getOrderId() {
            return orderId;
        }
    
        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }
    
        public double getAmount() {
            return amount;
        }
    
        public void setAmount(double amount) {
            this.amount = amount;
        }
    }
    
    public class PaymentResponse {
        private Integer id;
        private String orderId;
        private double amount;
        private PaymentStatus status;
        
        public Integer getId() {
            return id;
        }
    
        public void setId(Integer id) {
            this.id = id;
        }
    
        public String getOrderId() {
            return orderId;
        }
    
        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }
    
        public double getAmount() {
            return amount;
        }
    
        public void setAmount(double amount) {
            this.amount = amount;
        }
    
        public PaymentStatus getStatus() {
            return status;
        }
    
        public void setStatus(PaymentStatus status) {
            this.status = status;
        }

    }
    
    public enum PaymentStatus {
        PENDING,
        COMPLETED,
        FAILED
    }
    
}
