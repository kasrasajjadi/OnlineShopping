package com.mapsaProject.onlineShop.model;

import com.mapsaProject.onlineShop.dto.CartDto;
import com.mapsaProject.onlineShop.dto.CustomerDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tbl_invoice")
@Data
public class Invoice extends BaseEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "delivery_status")
    private boolean delivered;

//    @OneToOne
//    private Cart cart;
//
//    @OneToOne
//    private Customer customer;

    @Column(name = "cart_Id")
    private Long cartId;
    @Column(name = "customer_Id")
    private Long customerId;
}
