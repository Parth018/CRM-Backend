package com.parth.crm.controller;

import com.parth.crm.models.FollowUpReminders;
import com.parth.crm.models.LeadManagement;
import com.parth.crm.models.SalesTasks;
import com.parth.crm.repository.FollowUpRemindersRepository;
import com.parth.crm.repository.LeadManagementRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@SpringBootApplication
@RequestMapping("api/v1/followUpReminders")
public class FollowUpRemindersController {

    private final FollowUpRemindersRepository followUpRemindersRepository;
    private final LeadManagementRepository leadManagementRepository;
    public FollowUpRemindersController(FollowUpRemindersRepository followUpRemindersRepository, LeadManagementRepository leadManagementRepository) {
        this.followUpRemindersRepository = followUpRemindersRepository;
        this.leadManagementRepository = leadManagementRepository;
    }

    @GetMapping
    public List<FollowUpReminders> getFollowUpReminders(){
        return followUpRemindersRepository.findAll();
    }

    record NewFollowUpReminders(
            String id,
            String reminder,
            String customer_name,
            String lead_id,
            String email,
            Integer phone,
            String make,
            String model,
            String color,
            Integer year,
            Integer quotation
    ){

    }
    @PostMapping
    public ResponseEntity<String> addFollowUpReminders(@RequestBody NewFollowUpReminders request){
        try {
            Optional<LeadManagement> leadManagementOptional = leadManagementRepository.findByLeadId(request.lead_id());
            FollowUpReminders followUpReminders = new FollowUpReminders();
            followUpReminders.setReminder(request.reminder());
            followUpReminders.setCustomer_name(request.customer_name());
            followUpReminders.setEmail(request.email());
            followUpReminders.setPhone(request.phone());
            followUpReminders.setMake(request.make());
            followUpReminders.setModel(request.model());
            followUpReminders.setColor(request.color());
            followUpReminders.setYear(request.year());
            followUpReminders.setQuotation(request.quotation());
            if (leadManagementOptional.isPresent()){
                LeadManagement leadManagement = leadManagementOptional.get();
                followUpReminders.setLead_id(leadManagement.getId());
                followUpReminders.setLeadManagement(leadManagement);
            }
            followUpRemindersRepository.save(followUpReminders);

            return new ResponseEntity<>("Data Inserted Successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to insert data!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{followUpId}")
    public ResponseEntity<String> updateFollowUpReminders(@RequestBody FollowUpReminders request, @PathVariable("followUpId") Integer id){
        Optional<FollowUpReminders> followUpRemindersOptional = followUpRemindersRepository.findById(id);
        Optional<LeadManagement> leadManagementOptional = leadManagementRepository.findByLeadId(request.getLead_id());
        try {
            if (followUpRemindersOptional.isPresent()){
                FollowUpReminders followUpReminders = followUpRemindersOptional.get();
                followUpReminders.setReminder(request.getReminder());
                followUpReminders.setCustomer_name(request.getCustomer_name());
                followUpReminders.setEmail(request.getEmail());
                followUpReminders.setPhone(request.getPhone());
                followUpReminders.setMake(request.getMake());
                followUpReminders.setModel(request.getModel());
                followUpReminders.setColor(request.getColor());
                followUpReminders.setYear(request.getYear());
                followUpReminders.setQuotation(request.getQuotation());
                if (leadManagementOptional.isPresent()){
                    LeadManagement leadManagement = leadManagementOptional.get();
                    followUpReminders.setLead_id(leadManagement.getId());
                    followUpReminders.setLeadManagement(leadManagement);
                    followUpRemindersRepository.save(followUpReminders);
                }
                return new ResponseEntity<>("Data Updated Successfully!", HttpStatus.OK);
            }
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return new ResponseEntity<>("Failed to Update data!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Data is not valid!",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("{followUpId}")
    public ResponseEntity<String> deleteFollowUpReminders(@PathVariable("followUpId") Integer id){
        try {
            // Check if the followUpReminders object exists
            boolean exists = followUpRemindersRepository.existsById(id);
            if (!exists) {
                return new ResponseEntity<>("followUpReminders not found!", HttpStatus.NOT_FOUND);
            }

            // Delete the followUpReminders object
            followUpRemindersRepository.deleteById(id);

            // Print a message to the console
            //System.out.println("followUpReminders with ID " + id + " has been deleted.");

            // Return a success response
            return new ResponseEntity<>("followUpReminders deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            // Print error message to the console
            System.err.println("Error deleting followUpReminders with ID " + id + ": " + e.getMessage());

            // Return an error response
            return new ResponseEntity<>("Failed to delete followUpReminders!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
