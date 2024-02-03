package com.nq.spring.reader;

import com.nq.spring.resource.Resource;

/**
 * @author Alfred.Ning
 * @since 2024年01月26日 22:24:00
 */
public interface BeanDefinitionReader {

  void loadBeanDefinitions(Resource resource);

  void loadBeanDefinitions(String location);

}
