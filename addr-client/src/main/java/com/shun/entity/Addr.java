package com.shun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Addr {
    private Integer id;
    private String name;
    private String addrInfo;
    private String phone;
    private Integer userId;
    private String defaultStatus;
}
