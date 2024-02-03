package com.nq.spring.resolver;

import com.nq.spring.ioc.BeanFactory;
import com.nq.spring.po.RuntimeBeanReference;
import com.nq.spring.po.TypedStringValue;

/**
 * @author Alfred.Ning
 * @since 2024年01月26日 21:47:00
 */
public class BeanDefinitionValueResolver {

  private BeanFactory beanFactory;

  public BeanDefinitionValueResolver(BeanFactory beanFactory) {
    this.beanFactory = beanFactory;
  }

  public Object parseValue(Object value) {
    if (value instanceof TypedStringValue) {
      TypedStringValue typedStringValue = (TypedStringValue) value;
      String stringValueValue = typedStringValue.getValue();
      Class clazzTargetType = typedStringValue.getTargetType();
      return handleType(stringValueValue, clazzTargetType);
    } else if (value instanceof RuntimeBeanReference) {
      RuntimeBeanReference runtimeBeanReference = (RuntimeBeanReference) value;
      String ref = runtimeBeanReference.getRef();
      return beanFactory.getBean(ref);
    }
    return null;
  }

  private Object handleType(String stringValueValue, Class clazzTargetType) {
    if (clazzTargetType == String.class) {
      return stringValueValue;
    } else if (clazzTargetType == Integer.class) {
      return Integer.parseInt(stringValueValue);
    }
    return null;
  }
}
