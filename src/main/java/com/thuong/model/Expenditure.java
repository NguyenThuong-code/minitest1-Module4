package com.thuong.model;

import javax.persistence.*;

@Entity
@Table(name="expenditures")
public class Expenditure {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String listSpend;
    private String image;

    public Expenditure() {
    }

    public Expenditure(Long id, String name, Double price, String description, String listSpend, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.listSpend = listSpend;
        this.image = image;
    }
    @Override
    public String toString() {
        return String.format("Expenditure[id=%d, name='%s', price='%s', description='%s', listSpend='%s', image='%s']", id, name,price,description,listSpend,image);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getListSpend() {
        return listSpend;
    }

    public void setListSpend(String listSpend) {
        this.listSpend = listSpend;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
