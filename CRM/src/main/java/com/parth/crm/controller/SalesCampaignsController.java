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
    public void addSalesCampaigns(@RequestBody NewSalesCampaigns request){
        SalesCampaign salesCampaign = new SalesCampaign();
        salesCampaign.setTypes(request.types());
        salesCampaign.setName(request.name());
        salesCampaignsRepository.save(salesCampaign);
    }
    @PutMapping("{salesId}")
    public ResponseEntity<SalesCampaign> updateSalesCampaigns(@RequestBody SalesCampaign request, @PathVariable("salesId") Integer id){
        Optional<SalesCampaign> salesCampaignOptional = salesCampaignsRepository.findById(id);
        try {
            if (salesCampaignOptional.isPresent()){
                SalesCampaign salesCampaign = salesCampaignOptional.get();
                salesCampaign.setName(request.getName());
                salesCampaign.setTypes(request.getTypes());
                return new ResponseEntity<>(salesCampaignsRepository.save(salesCampaign), HttpStatus.OK);
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
    public void deleteSalesCampaigns(@PathVariable("salesId") Integer id){
        salesCampaignsRepository.deleteById(id);
    }
}
