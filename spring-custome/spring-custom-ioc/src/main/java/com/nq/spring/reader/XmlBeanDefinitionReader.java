package com.nq.spring.reader;

import com.nq.spring.ioc.DefaultListableBeanFactory;
import com.nq.spring.registry.BeanDefinitionRegistry;
import com.nq.spring.resource.ClassPathResource;
import com.nq.spring.resource.Resource;
import com.nq.spring.utils.DocumentUtils;
import java.io.InputStream;
import org.dom4j.Document;

/**
 * 制定加载xml流程
 *
 * @author Alfred.Ning
 * @since 2024年01月26日 22:25:00
 */
public class XmlBeanDefinitionReader implements BeanDefinitionReader {

  private BeanDefinitionRegistry beanDefinitionRegistry;

  public XmlBeanDefinitionReader(DefaultListableBeanFactory defaultListableBeanFactory) {
    this.beanDefinitionRegistry = defaultListableBeanFactory;
  }

  @Override
  public void loadBeanDefinitions(Resource resource) {
    InputStream inputStream = resource.getInputStream();
    Document document = DocumentUtils.createDocument(inputStream);
    BeanDefinitionDocumentReader documentReader = new BeanDefinitionDocumentReader(beanDefinitionRegistry);
    documentReader.parseBeanDefinitions(document.getRootElement());
  }

  @Override
  public void loadBeanDefinitions(String location) {
    ClassPathResource resource = new ClassPathResource(location);
    this.loadBeanDefinitions(resource);
  }
}
