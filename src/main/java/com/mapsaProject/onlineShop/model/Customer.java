package com.mapsaProject.onlineShop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "tbl_customer")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Customer extends BaseEntity{

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "location")
    private String location;

    @Column(name = "mobile")
    private int mobile;

    @Column(name = "age")
    private int age;

}
