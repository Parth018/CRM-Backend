package com.parth.crm.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class InventoryManagement {
    @Id
    @SequenceGenerator(
            name = "inventory_id_sequence",
            sequenceName = "inventory_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "inventory_id_sequence"
    )
    private Integer id;
    private String make;
    private String model;
    private Integer year;
    private Integer price;

    public InventoryManagement(Integer id,
                               String make,
                               String model,
                               Integer year,
                               Integer price) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public InventoryManagement() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryManagement that = (InventoryManagement) o;
        return Objects.equals(id, that.id) && Objects.equals(make, that.make) && Objects.equals(model, that.model) && Objects.equals(year, that.year) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, make, model, year, price);
    }

    @Override
    public String toString() {
        return "InventoryManagement{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", price=" + price +
                '}';
    }
}
