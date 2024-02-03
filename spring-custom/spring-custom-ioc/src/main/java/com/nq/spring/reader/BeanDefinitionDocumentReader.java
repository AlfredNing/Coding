package com.nq.spring.reader;

import com.nq.spring.parseer.BeanDefinitionParserDelegate;
import com.nq.spring.registry.BeanDefinitionRegistry;
import java.util.List;
import org.dom4j.Element;

/**
 * @author Alfred.Ning
 * @since 2024年01月26日 22:22:00
 */
public class BeanDefinitionDocumentReader {

  private BeanDefinitionRegistry beanDefinitionRegistry;

  public BeanDefinitionDocumentReader(BeanDefinitionRegistry beanDefinitionRegistry) {
    this.beanDefinitionRegistry = beanDefinitionRegistry;
  }

  public void parseBeanDefinitions(Element rootElement) {
    BeanDefinitionParserDelegate definitionParserDelegate = new BeanDefinitionParserDelegate(beanDefinitionRegistry);
    List<Element> elements = rootElement.elements();
    for (Element element : elements) {
      String name = element.getName();
      if (name.equals("bean")) {
        definitionParserDelegate.parseDefaultBeanDefinition(element);
      } else {
        definitionParserDelegate.parseCustomBeanDefinition(element);
      }
    }
  }

}
