package com.amar.paymentservice.payment;


import java.time.LocalDate;
import java.util.Objects;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "payment")
public class Payment{
  @Id
  @GeneratedValue(generator = "generator")
  @GenericGenerator(name = "generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
  private Integer payment_id;
  private Double amount;
      private PaymentType type; 
      
  
    Payment() {}
  
    public Payment(Double amount,PaymentType type){
      this.amount=amount;
      this.type=type;
      }
  
    
  
    public int getId() {
      return payment_id;
  }
  
  
  
  public Double getAmount() {
      return amount;
  }
  
  public void setAmount(Double amount) {
      this.amount = amount;
  }
  
 
  
  public PaymentType getType() {
      return type;
  }
  
  public void setType(PaymentType type) {
      this.type = type;
  }
  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Payment))
      return false;
    Payment payment = (Payment) o;
    return Objects.equals(this.payment_id, payment.payment_id) && Objects.equals(this.amount, payment.amount) && Objects.equals(this.type, payment.type);
       
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.payment_id, this.amount,this.type);
  }

  @Override
  public String toString() {
    return "Payment{" + "id=" + this.payment_id + ", amount=" + this.amount +'\'' +", type='"+this.type+'}';
  }


  
 
    
}
