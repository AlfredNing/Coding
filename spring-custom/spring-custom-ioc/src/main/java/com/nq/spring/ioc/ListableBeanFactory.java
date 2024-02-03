package com.nq.spring.ioc;

import com.nq.spring.po.BeanDefinition;
import java.util.List;

/**
 * @author Alfred.Ning
 * @since 2024年02月03日 13:34:00
 */
public interface ListableBeanFactory extends BeanFactory {

  void registerBeanDefinition(String beanName, BeanDefinition bd);

  List<BeanDefinition> getBeanDefinitions();

  <T> List<T> getBeansType(Class type);
}
