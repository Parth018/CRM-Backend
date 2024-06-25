package com.parth.crm.models;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
public class SalesTasks {
    @Id
    @SequenceGenerator(
            name = "task_id_sequence",
            sequenceName = "task_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_id_sequence"
    )
    private Integer id;

    @Enumerated(EnumType.STRING)
    private TaskTypes types;

    private String name;

    public SalesTasks(Integer id,
                      TaskTypes types,
                      String name) {
        this.id = id;
        this.types = types;
        this.name = name;
    }

    public SalesTasks() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TaskTypes getTypes() {
        return types;
    }

    public void setTypes(TaskTypes types) {
        this.types = types;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesTasks that = (SalesTasks) o;
        return Objects.equals(id, that.id) && types == that.types && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, types, name);
    }

    @Override
    public String toString() {
        return "SalesTasks{" +
                "id=" + id +
                ", types=" + types +
                ", name='" + name + '\'' +
                '}';
    }
}
