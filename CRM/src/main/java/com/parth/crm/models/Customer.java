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

    @OneToOne
    @JoinColumn(name = "lead_id")
    private LeadManagement leadManagement;


    public Customer(Integer id,
                    String name,
                    String email,
                    Integer phone,
                    LeadManagement leadManagement) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
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
        return Objects.equals(id, customer.id) &&  Objects.equals(name, customer.name) && Objects.equals(email, customer.email) && Objects.equals(phone, customer.phone) && Objects.equals(leadManagement, customer.leadManagement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phone, leadManagement);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", leadManagement=" + leadManagement +
                '}';
    }
}
