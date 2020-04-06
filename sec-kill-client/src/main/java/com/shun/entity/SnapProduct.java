package com.shun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SnapProduct {
    @Id
    private Integer id;
    private Integer productId;
    private Integer count;
    private BigDecimal price;
    private Date createDate;
    private Date snapDate;
    private Product product;
}
