package com.nq.ioc;

import lombok.Data;

/**
 * bean中property ref属性值
 *
 * @author Alfred.Ning
 * @since 2024年01月20日 13:32:00
 */
@Data
public class RuntimeBeanReference {
  private String ref;
  private Class<?> targetType;

}
