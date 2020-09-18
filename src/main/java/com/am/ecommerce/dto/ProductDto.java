package com.am.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class ProductDto {
    private String productName;
    private String description;
    private Double cost;
    private Integer quantity;
    private String image;
    private String category;
}
