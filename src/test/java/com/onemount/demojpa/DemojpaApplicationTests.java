package com.onemount.demojpa;

import com.onemount.demojpa.model.Customer;
import com.onemount.demojpa.repository.CustomerRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@Sql({"/Customer.sql"})
class DemojpaApplicationTests {

	@Autowired
	private CustomerRepository customerRepository;

	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Test
	@DisplayName("01. Get count customer")
	public void getCustomerCount(){
		assertThat(customerRepository.count()).isGreaterThan(10);
	}
	
	@Test
	@DisplayName("02. Find Customer id = 3 ")
	public void findByIdCustomer(){
		Optional<Customer> customer = customerRepository.findById(3L);
		if (customer.isPresent()) {
			assertThat(customer.get()).extracting("firstname").isEqualTo("Ty");
		  }  
		
	}
	
	@Test
	@DisplayName("03. Find Email Customer")
	public void findByEmailCustomer(){
		Optional<Customer> customer = customerRepository.findByEmail("tnormaville2@bandcamp.com");
		if (customer.isPresent()) {
			assertThat(customer.get()).extracting("firstname").isEqualTo("Ty");
		  }  
		
	}
	@Test
	@DisplayName("04. Find Job Customer")
	public void findByJobCustomer(){
		List<Customer> customer = customerRepository.findByJob("Dental Hygienist");
		if (customer.size()>0) {
			assertThat(customer.get(0)).extracting("job").isEqualTo("Dental Hygienist");
		  }  
		
	}

	@Test
	@DisplayName("05. Test with TestEntityManager")
	public void insertCustomer(){
		Customer customer21 = new Customer();
		customer21.setFirstname("Ngoc");
		customer21.setLastname("Nguyen");
		customer21.setEmail("ngoc@gmail.com");
		customer21.setMobile("0987654321");
		customer21.setJob("Dev Outsystems");
        Long id = (Long) testEntityManager.persistAndGetId(customer21);
        assertThat(testEntityManager.find(Customer.class,  id)).isEqualTo(customer21);
    }


	@Test
	@DisplayName("06. Delete Customer id = 4")
	public void deleteCustomer(){
		long count = customerRepository.count();
		Optional<Customer> customer = customerRepository.findById(4L);
		if (customer.isPresent()) {
			customerRepository.delete(customer.get());
			assertThat(customerRepository.count()).isEqualTo(count-1);
		  }  
	}

}
