package com.nq.spring.parseer;

import com.nq.spring.po.BeanDefinition;
import com.nq.spring.po.PropertyValue;
import com.nq.spring.po.RuntimeBeanReference;
import com.nq.spring.po.TypedStringValue;
import com.nq.spring.registry.BeanDefinitionRegistry;
import com.nq.spring.utils.ReflectUtils;
import java.util.List;
import org.dom4j.Element;

/**
 * 该类具体分发BeanDefinition由那个进行解析
 *
 * @author Alfred.Ning
 * @since 2024年01月26日 22:37:00
 */
public class BeanDefinitionParserDelegate {

  private BeanDefinitionRegistry beanDefinitionRegistry;

  public BeanDefinitionParserDelegate(BeanDefinitionRegistry beanDefinitionRegistry) {
    this.beanDefinitionRegistry = beanDefinitionRegistry;
  }

  public void parseDefaultBeanDefinition(Element element) {
    String className = element.attributeValue("class");
    if (className == null || className.equals("")) {
      return;
    }
    Class clazzType = ReflectUtils.resolveClassName(className);

    String beanName = element.attributeValue("id");
    if (beanName == null || beanName.equals("")) {
      beanName = clazzType.getSimpleName();
    }
    String scope = element.attributeValue("scope");
    scope = scope == null || scope.equals("") ? "singleton" : scope;
    String initMethod = element.attributeValue("init-method");
    BeanDefinition bd = new BeanDefinition(beanName, className);
    bd.setClazzType(clazzType);
    bd.setScope(scope);
    bd.setInitMethod(initMethod);

    List<Element> property = element.elements("property");
    for (Element propElement : property) {
      parsePropElement(propElement, bd);
    }

    this.beanDefinitionRegistry.registerBeanDefinition(beanName, bd);

  }

  private void parsePropElement(Element propElement, BeanDefinition bd) {
    String name = propElement.attributeValue("name");
    String value = propElement.attributeValue("value");
    String ref = propElement.attributeValue("ref");

    if ((value == null || value.equals("")) && (ref == null || ref.equals(""))) {
      return;
    }
    PropertyValue pv = null;
    if (value != null && !value.equals("")) {
      TypedStringValue typedStringValue = new TypedStringValue(value);
      Class targetType = ReflectUtils.resolveType(bd.getClazzType(), name);
      typedStringValue.setTargetType(targetType);

      pv = new PropertyValue(name, typedStringValue);
    } else if (ref != null && !ref.equals("")) {
      RuntimeBeanReference runtimeBeanReference = new RuntimeBeanReference(ref);
      pv = new PropertyValue(name, runtimeBeanReference);
    }
    bd.addProperty(pv);
  }


  // aop ioc context mvc解析
  public void parseCustomBeanDefinition(Element element) {

  }
}
