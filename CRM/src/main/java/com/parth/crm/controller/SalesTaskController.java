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
    public void addSalesTask(@RequestBody NewSalesTask request){
        SalesTasks salesTasks = new SalesTasks();
        salesTasks.setName(request.name());
        salesTasks.setTypes(request.types());
        salesTaskRepository.save(salesTasks);
    }

    @PutMapping("{salesId}")
    public ResponseEntity<SalesTasks> updateSalesTask(@RequestBody SalesTasks request, @PathVariable("salesId") Integer id){
        Optional<SalesTasks> salesTasksOptional = salesTaskRepository.findById(id);
        try {
            if (salesTasksOptional.isPresent()){
                SalesTasks salesTasks = salesTasksOptional.get();
                salesTasks.setName(request.getName());
                salesTasks.setTypes(request.getTypes());
                return new ResponseEntity<>(salesTaskRepository.save(salesTasks), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    @DeleteMapping("{salesId}")
    public void deleteSalesTask(@PathVariable("salesId") Integer id){
        salesTaskRepository.deleteById(id);
    }
}
