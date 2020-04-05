package com.shun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {
    @Id
    private Integer id;
    private Integer productId;
    private Integer categoryId;
}
