package com.amar.orderservice.Order;

class OrderNotFoundException extends RuntimeException {

    OrderNotFoundException(Integer id) {
      super("Could not find order " + id);
    }
  }