package com.mapsaProject.onlineShop.model;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  Long id;
  @Column(name="deleted")
  boolean deleted;
  @Column(name="created_at")
  private Date createdAt;
  @Column(name="updated_at")
  private Date updatedAt;


  @PreUpdate
  private void setUpdateAt(){
    this.setUpdatedAt(new Date());
  }

  @PrePersist
  private void setCreatedAt(){
    this.setCreatedAt(new Date());
  }


}
