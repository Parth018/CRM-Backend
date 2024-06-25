package com.parth.crm.controller;

import com.parth.crm.models.InventoryManagement;
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
    public void addInventoryManagement(@RequestBody NewInventoryManagement request){
        InventoryManagement inventoryManagement = new InventoryManagement();
        inventoryManagement.setMake(request.make());
        inventoryManagement.setModel(request.model());
        inventoryManagement.setYear(request.year());
        inventoryManagement.setPrice(request.price());
        inventoryManagementRepository.save(inventoryManagement);
    }

    @PutMapping("{inventoryId}")
    public ResponseEntity<InventoryManagement> updateInventoryManagement(@RequestBody InventoryManagement request, @PathVariable("inventoryId") Integer id){
        Optional<InventoryManagement> inventoryManagementOptional = inventoryManagementRepository.findById(id);
        try {
            if (inventoryManagementOptional.isPresent()){
                InventoryManagement inventoryManagement = inventoryManagementOptional.get();
                inventoryManagement.setMake(request.getMake());
                inventoryManagement.setModel(request.getModel());
                inventoryManagement.setYear(request.getYear());
                inventoryManagement.setPrice(request.getPrice());
                return new ResponseEntity<>(inventoryManagementRepository.save(inventoryManagement), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    @DeleteMapping("{inventoryId}")
    public void deleteInventoryManagement(@PathVariable("inventoryId") Integer id){
        inventoryManagementRepository.deleteById(id);
    }
}
