package com.am.ecommerce.controller;

import com.am.ecommerce.dto.ProductDto;
import com.am.ecommerce.model.ProductEntity;
import com.am.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    public List<ProductDto> getProducts() {
        List<ProductEntity> allProducts = productService.fetchAllProducts();

        List<ProductDto> products = new ArrayList<>();

        allProducts.forEach(product -> {
            ProductDto productDto = new ProductDto();
            productDto.setProductName(product.getProductName());
            productDto.setCategory(product.getCategory());
            productDto.setCost(product.getCost());
            productDto.setDescription(product.getDescription());
            productDto.setImage(product.getImage());
            productDto.setQuantity(product.getQuantity());
            products.add(productDto);
        });

        return products;
    }
}
