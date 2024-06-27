package com.parth.crm.controller;

import com.parth.crm.models.CampaignTypes;
import com.parth.crm.models.Customer;
import com.parth.crm.models.LeadManagement;
import com.parth.crm.models.SalesCampaign;
import com.parth.crm.repository.SalesCampaignsRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@SpringBootApplication
@RequestMapping("api/v1/salesCampaigns")
public class SalesCampaignsController {


    private final SalesCampaignsRepository salesCampaignsRepository;

    public SalesCampaignsController(SalesCampaignsRepository salesCampaignsRepository) {
        this.salesCampaignsRepository = salesCampaignsRepository;
    }

    @GetMapping
    public List<SalesCampaign> getSalesCampaigns(){
        return salesCampaignsRepository.findAll();
    }

    record NewSalesCampaigns(
            String id,
            CampaignTypes types,
            String name
    ){

    }
    @PostMapping
    public ResponseEntity<String> addSalesCampaigns(@RequestBody NewSalesCampaigns request){
        try {
            SalesCampaign salesCampaign = new SalesCampaign();
            salesCampaign.setTypes(request.types());
            salesCampaign.setName(request.name());
            salesCampaignsRepository.save(salesCampaign);

            return new ResponseEntity<>("Data Inserted Successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to insert data!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{salesId}")
    public ResponseEntity<String> updateSalesCampaigns(@RequestBody SalesCampaign request, @PathVariable("salesId") Integer id){
        Optional<SalesCampaign> salesCampaignOptional = salesCampaignsRepository.findById(id);
        try {
            if (salesCampaignOptional.isPresent()){
                SalesCampaign salesCampaign = salesCampaignOptional.get();
                salesCampaign.setName(request.getName());
                salesCampaign.setTypes(request.getTypes());
                salesCampaignsRepository.save(salesCampaign);
                return new ResponseEntity<>("Data Updated Successfully!", HttpStatus.OK);
            }
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return new ResponseEntity<>("Failed to Update data!",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Data is not valid!",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("{salesId}")
    public ResponseEntity<String> deleteSalesCampaigns(@PathVariable("salesId") Integer id){

        try {
            // Check if the salesCampaigns object exists
            boolean exists = salesCampaignsRepository.existsById(id);
            if (!exists) {
                return new ResponseEntity<>("salesCampaigns not found!", HttpStatus.NOT_FOUND);
            }

            // Delete the salesCampaigns object
            salesCampaignsRepository.deleteById(id);

            // Print a message to the console
            //System.out.println("salesCampaigns with ID " + id + " has been deleted.");

            // Return a success response
            return new ResponseEntity<>("salesCampaigns deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            // Print error message to the console
            System.err.println("Error deleting salesCampaigns with ID " + id + ": " + e.getMessage());

            // Return an error response
            return new ResponseEntity<>("Failed to delete salesCampaigns!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
