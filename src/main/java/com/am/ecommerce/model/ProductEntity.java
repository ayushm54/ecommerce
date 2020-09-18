package com.am.ecommerce.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Integer productId;

    @Column(name="product_name")
    private String productName;

    @Column(name="description")
    private String description;

    @Column(name="cost")
    private Double cost;

    @Column(name="quantity")
    private Integer quantity;

    @Column(name="image")
    private String image;

    @Column(name="category")
    private String category;
}
