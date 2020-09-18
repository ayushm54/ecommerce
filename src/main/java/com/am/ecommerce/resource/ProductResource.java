package com.am.ecommerce.resource;

import com.am.ecommerce.controller.ProductController;
import com.am.ecommerce.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductResource {

    @Autowired
    ProductController productController;

    @GetMapping("/load")
    public ResponseEntity<List<ProductDto>> getProducts(){
        return ResponseEntity.ok(productController.getProducts());
    }
}
