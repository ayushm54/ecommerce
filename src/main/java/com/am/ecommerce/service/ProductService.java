package com.am.ecommerce.service;

import com.am.ecommerce.dao.ProductDao;
import com.am.ecommerce.model.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public List<ProductEntity> fetchAllProducts(){
        return productDao.findAll();
    }
}
