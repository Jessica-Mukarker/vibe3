package com.amar.orderservice.Order;

import org.springframework.data.jpa.repository.JpaRepository;

interface OrderRepository extends JpaRepository<Orders, Integer> {
}

