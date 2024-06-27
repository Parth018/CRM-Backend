package com.parth.crm.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_id_sequence",
            sequenceName = "customer_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence"
    )
    private Integer id;

    private String name;
    private String email;
    private Integer phone;

    @Column(name = "lead_id", nullable = false)
    private String leadId;

    @OneToOne
    @JoinColumn(name = "lead_id", referencedColumnName = "id", insertable = false, updatable = false)
    private LeadManagement leadManagement;

    public Customer(Integer id, String name, String email, Integer phone, String lead_id, LeadManagement leadManagement) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.leadId = lead_id;
        this.leadManagement = leadManagement;
    }

    public Customer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(email, customer.email) && Objects.equals(phone, customer.phone) && Objects.equals(leadId, customer.leadId) && Objects.equals(leadManagement, customer.leadManagement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phone, leadId, leadManagement);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", lead_id='" + leadId + '\'' +
                ", leadManagement=" + leadManagement +
                '}';
    }
}
