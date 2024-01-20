package com.nq.ioc;

import lombok.Data;

/**
 * bean中定义属性封装
 * @author Alfred.Ning
 * @since 2024年01月20日 13:30:00
 */
@Data
public class PropertyValue {
  private String name;
  private Object value;

}
