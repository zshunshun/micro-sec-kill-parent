package com.shun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Category {
    @Id
    private Integer id;
    private String name;
    private Integer parentId;
}
