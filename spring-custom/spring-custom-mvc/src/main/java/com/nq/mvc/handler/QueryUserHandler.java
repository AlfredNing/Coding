package com.nq.mvc.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alfred.Ning
 * @since 2024年02月01日 07:08:00
 */
public class QueryUserHandler implements HttpRequestHandler{

  @Override
  public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
    System.out.println("QueryUserServlet......");
    response.setContentType("application/json;charset=UTF-8");
    response.getWriter().write("QueryUserServlet...");
  }
}
