package com.amar.customerservice.Customer;

class CustomerNotFoundException extends RuntimeException {

    CustomerNotFoundException(Integer id) {
      super("Could not find customer " + id);
    }
  }