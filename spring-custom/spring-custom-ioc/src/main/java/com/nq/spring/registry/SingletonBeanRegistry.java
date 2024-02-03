package com.nq.spring.registry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alfred.Ning
 * @since 2024年01月26日 08:18:00
 */
public class SingletonBeanRegistry {

  private Map<String, Object> singletonObjects = new HashMap<>();

  public void addSingleton(String beanName, Object bean) {
    this.singletonObjects.put(beanName, bean);
  }

  public Object getSingleton(String beanName){
    return this.singletonObjects.get(beanName);
  }

}
