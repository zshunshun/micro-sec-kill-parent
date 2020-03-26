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
public class Manager {
  @Id
  private Integer id;
  private String username;
  private String password;
  private String salt;

}
