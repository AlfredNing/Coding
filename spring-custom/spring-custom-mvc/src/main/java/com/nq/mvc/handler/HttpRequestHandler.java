package com.nq.mvc.handler;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alfred.Ning
 * @since 2024年02月01日 08:08:00
 */
public interface HttpRequestHandler {

  void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
