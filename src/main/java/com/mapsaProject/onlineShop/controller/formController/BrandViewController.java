package com.mapsaProject.onlineShop.controller.formController;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor (onConstructor_ = @Autowired)
@RequestMapping("/View/Brand")
public class BrandViewController {
    private final com.mapsaProject.onlineShop.service.IBrandService IBrandService;
    private  final com.mapsaProject.onlineShop.mapper.BrandMapper BrandMapper;


    @GetMapping("/brand-image-list")
    public String brandImgList(Model model) {
        model.addAttribute("brandList", BrandMapper.toBrandDtoList(IBrandService.listAll()));
        return "brandList";
    }
}
