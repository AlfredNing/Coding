package com.nq.spring.po;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * @author Alfred.Ning
 * @since 2024年01月19日 08:10:00
 */
@Data
public class BeanDefinition {

  // bean标签的class属性
  private String clazzName;

  // bean标签的class属性对应的class类型
  private Class<?> clazzType;
  // 对应的beanName id二选一
  private String beanName;

  // 初始化方法名称
  private String initMethod;

  // 默认singleton
  private String scope;

  // bean定义的属性信息
  private List<PropertyValue> propertyValues = new ArrayList<>();

  public BeanDefinition(String beanName, String clazzName) {
    this.beanName = beanName;
    this.clazzName = clazzName;
    this.clazzType = resolveClassName(clazzName);
  }

  private Class<?> resolveClassName(String clazzName) {
    try {
      return Class.forName(clazzName);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static final String SCOPE_SINGLETON = "singleton";
  private static final String SCOPE_PROTOTYPE = "prototype";

  public Boolean isSingleton() {
    return SCOPE_SINGLETON.equals(this.scope);
  }

  public Boolean isPrototype() {
    return SCOPE_PROTOTYPE.equals(this.scope);
  }

  public void addProperty(PropertyValue pv) {
    this.propertyValues.add(pv);
  }
}
