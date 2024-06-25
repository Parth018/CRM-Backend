package com.parth.crm.controller;

import com.parth.crm.models.LeadManagement;
import com.parth.crm.models.Priority;
import com.parth.crm.models.Status;
import com.parth.crm.repository.LeadManagementRepository;
import com.parth.crm.viewModels.LeadManagementView;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@SpringBootApplication
@RequestMapping("api/v1/leadManagement")
public class LeadManagementController {

    private  final LeadManagementRepository leadManagementRepository;

    public LeadManagementController(LeadManagementRepository leadManagementRepository) {
        this.leadManagementRepository = leadManagementRepository;
    }

    @GetMapping
    public List<LeadManagement> getLeadManagement() {
        return leadManagementRepository.findAll();
    }

    record NewLeadManagement(
     String id,
     String lead_id,
     Status status,
     Priority priority,
     String make,
     String model,
     Integer year
    ){

    }
    @PostMapping
    public void addLeadManagement(@RequestBody NewLeadManagement request){
            LeadManagement leadManagement = new LeadManagement();
            leadManagement.setLead_id(request.lead_id());
            leadManagement.setStatus(request.status());
            leadManagement.setPriority(request.priority());
            leadManagement.setMake(request.make());
            leadManagement.setModel(request.model());
            leadManagement.setYear(request.year());
            leadManagementRepository.save(leadManagement);
    }

    @PutMapping("{leadId}")
    public ResponseEntity<LeadManagement> updateLeadManagement(@RequestBody LeadManagement request, @PathVariable("leadId") Integer id){
        Optional<LeadManagement> leadManagement = leadManagementRepository.findById(id);
        try {
            if (leadManagement.isPresent()){
                LeadManagement _leadManagement = leadManagement.get();
                _leadManagement.setLead_id(request.getLead_id());
                _leadManagement.setStatus(request.getStatus());
                _leadManagement.setPriority(request.getPriority());
                _leadManagement.setMake(request.getMake());
                _leadManagement.setModel(request.getModel());
                _leadManagement.setYear(request.getYear());
                return new ResponseEntity<>(leadManagementRepository.save(_leadManagement), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
       return null;
    }

    @DeleteMapping("{leadId}")
    public void deleteLeadManagement(@PathVariable("leadId") Integer id){
        leadManagementRepository.deleteById(id);
    }

}
