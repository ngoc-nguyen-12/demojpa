package com.onemount.demojpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Customer")
@Data
public class Customer {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "first_name")
  private String firstname;
  
  @Column(name = "last_name")
  private String lastname;
  private String email;
  private String mobile;
  private String job;
}
