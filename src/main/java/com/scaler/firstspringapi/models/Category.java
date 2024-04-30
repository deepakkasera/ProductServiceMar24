package com.scaler.firstspringapi.models;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Setter@Getter
public class Category extends BaseModel {
    private String title;
//    @OneToMany(mappedBy = "category") // (fetch = FetchType.EAGER)
//    private List<Product> products;

}
