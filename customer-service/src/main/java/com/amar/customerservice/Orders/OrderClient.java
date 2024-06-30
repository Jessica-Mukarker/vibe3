package com.amar.customerservice.Orders;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "order-service")
public interface OrderClient {

    @GetMapping("/orders")
    ResponseEntity<List<OrderResponse>> getAllOrders();

    @GetMapping("/orders/{orderId}")
    ResponseEntity<OrderResponse> getOrderById(@PathVariable("orderId") Integer orderId);

    @PostMapping("/orders")
    ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest order);

    @DeleteMapping("/orders/{orderId}/cancel")
    ResponseEntity<OrderResponse> cancelOrder(@PathVariable("orderId") Integer orderId);

    @PutMapping("/orders/{orderId}/complete")
    ResponseEntity<OrderResponse> completeOrder(@PathVariable("orderId") Integer orderId);

    @PutMapping("/orders/{orderId}")
    ResponseEntity<OrderResponse> updateOrder(@RequestBody OrderRequest order, @PathVariable("orderId") Integer orderId);

    public class OrderResponse {
        private Integer id;
        private String description;
        private Status status;
        private Integer customerId;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public Integer getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Integer customerId) {
            this.customerId = customerId;
        }

        public enum Status {
            IN_PROGRESS,
            COMPLETED,
            CANCELLED
        }
    }

    public class OrderRequest {
        private String description;
        private Integer customerId;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Integer customerId) {
            this.customerId = customerId;
        }
    }
}
