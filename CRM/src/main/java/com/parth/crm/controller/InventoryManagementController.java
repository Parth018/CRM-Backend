package com.parth.crm.controller;

import com.parth.crm.models.InventoryManagement;
import com.parth.crm.models.SalesCampaign;
import com.parth.crm.models.SalesTasks;
import com.parth.crm.models.TaskTypes;
import com.parth.crm.repository.InventoryManagementRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@SpringBootApplication
@RequestMapping("api/v1/InventoryManagement")
public class InventoryManagementController {

    private final InventoryManagementRepository inventoryManagementRepository;


    public InventoryManagementController(InventoryManagementRepository inventoryManagementRepository) {
        this.inventoryManagementRepository = inventoryManagementRepository;
    }

    @GetMapping
    public List<InventoryManagement> getInventoryManagement(){
        return inventoryManagementRepository.findAll();
    }

    record NewInventoryManagement(
            String id,
            String make,
            String model,
            Integer year,
            Integer price
    ){

    }
    @PostMapping
    public ResponseEntity<String> addInventoryManagement(@RequestBody NewInventoryManagement request){

        try {
            InventoryManagement inventoryManagement = new InventoryManagement();
            inventoryManagement.setMake(request.make());
            inventoryManagement.setModel(request.model());
            inventoryManagement.setYear(request.year());
            inventoryManagement.setPrice(request.price());
            inventoryManagementRepository.save(inventoryManagement);

            return new ResponseEntity<>("Data Inserted Successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to insert data!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{inventoryId}")
    public ResponseEntity<String> updateInventoryManagement(@RequestBody InventoryManagement request, @PathVariable("inventoryId") Integer id){
        Optional<InventoryManagement> inventoryManagementOptional = inventoryManagementRepository.findById(id);
        try {
            if (inventoryManagementOptional.isPresent()){
                InventoryManagement inventoryManagement = inventoryManagementOptional.get();
                inventoryManagement.setMake(request.getMake());
                inventoryManagement.setModel(request.getModel());
                inventoryManagement.setYear(request.getYear());
                inventoryManagement.setPrice(request.getPrice());
                inventoryManagementRepository.save(inventoryManagement);
                return new ResponseEntity<>("Data Updated Successfully!", HttpStatus.OK);
            }
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return new ResponseEntity<>("Failed to Update data!",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Data is not valid!",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("{inventoryId}")
    public ResponseEntity<String> deleteInventoryManagement(@PathVariable("inventoryId") Integer id){
        try {
            // Check if the inventoryManagement object exists
            boolean exists = inventoryManagementRepository.existsById(id);
            if (!exists) {
                return new ResponseEntity<>("inventoryManagement not found!", HttpStatus.NOT_FOUND);
            }

            // Delete the inventoryManagement object
            inventoryManagementRepository.deleteById(id);

            // Print a message to the console
            //System.out.println("inventoryManagement with ID " + id + " has been deleted.");

            // Return a success response
            return new ResponseEntity<>("inventoryManagement deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            // Print error message to the console
            System.err.println("Error deleting inventoryManagement with ID " + id + ": " + e.getMessage());

            // Return an error response
            return new ResponseEntity<>("Failed to delete inventoryManagement!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
