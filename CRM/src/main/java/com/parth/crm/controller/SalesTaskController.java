package com.parth.crm.controller;

import com.parth.crm.models.CampaignTypes;
import com.parth.crm.models.SalesCampaign;
import com.parth.crm.models.SalesTasks;
import com.parth.crm.models.TaskTypes;
import com.parth.crm.repository.SalesTaskRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@SpringBootApplication
@RequestMapping("api/v1/salesTask")
public class SalesTaskController {

    private final SalesTaskRepository salesTaskRepository;

    public SalesTaskController(SalesTaskRepository salesTaskRepository) {
        this.salesTaskRepository = salesTaskRepository;
    }

    @GetMapping
    public List<SalesTasks> getSalesTask(){
        return salesTaskRepository.findAll();
    }

    record NewSalesTask(
            String id,
            TaskTypes types,
            String name
    ){

    }
    @PostMapping
    public ResponseEntity<String> addSalesTask(@RequestBody NewSalesTask request){
        try {
            SalesTasks salesTasks = new SalesTasks();
            salesTasks.setName(request.name());
            salesTasks.setTypes(request.types());
            salesTaskRepository.save(salesTasks);


            return new ResponseEntity<>("Data Inserted Successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to insert data!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{salesId}")
    public ResponseEntity<String> updateSalesTask(@RequestBody SalesTasks request, @PathVariable("salesId") Integer id){
        Optional<SalesTasks> salesTasksOptional = salesTaskRepository.findById(id);
        try {
            if (salesTasksOptional.isPresent()){
                SalesTasks salesTasks = salesTasksOptional.get();
                salesTasks.setName(request.getName());
                salesTasks.setTypes(request.getTypes());
                salesTaskRepository.save(salesTasks);
                return new ResponseEntity<>("Data Updated Successfully!", HttpStatus.OK);
            }
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return new ResponseEntity<>("Failed to Update data!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Data is not valid!",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("{salesId}")
    public ResponseEntity<String> deleteSalesTask(@PathVariable("salesId") Integer id){

        try {
            // Check if the salesTask object exists
            boolean exists = salesTaskRepository.existsById(id);
            if (!exists) {
                return new ResponseEntity<>("salesTask not found!", HttpStatus.NOT_FOUND);
            }

            // Delete the salesTask object
            salesTaskRepository.deleteById(id);

            // Print a message to the console
            //System.out.println("salesTask with ID " + id + " has been deleted.");

            // Return a success response
            return new ResponseEntity<>("salesTask deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            // Print error message to the console
            System.err.println("Error deleting salesTask with ID " + id + ": " + e.getMessage());

            // Return an error response
            return new ResponseEntity<>("Failed to delete salesTask!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
