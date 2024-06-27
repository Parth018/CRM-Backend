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
    public ResponseEntity<String> addLeadManagement(@RequestBody NewLeadManagement request){
        try {
            LeadManagement leadManagement = new LeadManagement();
            leadManagement.setLeadId(request.lead_id());
            leadManagement.setStatus(request.status());
            leadManagement.setPriority(request.priority());
            leadManagement.setMake(request.make());
            leadManagement.setModel(request.model());
            leadManagement.setYear(request.year());
            leadManagementRepository.save(leadManagement);

            return new ResponseEntity<>("Data Inserted Successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to insert data!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{leadId}")
    public ResponseEntity<String> updateLeadManagement(@RequestBody LeadManagement request, @PathVariable("leadId") String id){
        Optional<LeadManagement> leadManagement = leadManagementRepository.findById(id);
        try {
            if (leadManagement.isPresent()){
                LeadManagement _leadManagement = leadManagement.get();
                _leadManagement.setLeadId(request.getLeadId());
                _leadManagement.setStatus(request.getStatus());
                _leadManagement.setPriority(request.getPriority());
                _leadManagement.setMake(request.getMake());
                _leadManagement.setModel(request.getModel());
                _leadManagement.setYear(request.getYear());
                leadManagementRepository.save(_leadManagement);
                return new ResponseEntity<>("Data Updated Successfully!", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Failed to Update data!",HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            System.err.println(e.getMessage());

        }
        return new ResponseEntity<>("Not able to found leadManagement!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{leadId}")
    public ResponseEntity<String> deleteLeadManagement(@PathVariable("leadId") String id){

        try {
            // Check if the LeadManagement object exists
            boolean exists = leadManagementRepository.existsById(id);
            if (!exists) {
                return new ResponseEntity<>("LeadManagement not found!", HttpStatus.NOT_FOUND);
            }

            // Delete the LeadManagement object
            leadManagementRepository.deleteById(id);

            // Print a message to the console
            //System.out.println("LeadManagement with ID " + id + " has been deleted.");

            // Return a success response
            return new ResponseEntity<>("LeadManagement deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            // Print error message to the console
            System.err.println("Error deleting LeadManagement with ID " + id + ": " + e.getMessage());

            // Return an error response
            return new ResponseEntity<>("Failed to delete LeadManagement!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
