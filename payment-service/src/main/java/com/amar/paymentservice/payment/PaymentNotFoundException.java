package com.amar.paymentservice.payment;

public class PaymentNotFoundException extends RuntimeException {

    public PaymentNotFoundException(Integer id) {
     super("Could not find Payment " + id);
   }
 }
