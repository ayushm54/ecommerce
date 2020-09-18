package com.am.ecommerce.resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/order")
public class OrderResource {

    @PostMapping("/place")
    public void placeOrder(){

    }
}
