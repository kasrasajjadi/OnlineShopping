package com.mapsaProject.onlineShop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_product")
public class Product extends BaseEntity{
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Double price;
    @ManyToOne
    @JoinColumn(name = "brand")
    private Brand brand;
    @OneToMany(mappedBy = "product")
    private List<ProductImg> productImg;
    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;
}
