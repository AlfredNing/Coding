package com.nq.mvc.mapping;

import com.nq.mvc.handler.QueryUserHandler;
import com.nq.mvc.handler.SaveUserHandler;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Alfred.Ning
 * @since 2024年02月01日 07:11:00
 */
public class SimpleUrlHandlerMapping implements HandlerMapping {

  private Map<String, Object> urlMapping = new HashMap<>();

  public SimpleUrlHandlerMapping() {
    urlMapping.put("/queryUser", new QueryUserHandler());
    urlMapping.put("/saveUser", new SaveUserHandler());
  }

  public Object getHandler(HttpServletRequest request) {
    String requestURI = request.getRequestURI();
    if (request == null || "".equals(requestURI)) {
      return null;
    }
    return urlMapping.get(requestURI);
  }

}
