package com.mapsaProject.onlineShop.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="tbl_product_image")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProductImg extends BaseEntity{


    @Column(name = "product_img" ,nullable = true)
    private String productImages;

    @ManyToOne()
    @JoinColumn(name = "product")
    private Product product;




}
