package com.shun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "`order`")
public class Order {
    @Id
    private Integer id;
    private Integer userId;
    private String name;
    private String phone;
    private String addr;
    private String productName;
    private BigDecimal originalPrice;
    private BigDecimal snapPrice;
    private Integer count;
    private BigDecimal totalPrice;
    private Date createDate;
    private String status;
}
