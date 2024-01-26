package com.nq.spring.ioc;

import com.nq.spring.po.BeanDefinition;
import com.nq.spring.registry.BeanDefinitionRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alfred.Ning
 * @since 2024年01月26日 21:56:00
 */
public class DefaultListableBeanFactory extends AbstractAutowiredCapableBeanFactory implements BeanDefinitionRegistry {

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

}
