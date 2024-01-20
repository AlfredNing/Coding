import com.nq.base.dao.UserDaoImpl;
import com.nq.base.po.User;
import com.nq.base.service.UserService;
import com.nq.base.service.UserServiceImpl;
import com.nq.ioc.BeanDefinition;
import com.nq.ioc.PropertyValue;
import com.nq.ioc.RuntimeBeanReference;
import com.nq.ioc.TypedStringValue;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Alfred.Ning
 * @since 2024年01月18日 22:41:00
 */
public class Test1 {

  private Map<String, BeanDefinition> beanDefinition = new HashMap();
  private Map<String, Object> singletionObjects = new HashMap();

  @Before
  public void init() {
    // 加载beanDefinition

  }

  @Test
  public void test() {
//    UserService userService = ((UserService) getObject("userService"));
    UserService userService = (UserService) getBean("userService");
    HashMap<String, Object> map = new HashMap<>();
    map.put("username", "ningqiang");

    List<User> users = userService.queryUserList(map);
    for (User user : users) {
      System.out.println(user);
    }

  }

  private Object getObject(String serviceName) {
    Object o = null;
    if ("userService".equals(serviceName)) {
      UserServiceImpl userService = new UserServiceImpl();
      UserDaoImpl userDao = new UserDaoImpl();
      BasicDataSource basicDataSource = new BasicDataSource();
      basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
      basicDataSource.setUrl(
          "jdbc:mysql://localhost:3307/spring?useSSL=false&serverTimezone=UTC&nullCatalogMeansCurrent=true");
      basicDataSource.setUsername("root");
      basicDataSource.setPassword("ningqiang");

      userDao.setDataSource(basicDataSource);
      userService.setUserDao(userDao);
      o = userService;
    }
    return o;
  }

  private Object getBean(String beanName) {
    Object bean = this.singletionObjects.get(beanName);
    if (bean != null) {
      return bean;
    }
    BeanDefinition bd = this.beanDefinition.get(beanName);
    if (bd == null) {
      return null;
    }
    if (bd.isSingleton()) {
      bean = crateBean(bd);
      this.singletionObjects.put(beanName, bean);
    } else if (bd.isPrototype()) {

    } else {

    }
    return bean;
  }

  private Object crateBean(BeanDefinition bd) {
    // 创建实例
    Object bean = createInstance(bd);
    // 属性填充
    populateBean(bean, bd);
    // 初始话bean
    initilizingBean(bean, bd);
    return bean;
  }

  private void initilizingBean(Object bean, BeanDefinition bd) {
    String initMethod = bd.getInitMethod();
    if (initMethod == null || initMethod.equals("")) {
      return;
    }

    invokeMethod(bd.getClazzType(), bean, initMethod);
  }

  private void invokeMethod(Class<?> clazzType, Object bean, String initMethod) {
    try {
      Method method = clazzType.getMethod(initMethod);
      method.invoke(bean);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  private void populateBean(Object bean, BeanDefinition bd) {
    for (PropertyValue pv : bd.getPropertyValues()) {
      String name = pv.getName();
      Object value = pv.getValue();
      Object valueToUse = parseValue(bd, name, value);
      // 依赖注入
      setProperty(bd.getClazzType(), bean, name, valueToUse);
    }

  }

  private Object parseValue(BeanDefinition bd, String name, Object value) {
    if (value instanceof TypedStringValue) {
      TypedStringValue typedStringValue = (TypedStringValue) value;
      String stringValue = typedStringValue.getValue();
      Class targetType = typedStringValue.getTargetType();
      return handleType(stringValue, targetType);
    } else if (value instanceof RuntimeBeanReference) {
      RuntimeBeanReference reference = (RuntimeBeanReference) value;
      String ref = reference.getRef();
      // 循环依赖
      return getBean(ref);
    }
    return null;
  }

  private Object handleType(String stringValue, Class targetType) {
    if (targetType == String.class) {
      return stringValue;
    } else if (targetType == Integer.class) {
      return Integer.parseInt(stringValue);
    }
    return null;
  }

  private void setProperty(Class<?> clazzType, Object bean, String name, Object value) {
    try {
      Field field = clazzType.getField(name);
      field.setAccessible(true);
      field.set(bean, value);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  private Object createInstance(BeanDefinition bd) {
    return newInstance(bd.getClazzType());
  }

  private Object newInstance(Class<?> clazzType) {
    try {
      Constructor<?> constructor = clazzType.getConstructor();
      return constructor.newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
