package com.parth.crm.models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;


@Entity
public class LeadManagement {
    @Id
//    @SequenceGenerator(
//            name = "lead_id_sequence",
//            sequenceName = "lead_id_sequence"
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "lead_id_sequence"
//    )
    private String id;

    @Column(name = "lead_id", nullable = false)
    private String leadId;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private String make;
    private String model;
    private Integer year;

    public LeadManagement() {
    }

    public LeadManagement(String id, String leadId, Status status, Priority priority, String make, String model, Integer year) {
        this.id = id;
        this.leadId = leadId;
        this.status = status;
        this.priority = priority;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLeadId() {
        return leadId;
    }

    public void setLeadId(String leadId) {
        this.leadId = leadId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeadManagement that = (LeadManagement) o;
        return Objects.equals(id, that.id) && Objects.equals(leadId, that.leadId) && status == that.status && priority == that.priority && Objects.equals(make, that.make) && Objects.equals(model, that.model) && Objects.equals(year, that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, leadId, status, priority, make, model, year);
    }

    @Override
    public String toString() {
        return "LeadManagement{" +
                "id='" + id + '\'' +
                ", leadId='" + leadId + '\'' +
                ", status=" + status +
                ", priority=" + priority +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }
}
