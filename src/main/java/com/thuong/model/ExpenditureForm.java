package com.thuong.model;

import org.springframework.web.multipart.MultipartFile;

public class ExpenditureForm {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String listSpend;
    private MultipartFile image;

    public ExpenditureForm() {
    }

    public ExpenditureForm(Long id, String name, Double price, String description, String listSpend, MultipartFile image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.listSpend = listSpend;
        this.image = image;
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
