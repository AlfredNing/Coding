package com.nq.spring.ioc;

import com.nq.spring.po.BeanDefinition;
import com.nq.spring.po.PropertyValue;
import com.nq.spring.resolver.BeanDefinitionValueResolver;
import com.nq.spring.utils.ReflectUtils;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 创建Bean
 *
 * @author Alfred.Ning
 * @since 2024年01月26日 08:32:00
 */
public class AbstractAutowiredCapableBeanFactory extends AbstractBeanFactory {

  @Override
  public Object createBean(BeanDefinition bd) {
    // 利用反射创建出来对象的实例
    Object bean = createInstance(bd);
    // 利用反射创建出来的对象进行属性赋值-
    populateBean(bean, bd);
    // 利用反射调用对象bean对象的初始化方法
    initializingBean(bean, bd);
    return bean;
  }

  private void initializingBean(Object bean, BeanDefinition bd) {
    String initMethod = bd.getInitMethod();
    if (initMethod == null || initMethod.equals("")) {
      return;
    }
    ReflectUtils.invokeMethod(bd.getClazzType(), bean, initMethod);
  }


  private void populateBean(Object bean, BeanDefinition bd) {
    List<PropertyValue> propertyValues = bd.getPropertyValues();
    BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(this);
    for (PropertyValue pv : propertyValues) {
      String name = pv.getName();
      Object value = pv.getValue();
      Object valueToUse = valueResolver.parseValue(value);
      ReflectUtils.setProperty(bd.getClazzType(), bean, name, valueToUse);
    }
  }

  private Object createInstance(BeanDefinition bd) {
    return ReflectUtils.newInstance(bd.getClazzType());
  }


  @Override
  protected BeanDefinition getBeanDefinition(String beanName) {
    return null;
  }
}
