package com.parth.crm.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class FollowUpReminders {
    @Id
    @SequenceGenerator(
            name = "reminders_id_sequence",
            sequenceName = "reminders_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reminders_id_sequence"
    )
    private Integer id;
    private String reminder;
    private String customer_name;

    @Column(name = "lead_id", nullable = false)
    private String leadId;

    @OneToOne
    @JoinColumn(name = "lead_id", referencedColumnName = "id", insertable = false, updatable = false)
    private LeadManagement leadManagement;

    private String email;
    private Integer phone;
    private String make;
    private String model;
    private String color;
    private Integer year;
    private Integer quotation;

    public FollowUpReminders() {
    }

    public FollowUpReminders(Integer id, String reminder, String customer_name, String lead_id, LeadManagement leadManagement, String email, Integer phone, String make, String model, String color, Integer year, Integer quotation) {
        this.id = id;
        this.reminder = reminder;
        this.customer_name = customer_name;
        this.leadId = lead_id;
        this.leadManagement = leadManagement;
        this.email = email;
        this.phone = phone;
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.quotation = quotation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getLead_id() {
        return leadId;
    }

    public void setLead_id(String lead_id) {
        this.leadId = lead_id;
    }

    public LeadManagement getLeadManagement() {
        return leadManagement;
    }

    public void setLeadManagement(LeadManagement leadManagement) {
        this.leadManagement = leadManagement;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getQuotation() {
        return quotation;
    }

    public void setQuotation(Integer quotation) {
        this.quotation = quotation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowUpReminders that = (FollowUpReminders) o;
        return Objects.equals(id, that.id) && Objects.equals(reminder, that.reminder) && Objects.equals(customer_name, that.customer_name) && Objects.equals(leadId, that.leadId) && Objects.equals(leadManagement, that.leadManagement) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone) && Objects.equals(make, that.make) && Objects.equals(model, that.model) && Objects.equals(color, that.color) && Objects.equals(year, that.year) && Objects.equals(quotation, that.quotation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reminder, customer_name, leadId, leadManagement, email, phone, make, model, color, year, quotation);
    }

    @Override
    public String toString() {
        return "FollowUpReminders{" +
                "id=" + id +
                ", reminder='" + reminder + '\'' +
                ", customer_name='" + customer_name + '\'' +
                ", lead_id='" + leadId + '\'' +
                ", leadManagement=" + leadManagement +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", year=" + year +
                ", quotation=" + quotation +
                '}';
    }
}
