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
            String lead_id,
            String name,
            String email,
            Integer phone
    ){

    }

    @PostMapping
    public ResponseEntity<String> addCustomer(@RequestBody NewCustomer request){
        try {
            Optional<LeadManagement> leadManagementOptional = leadManagementRepository.findByLeadId(request.lead_id());
            Customer customer = new Customer();
            customer.setName(request.name());
            customer.setEmail(request.email());
            customer.setPhone(request.phone());
            if (leadManagementOptional.isPresent()){
                LeadManagement leadManagement = leadManagementOptional.get();
                customer.setLead_id(leadManagement.getId());
                customer.setLeadManagement(leadManagement);
                customerRepository.save(customer);
                return new ResponseEntity<>("Data Inserted Successfully!", HttpStatus.CREATED);
            }
            return new ResponseEntity<>("Could not able to find lead!", HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            // Handle the case where lead_id is not a valid integer
            System.err.println("Invalid lead_id: " + request.lead_id());
            // Optionally, throw an exception or return a response indicating the error
            //throw new IllegalArgumentException("Invalid lead_id: " + request.lead_id(), e);
            return new ResponseEntity<>("Failed to insert data!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{cus_id}")
    public ResponseEntity<String> updateCustomer(@RequestBody Customer request, @PathVariable("cus_id") Integer id){
        Optional<Customer> customerOptional = customerRepository.findById(id);
        //Cannot invoke "com.parth.crm.models.LeadManagement.getId()" because the return value of "com.parth.crm.models.Customer.getLeadManagement()" is null
        //Optional<LeadManagement> leadManagementOptional = leadManagementRepository.findById(request.getLeadManagement().getId());
        Optional<LeadManagement> leadManagementOptional = leadManagementRepository.findByLeadId(request.getLead_id());
        try {
            if (customerOptional.isPresent()){
                Customer customer = customerOptional.get();
                customer.setName(request.getName());
                customer.setEmail(request.getEmail());
                customer.setPhone(request.getPhone());
                if (leadManagementOptional.isPresent()){
                    LeadManagement leadManagement = leadManagementOptional.get();
                    customer.setLead_id(leadManagement.getId());
                    customer.setLeadManagement(leadManagement);
                    customerRepository.save(customer);
                }
                return new ResponseEntity<>("Data Updated Successfully!", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Failed to Update data!",HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }

        return new ResponseEntity<>("Not able to found Customer!", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("{cus_id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("cus_id") Integer id){

        try {
            // Check if the customer object exists
            boolean exists = customerRepository.existsById(id);
            if (!exists) {
                return new ResponseEntity<>("Customer not found!", HttpStatus.NOT_FOUND);
            }

            // Delete the Customer object
            customerRepository.deleteById(id);

            // Print a message to the console
            //System.out.println("Customer with ID " + id + " has been deleted.");

            // Return a success response
            return new ResponseEntity<>("Customer deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            // Print error message to the console
            System.err.println("Error deleting Customer with ID " + id + ": " + e.getMessage());

            // Return an error response
            return new ResponseEntity<>("Failed to delete Customer!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
