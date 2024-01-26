package com.nq.spring.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * bean中定义属性封装
 * @author Alfred.Ning
 * @since 2024年01月20日 13:30:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyValue {
  private String name;
  private Object value;

}
