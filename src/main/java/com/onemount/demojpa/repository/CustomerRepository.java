package com.onemount.demojpa.repository;

import java.util.List;
import java.util.Optional;

import com.onemount.demojpa.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String Email);
    List<Customer> findByJob(String job);
}
