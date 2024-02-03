package com.nq.mvc.adapter;

import com.nq.mvc.handler.HttpRequestHandler;
import com.nq.mvc.model.ModelAndView;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alfred.Ning
 * @since 2024年02月01日 08:20:00
 */
public class SimpleControllerHandlerAdapter implements HandleAdapter {

  @Override
  public boolean supports(Object handler) {
    return handler instanceof HttpRequestHandler;
  }

  @Override
  public ModelAndView handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    ((HttpRequestHandler) handler).handleRequest(request, response);
    return null;
  }
}
