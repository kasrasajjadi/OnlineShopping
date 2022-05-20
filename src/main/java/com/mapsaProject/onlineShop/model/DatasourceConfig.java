package com.mapsaProject.onlineShop.model;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {
    @Bean
    public DataSource dataSource(){
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/sq")
                .username("root")
                .password("123456")
                .build();
    }

    //    @Override
//    public <T> List<List<T>> getPages(Collection<T> c, Integer pageSize) {
//        if (c == null)
//            return Collections.emptyList();
//        List<T> list = new ArrayList<T>(c);
//        if (pageSize == null || pageSize <= 0 || pageSize > list.size())
//            pageSize = list.size();
//        int numPages = (int) Math.ceil((double)list.size() / (double)pageSize);
//        List<List<T>> pages = new ArrayList<List<T>>(numPages);
//        for (int pageNum = 0; pageNum < numPages;)
//            pages.add(list.subList(pageNum * pageSize, Math.min(++pageNum * pageSize, list.size())));
//        return pages;
//    }

    // Predicate<Product> byPrice = product -> product.getPrice() >= min && product.getPrice() <= max;
    // List<Product> products = productService.listAll().stream().filter(byPrice).collect(Collectors.toList());
    // productService.getListPagination(page,size);

    //<T> List<List<T>> getPages(Collection<T> c, Integer pageSize);
}
