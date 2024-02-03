package com.nq.spring.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Alfred.Ning
 * @since 2024年01月26日 21:34:00
 */
public class ReflectUtils {

  public static Object newInstance(Class<?> clazzType) {
    try {
      Constructor constructor = clazzType.getConstructor();
      return constructor.newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void setProperty(Class clazzType, Object bean, String name, Object value) {
    try {
      Field field = clazzType.getDeclaredField(name);
      field.setAccessible(true);
      field.set(bean, value);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public static void invokeMethod(Class<?> clazzType, Object bean, String initMethod) {
    try {
      Method method = clazzType.getMethod(initMethod);
      method.invoke(bean);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static Class resolveClassName(String className) {
    try {
      return Class.forName(className);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static Class resolveType(Class clazzType, String name) {
    try {
      Field declaredField = clazzType.getDeclaredField(name);
      declaredField.setAccessible(true);
      return declaredField.getType();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
