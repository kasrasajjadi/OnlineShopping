package com.mapsaProject.onlineShop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_brand")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand extends BaseEntity {
  @Column(name = "brand_tile")
  private String title;
  @Column(name = "brand_des")
  private String description;
  @Column(name = "brand_img")
  private String image;
  @OneToMany(mappedBy = "brand")
  private List<Product> products;
  @ManyToMany
  @JoinTable(name = "category_brand", inverseJoinColumns = @JoinColumn(name = "category_id"), joinColumns =  @JoinColumn(name = "brand_id"))
  List<Category> categories;
}
