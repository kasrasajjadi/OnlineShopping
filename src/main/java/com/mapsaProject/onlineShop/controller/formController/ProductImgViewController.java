package com.mapsaProject.onlineShop.controller.formController;

import com.mapsaProject.onlineShop.mapper.ProductImgMapper;
import com.mapsaProject.onlineShop.mapper.ProductMapper;
import com.mapsaProject.onlineShop.service.IProductImgService;
import com.mapsaProject.onlineShop.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RequestMapping("/view/product-image")
public class ProductImgViewController {

    private final IProductImgService IProductImgService;
    private final ProductImgMapper productImgMapper;


    //read
    @GetMapping("/product-image-list")
    public String productImgList(Model model) {
        model.addAttribute("productImgList", productImgMapper.toProductImgDtoList(IProductImgService.listAll()));
        return "ProductImgList";
    }

}
