package com.nq.mvc.mapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alfred.Ning
 * @since 2024年02月01日 07:16:00
 */
public interface HandlerMapping {

  Object getHandler(HttpServletRequest request);

}
