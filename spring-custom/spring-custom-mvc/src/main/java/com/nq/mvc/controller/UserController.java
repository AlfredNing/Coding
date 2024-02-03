package com.nq.mvc.controller;

import com.nq.mvc.annotation.Controller;
import com.nq.mvc.annotation.RequestMapping;
import com.nq.mvc.annotation.ResponseBody;

/**
 * @author Alfred.Ning
 * @since 2024年02月03日 13:51:00
 */
@Controller
@RequestMapping("/user")
public class UserController {

  @RequestMapping("query")
  @ResponseBody
  public String queryUser() {
    return "222";
  }
}
