package com.mapsaProject.onlineShop.controller.formController;

import com.mapsaProject.onlineShop.mapper.ProductMapper;
import com.mapsaProject.onlineShop.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RequestMapping("/view/product")
public class ProductViewController {

    private final IProductService iProductService;
    private final ProductMapper productMapper;

    @GetMapping("/product-list")
    public String productList(Model model){
        model.addAttribute("productList",productMapper.toProductDtoList(iProductService.listAll()));
        return "ProductList";
    }
}
