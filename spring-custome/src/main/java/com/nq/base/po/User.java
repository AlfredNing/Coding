package com.nq.base.po;

import java.lang.reflect.Field;
import java.util.Date;
import lombok.Data;

/**
 * @author Alfred.Ning
 * @since 2024年01月18日 08:40:00
 */
@Data
public class User {

  private int id;
  private String username;
  private Date birthday;
  private String sex;
  private String address;
}