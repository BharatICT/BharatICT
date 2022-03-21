package com.javatechie.jpa.controller;

import com.javatechie.jpa.dto.OrderCount;
import com.javatechie.jpa.dto.OrderRequest;
import com.javatechie.jpa.dto.OrderResponse;
import com.javatechie.jpa.entity.Customer;
import com.javatechie.jpa.repository.CustomerRepository;
import com.javatechie.jpa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/placeOrder")
    public Customer placeOrder(@RequestBody OrderRequest request){
       return customerRepository.save(request.getCustomer());
    }
    
    @PostMapping("/updateOrder")
    public Customer updateOrder(@RequestBody OrderRequest request) throws Exception
    {
    	Customer updateBo= customerRepository.findById(request.getCustomer().getId())
    							 .map(
    									 customer -> {
										 			customer.setEmail(request.getCustomer().getEmail());
										 			customer.setGender(request.getCustomer().getGender());
										 			customer.setName(request.getCustomer().getName());
										 			customer.setProducts(request.getCustomer().getProducts());
										 			return customerRepository.save(customer);
    									 }
    								).orElseThrow(()-> new Exception());
    	
    	return updateBo;
    }
    
    @GetMapping("/findAllOrders")
    public List<Customer> findAllOrders(){
        return customerRepository.findAll();
    }

    @GetMapping("/getInfo")
    public List<OrderResponse> getJoinInformation(){
        return customerRepository.getJoinInformation();
    }
    
    @GetMapping("/getCountDetails")
    public List<OrderCount> getCountDetails(){
        return customerRepository.getCountDetails();
    }
    
    @PostMapping("/deleteOrder/{orderId}")
    public String deleteOrder(@PathVariable("orderId")int orderId)
    {
    	customerRepository.deleteById(orderId);
    	return "successfully deleted";
    }
}
