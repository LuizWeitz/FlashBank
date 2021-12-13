package com.flashbank.repository;

import org.springframework.data.repository.CrudRepository;

import com.flashbank.model.Payment;

public interface paymentRepository extends CrudRepository<Payment, Long>{

}
