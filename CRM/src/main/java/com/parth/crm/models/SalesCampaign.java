package com.parth.crm.models;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
public class SalesCampaign {
    @Id
    @SequenceGenerator(
            name = "campaign_id_sequence",
            sequenceName = "campaign_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "campaign_id_sequence"
    )
    private Integer id;

    @Enumerated(EnumType.STRING)
    private CampaignTypes types;

    private String name;

    public SalesCampaign(Integer id,
                         CampaignTypes types,
                         String name) {
        this.id = id;
        this.types = types;
        this.name = name;
    }

    public SalesCampaign() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CampaignTypes getTypes() {
        return types;
    }

    public void setTypes(CampaignTypes types) {
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
        SalesCampaign that = (SalesCampaign) o;
        return Objects.equals(id, that.id) && types == that.types && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, types, name);
    }

    @Override
    public String toString() {
        return "SalesCampaign{" +
                "id=" + id +
                ", types=" + types +
                ", name='" + name + '\'' +
                '}';
    }
}
