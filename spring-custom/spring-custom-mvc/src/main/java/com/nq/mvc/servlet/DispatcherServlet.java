package com.nq.mvc.servlet;

import com.nq.mvc.adapter.HandleAdapter;
import com.nq.mvc.adapter.HttpRequestHandlerAdapter;
import com.nq.mvc.adapter.SimpleControllerHandlerAdapter;
import com.nq.mvc.handler.Controller;
import com.nq.mvc.handler.HttpRequestHandler;
import com.nq.mvc.handler.QueryUserHandler;
import com.nq.mvc.handler.SaveUserHandler;
import com.nq.mvc.mapping.BeanNameUrlHandleMapping;
import com.nq.mvc.mapping.HandlerMapping;
import com.nq.mvc.mapping.SimpleUrlHandlerMapping;
import com.nq.spring.ioc.DefaultListableBeanFactory;
import com.nq.spring.reader.XmlBeanDefinitionReader;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * 用于处理分发请求
 */
public class DispatcherServlet extends AbstractServlet {

  private List<HandlerMapping> handlerMappingList = new ArrayList<>();
  private List<HandleAdapter> handleAdapters = new ArrayList<>();

  @Override
  public void init(ServletConfig config) throws ServletException {
    handlerMappingList.add(new BeanNameUrlHandleMapping());
    handlerMappingList.add(new SimpleUrlHandlerMapping());
    handleAdapters.add(new HttpRequestHandlerAdapter());
    handleAdapters.add(new SimpleControllerHandlerAdapter());


    // 注入Bean
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
    xmlBeanDefinitionReader.loadBeanDefinitions("springmvc.xml");


  }

  public void dispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    /**
     * 1. 接受请求
     * 2. 分发请求
     * 3. 查找处理类
     * 4. 调用处理方法
     * 5. 响应结果
     */
    Object handler = getHandler(request);
    HandleAdapter handleAdapter = getHandlerAdapter(handler);
    if (handleAdapter == null) {
      return;
    }
    handleAdapter.handleRequest(handler, request, response);
  }

  private HandleAdapter getHandlerAdapter(Object handler) {
    for (HandleAdapter handleAdapter : handleAdapters) {
      if (handleAdapter.supports(handler)) {
        return handleAdapter;
      }
    }
    return null;
  }

  private Object getHandler(HttpServletRequest request) {
    for (HandlerMapping handlerMapping : handlerMappingList) {
      Object handler = handlerMapping.getHandler(request);
      if (handler != null) {
        return handler;
      }
    }

    return null;
  }
}
