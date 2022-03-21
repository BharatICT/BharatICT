package com.javatechie.jpa.repository;

import com.javatechie.jpa.dto.OrderCount;
import com.javatechie.jpa.dto.OrderResponse;
import com.javatechie.jpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

   @Query("SELECT new com.javatechie.jpa.dto.OrderResponse(c.name , p.productName) FROM Customer c JOIN Product p on c.id=p.cp_fk")
    public List<OrderResponse> getJoinInformation();
   
   @Query("select new com.javatechie.jpa.dto.OrderCount(COUNT(c.id),c.name) from Customer c join c.products p group by c.name")
   public List<OrderCount> getCountDetails();
}
