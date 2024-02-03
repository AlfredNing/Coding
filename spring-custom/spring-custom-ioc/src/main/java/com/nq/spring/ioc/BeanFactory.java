package com.nq.spring.ioc;

/**
 * @author Alfred.Ning
 * @since 2024年01月26日 08:15:00
 */
public interface BeanFactory {

  Object getBean(String beanName);
}
