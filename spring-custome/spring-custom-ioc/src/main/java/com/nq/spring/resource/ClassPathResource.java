package com.nq.spring.resource;

import java.io.InputStream;

/**
 * @author Alfred.Ning
 * @since 2024年01月26日 22:11:00
 */
public class ClassPathResource implements Resource {

  private String location;

  public ClassPathResource(String location) {
    this.location = location;
  }

  @Override
  public InputStream getInputStream() {
    // web环境中classPath:xxx.xml
    if (location.indexOf("classpath:") > -1) {
      location = location.substring(10);
    }
    if (location.indexOf("classpath*:") > -1) {
      location = location.substring(11);
    }
    return this.getClass().getClassLoader().getResourceAsStream(location);
  }
}
