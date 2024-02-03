package com.nq.mvc.adapter;

import com.nq.mvc.model.ModelAndView;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alfred.Ning
 * @since 2024年02月01日 08:17:00
 */
public interface HandleAdapter {

  ModelAndView handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response)
      throws IOException;

  boolean supports(Object handler);
}
