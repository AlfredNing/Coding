package com.nq.spring.registry;

import com.nq.spring.po.BeanDefinition;
import java.util.List;

/**
 * @author Alfred.Ning
 * @since 2024年01月26日 08:23:00
 */
public interface BeanDefinitionRegistry {

  void registerBeanDefinition(String beanName, BeanDefinition bd);

  BeanDefinition getBeanDefinition(String beanName);

  List<BeanDefinition> getBeanDefinitions();

}
