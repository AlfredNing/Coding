package com.nq.spring.ioc;

import com.nq.spring.po.BeanDefinition;
import com.nq.spring.registry.SingletonBeanRegistry;

/**
 * 定义getBean的流程，细节不负责
 *
 * @author Alfred.Ning
 * @since 2024年01月26日 08:16:00
 */
public abstract class AbstractBeanFactory extends SingletonBeanRegistry implements BeanFactory {

  @Override
  public Object getBean(String beanName) {
    Object bean = this.getSingleton(beanName);
    if (bean != null) {
      return bean;
    }

    BeanDefinition bd = getBeanDefinition(beanName);
    if (bd == null) {
      return null;
    }
    if (bd.isSingleton()) {
      bean = createBean(bd);
      this.addSingleton(beanName, bean);
    } else if (bd.isPrototype()) {
      bean = createBean(bd);
    } else {

    }
    return bean;
  }

  protected abstract BeanDefinition getBeanDefinition(String beanName);

  protected abstract Object createBean(BeanDefinition bd);
}
