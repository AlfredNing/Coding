package com.nq.mvc.adapter;

import com.nq.mvc.handler.Controller;
import com.nq.mvc.model.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alfred.Ning
 * @since 2024年02月01日 08:19:00
 */
public class HttpRequestHandlerAdapter implements HandleAdapter {

  @Override
  public boolean supports(Object handler) {
    return handler instanceof Controller;
  }

  @Override
  public ModelAndView handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response) {
    return ((Controller) handler).handleRequest(request, response);
  }


}
