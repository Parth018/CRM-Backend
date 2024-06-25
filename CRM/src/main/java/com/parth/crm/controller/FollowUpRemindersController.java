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
            Integer lead_id,
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
    public void addFollowUpReminders(@RequestBody NewFollowUpReminders request){

        Optional<LeadManagement> leadManagementOptional = leadManagementRepository.findById(request.lead_id());
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
            followUpReminders.setLeadManagement(leadManagement);
        }
        followUpRemindersRepository.save(followUpReminders);
    }

    @PutMapping("{followUpId}")
    public ResponseEntity<FollowUpReminders> updateFollowUpReminders(@RequestBody FollowUpReminders request, @PathVariable("followUpId") Integer id){
        Optional<FollowUpReminders> followUpRemindersOptional = followUpRemindersRepository.findById(id);
        Optional<LeadManagement> leadManagementOptional = leadManagementRepository.findById(request.getLeadManagement().getId());
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
                    followUpReminders.setLeadManagement(leadManagement);
                }
                return new ResponseEntity<>(followUpRemindersRepository.save(followUpReminders), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    @DeleteMapping("{followUpId}")
    public void deleteFollowUpReminders(@PathVariable("followUpId") Integer id){
        followUpRemindersRepository.deleteById(id);
    }

}
