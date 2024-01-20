package com.nq.ioc;

import lombok.Data;

/**
 * bean中property value属性值
 * @author Alfred.Ning
 * @since 2024年01月20日 13:33:00
 */
@Data
public class TypedStringValue {
  private String value;
  private Class targetType;
}
