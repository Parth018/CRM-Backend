package com.parth.crm.controller;

import com.parth.crm.models.Customer;
import com.parth.crm.models.LeadManagement;
import com.parth.crm.repository.CustomerRepository;
import com.parth.crm.repository.LeadManagementRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@SpringBootApplication
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final LeadManagementRepository leadManagementRepository;

    public CustomerController(CustomerRepository customerRepository, LeadManagementRepository leadManagementRepository) {
        this.customerRepository = customerRepository;
        this.leadManagementRepository = leadManagementRepository;
    }

    @GetMapping
    public List<Customer> getCustomer(){
        return customerRepository.findAll();
    }

    record NewCustomer(
            String id,
            Integer lead_id,
            String name,
            String email,
            Integer phone
    ){

    }

    @PostMapping
    public void addCustomer(@RequestBody NewCustomer request){
        try {
            Optional<LeadManagement> leadManagementOptional = leadManagementRepository.findById(request.lead_id());
            Customer customer = new Customer();
            customer.setName(request.name());
            customer.setEmail(request.email());
            customer.setPhone(request.phone());
            if (leadManagementOptional.isPresent()){
                LeadManagement leadManagement = leadManagementOptional.get();
                customer.setLeadManagement(leadManagement);
                customerRepository.save(customer);
            }
        }
        catch (Exception e){
            // Handle the case where lead_id is not a valid integer
            System.err.println("Invalid lead_id: " + request.lead_id());
            // Optionally, throw an exception or return a response indicating the error
            //throw new IllegalArgumentException("Invalid lead_id: " + request.lead_id(), e);

        }
    }

    @PutMapping("{cus_id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer request, @PathVariable("cus_id") Integer id){
        Optional<Customer> customerOptional = customerRepository.findById(id);
        Optional<LeadManagement> leadManagementOptional = leadManagementRepository.findById(request.getLeadManagement().getId());
    try {
        if (customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            customer.setName(request.getName());
            customer.setEmail(request.getEmail());
            customer.setPhone(request.getPhone());
            if (leadManagementOptional.isPresent()){
                LeadManagement leadManagement = leadManagementOptional.get();
                customer.setLeadManagement(leadManagement);
                //customerRepository.save(customer);
            }
            return new ResponseEntity<>(customerRepository.save(customer), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    catch (Exception e){
        System.err.println(e.getMessage());
    }

        return null;
    }
    @DeleteMapping("{cus_id}")
    public void deleteCustomer(@PathVariable("cus_id") Integer id){
        customerRepository.deleteById(id);
    }

}
