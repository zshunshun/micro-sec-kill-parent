package com.shun.entity;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@HeadRowHeight(20)
@ContentRowHeight(15)
@ColumnWidth(30)
public class User {
  @Id
  @ExcelProperty("id")
  @ColumnWidth(5)
  private Integer id;
  @ExcelProperty("用户名")
  private String username;
  @ExcelProperty("密码")
  private String password;
  @ExcelProperty("盐值")
  private String salt;
  @ExcelProperty("昵称")
  @ColumnWidth(15)
  private String nickname;
  @ExcelProperty("电话")
  @ColumnWidth(15)
  private String phone;
  @ExcelProperty("性别")
  @ColumnWidth(10)
  private String sex;
  @ExcelProperty("头像")
  private String pic;
  @ExcelProperty("状态")
  @ColumnWidth(10)
  private String status;
  @ExcelProperty("创建日期")
  @DateTimeFormat("yyyy年MM月dd日")
  @ColumnWidth(15)
  private Date createDate;
  @ExcelProperty("最后登录日期")
  @DateTimeFormat("yyyy年MM月dd日")
  @ColumnWidth(15)
  private Date loginDate;
}
