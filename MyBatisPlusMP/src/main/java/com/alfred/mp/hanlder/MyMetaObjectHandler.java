package com.alfred.mp.hanlder;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import org.apache.ibatis.reflection.MetaObject;

/**
 * @author Alfred.Ning
 * @since 2023年09月17日 17:22:00
 */
public class MyMetaObjectHandler extends MetaObjectHandler {

  /**
   * 插入自动填充
   *
   * @param metaObject
   */
  @Override
  public void insertFill(MetaObject metaObject) {
    this.setFieldValByName("createDate", new Date(), metaObject);
    this.setFieldValByName("updateDate", new Date(), metaObject);

  }

  /**
   * 更新自动填充
   *
   * @param metaObject
   */
  @Override
  public void updateFill(MetaObject metaObject) {
    this.setFieldValByName("updateDate", new Date(), metaObject);
  }

}
