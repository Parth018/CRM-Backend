package com.parth.crm.models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Objects;


@Entity
public class LeadManagement {
    @Id
    @SequenceGenerator(
            name = "lead_id_sequence",
            sequenceName = "lead_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "lead_id_sequence"
    )
    private Integer id;
    private String lead_id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private String make;
    private String model;
    private Integer year;

    public LeadManagement(Integer id,
                          String leadId,
                          Status status,
                          Priority priority,
                          String make,
                          String model,
                          Integer year) {
        this.id = id;
        lead_id = leadId;
        this.status = status;
        this.priority = priority;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLead_id() {
        return lead_id;
    }

    public void setLead_id(String lead_id) {
        this.lead_id = lead_id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public LeadManagement() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeadManagement that = (LeadManagement) o;
        return Objects.equals(id, that.id) && Objects.equals(lead_id, that.lead_id) && status == that.status && priority == that.priority && Objects.equals(make, that.make) && Objects.equals(model, that.model) && Objects.equals(year, that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lead_id, status, priority, make, model, year);
    }

    @Override
    public String toString() {
        return "LeadManagement{" +
                "id=" + id +
                ", lead_id='" + lead_id + '\'' +
                ", status=" + status +
                ", priority=" + priority +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }
}
