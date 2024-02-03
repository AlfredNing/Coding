package com.nq.spring.ioc;

import com.nq.spring.po.BeanDefinition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alfred.Ning
 * @since 2024年01月26日 21:56:00
 */
public class DefaultListableBeanFactory extends AbstractAutowiredCapableBeanFactory implements ListableBeanFactory {

  private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

  @Override
  public void registerBeanDefinition(String beanName, BeanDefinition bd) {
    this.beanDefinitionMap.put(beanName, bd);
  }

  @Override
  public BeanDefinition getBeanDefinition(String beanName) {
    return this.beanDefinitionMap.get(beanName);
  }


  @Override
  public List<BeanDefinition> getBeanDefinitions() {
    ArrayList<BeanDefinition> beanDefinitions = new ArrayList<>();
    for (BeanDefinition bd : beanDefinitionMap.values()) {
      beanDefinitions.add(bd);
    }
    return beanDefinitions;
  }

  @Override
  public<T>  List<T> getBeansType(Class type) {
    List<T> beans = new ArrayList<>();
    List<BeanDefinition> beanDefinitions = getBeanDefinitions();
    for (BeanDefinition bd : beanDefinitions) {
      if (bd.getClazzType().isAssignableFrom(type)){
        beans.add((T)getBean(bd.getBeanName()));
      }
    }
    return (List<T>) beans;
  }
}
