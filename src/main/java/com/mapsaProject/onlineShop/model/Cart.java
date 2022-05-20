package com.mapsaProject.onlineShop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "tbl_cart")
public class Cart extends BaseEntity{

    @Column(name = "total_price")
    private Double totalPrice;
    @Column(name = "number_ofproduct")
    private Long numberOfProduct;
    @OneToMany(mappedBy = "cart")
    private List<Product> productList;

}
