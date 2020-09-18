package com.am.ecommerce.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue
    @Column(name="order_id")
    private Integer orderId;

    @Column(name="order_status")
    private String orderStatus;

    @Column(name = "order_total")
    private Integer orderTotal;

    @Column(name="products")
    private String products;

    @Column(name = "email")
    private String email;

    @Column(name = "address_line1")
    private String addressLine1;

    @Column(name = "address_line2")
    private String addressLine2;

    @Column(name = "city")
    private String city;

    @Column(name = "phone")
    private Integer phone;
}
