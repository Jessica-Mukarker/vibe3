package com.amar.customerservice.Customer;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amar.customerservice.Orders.OrderClient;
import com.amar.customerservice.Payments.PaymentClient;
import com.amar.customerservice.Payments.PaymentClient.PaymentRequest;
import com.amar.customerservice.Payments.PaymentClient.PaymentResponse;






@RestController
public class CustomerController {

	  private final CustomerRepository repository;
	  private final CustomerModelAssemble assembler;
    private final OrderClient orderClient;
    private final PaymentClient paymentClient;
  public CustomerController(CustomerRepository repository, CustomerModelAssemble assembler, OrderClient orderClient,PaymentClient paymentClient) {
		this.repository = repository;
		this.assembler = assembler;
    this.orderClient = orderClient;
    this.paymentClient=paymentClient;
	}
  
  
// Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/customers")
public
  CollectionModel<EntityModel<Customer>> all() {

    List<EntityModel<Customer>> customers = repository.findAll().stream() //
        .map(assembler::toModel) //
        .collect(Collectors.toList());
    return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class).all()).withSelfRel());

  }
  // end::get-aggregate-root[]

  @PostMapping("/customers")
  Customer newCustomer(@RequestBody Customer newCustomer) {
    return repository.save(newCustomer);
  }

  // Single item
  
  @GetMapping("/customers/{id}")
public
  EntityModel<Customer> one(@PathVariable Integer id) {
    
	  Customer customer = repository.findById(id) //
		      .orElseThrow(() -> new CustomerNotFoundException(id));
    return assembler.toModel(customer);
  }

  @PutMapping("/customers/{id}")
  Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Integer id) {
    
    return repository.findById(id)
      .map(customer -> {
    	  customer.setEmailAddress(newCustomer.getEmailAddress());
          customer.setFirstName(newCustomer.getFirstName());
          customer.setLastName(newCustomer.getLastName());
          customer.setPhoneNumber(newCustomer.getPhoneNumber());
          customer.setUsername(newCustomer.getUsername());
          customer.setid(newCustomer.getid()); 	  
        return repository.save(customer);
      })
      .orElseGet(() -> {
        newCustomer.setid(id);
        return repository.save(newCustomer);
      });
  }

  @DeleteMapping("/customers/{id}")
  void deleteCustomer(@PathVariable Integer id) {
    repository.deleteById(id);
  }
    
    @PostMapping("customers/{customerId}/payments")
    public ResponseEntity<PaymentResponse> createPayment(
            @PathVariable Integer customerId,
            @RequestBody PaymentRequest paymentRequest
    ) {
        ResponseEntity<PaymentResponse> response = PaymentClient.createPayment(paymentRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @GetMapping("customers/{customerId}/payments")
    public ResponseEntity<List<PaymentResponse>> getCustomerPayments(@PathVariable Integer customerId) {
        ResponseEntity<List<PaymentResponse>> response = paymentClient.getAllPayments();
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @GetMapping("customers/{customerId}/payments/{paymentId}")
    public ResponseEntity<PaymentResponse> getPayment(
            @PathVariable Integer customerId,
            @PathVariable Integer paymentId
    ) {
        ResponseEntity<PaymentResponse> response = paymentClient.getPaymentById(paymentId);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @PutMapping("customers/{customerId}/payments/{paymentId}")
    public ResponseEntity<PaymentResponse> updatePayment(
            @PathVariable Integer customerId,
            @PathVariable Integer paymentId,
            @RequestBody PaymentRequest paymentRequest
    ) {

        ResponseEntity<PaymentResponse> response = paymentClient.updatePayment(paymentId, paymentRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @DeleteMapping("customers/{customerId}/payments/{paymentId}")
    public ResponseEntity<Void> deletePayment(
            @PathVariable Integer customerId,
            @PathVariable Integer paymentId
    ) {
        ResponseEntity<Void> response = paymentClient.deletePayment(paymentId);
        return ResponseEntity.status(response.getStatusCode()).build();
    }

    @GetMapping("customers/{customerId}/orders")
    public ResponseEntity<List<OrderClient.OrderResponse>> getCustomerOrders(@PathVariable Integer customerId) {
        ResponseEntity<List<OrderClient.OrderResponse>> response = orderClient.getAllOrders();
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @GetMapping("customers/{customerId}/orders/{orderId}")
    public ResponseEntity<OrderClient.OrderResponse> getOrder(
            @PathVariable Integer customerId,
            @PathVariable Integer orderId
    ) {
        ResponseEntity<OrderClient.OrderResponse> response = orderClient.getOrderById(orderId);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @PostMapping("customers/{customerId}/orders")
    public ResponseEntity<OrderClient.OrderResponse> createOrder(
            @PathVariable Integer customerId,
            @RequestBody OrderClient.OrderRequest orderRequest
    ) {
        ResponseEntity<OrderClient.OrderResponse> response = orderClient.createOrder(orderRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @DeleteMapping("customers/{customerId}/orders/{orderId}/cancel")
    public ResponseEntity<OrderClient.OrderResponse> cancelOrder(
            @PathVariable Integer customerId,
            @PathVariable Integer orderId
    ) {
        ResponseEntity<OrderClient.OrderResponse> response = orderClient.cancelOrder(orderId);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @PutMapping("customers/{customerId}/orders/{orderId}/complete")
    public ResponseEntity<OrderClient.OrderResponse> completeOrder(
            @PathVariable Integer customerId,
            @PathVariable Integer orderId
    ) {
        ResponseEntity<OrderClient.OrderResponse> response = orderClient.completeOrder(orderId);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @PutMapping("customers/{customerId}/orders/{orderId}")
    public ResponseEntity<OrderClient.OrderResponse> updateOrder(
            @PathVariable Integer customerId,
            @PathVariable Integer orderId,
            @RequestBody OrderClient.OrderRequest orderRequest
    ) {
        ResponseEntity<OrderClient.OrderResponse> response = orderClient.updateOrder(orderRequest, orderId);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
