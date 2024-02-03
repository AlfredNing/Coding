package com.nq.mvc.handler;

import com.nq.mvc.model.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alfred.Ning
 * @since 2024年02月01日 08:10:00
 */
public interface Controller {

  ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response);
}
