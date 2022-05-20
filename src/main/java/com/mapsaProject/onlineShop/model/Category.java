package com.mapsaProject.onlineShop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name ="tbl_category")
public class Category extends BaseEntity {
    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="image")
    private String image;
    @ManyToMany
      @JoinTable(name="category_brand" ,joinColumns = @JoinColumn(name="category_id") , inverseJoinColumns = @JoinColumn(name="brand_id"))
    List<Brand> brands;
}
