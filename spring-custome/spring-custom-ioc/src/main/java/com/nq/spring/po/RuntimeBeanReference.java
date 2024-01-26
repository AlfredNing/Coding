package com.nq.spring.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * bean中property ref属性值
 *
 * @author Alfred.Ning
 * @since 2024年01月20日 13:32:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuntimeBeanReference {

  private String ref;
  private Class<?> targetType;

  public RuntimeBeanReference(String ref) {
    this.ref = ref;
  }
}
