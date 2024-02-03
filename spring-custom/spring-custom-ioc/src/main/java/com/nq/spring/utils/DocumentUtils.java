package com.nq.spring.utils;

import java.io.InputStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

/**
 * @author Alfred.Ning
 * @since 2024年01月26日 22:16:00
 */
public class DocumentUtils {

  public static Document createDocument(InputStream is) {
    try {
      SAXReader saxReader = new SAXReader();
      return saxReader.read(is);
    } catch (DocumentException e) {
      e.printStackTrace();
    }
    return null;
  }

}
