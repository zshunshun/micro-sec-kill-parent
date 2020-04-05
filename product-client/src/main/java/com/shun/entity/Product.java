package com.shun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private String name;
    private BigDecimal price;
    private String info;
    private String cover;
    private Integer inventory;
    private Integer volume;
    private Date createDate;
    private ProductCategory productCategory;
}
