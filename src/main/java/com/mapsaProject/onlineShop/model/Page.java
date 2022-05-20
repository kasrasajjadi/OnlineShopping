package com.mapsaProject.onlineShop.model;

import lombok.Data;

import java.util.List;
@Data
public class Page {
    private long totalPage;
    private long currentPage;
    private int pageSize;
    private long totalList;
    private List list;

}
