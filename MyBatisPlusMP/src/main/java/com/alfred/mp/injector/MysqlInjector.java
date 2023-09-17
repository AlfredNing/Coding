package com.alfred.mp.injector;

import com.baomidou.mybatisplus.entity.TableInfo;
import com.baomidou.mybatisplus.mapper.AutoSqlInjector;
import org.apache.ibatis.builder.MapperBuilderAssistant;

/**
 * @author Alfred.Ning
 * @since 2023年09月17日 16:55:00
 */
public class MysqlInjector extends AutoSqlInjector {

  @Override
  protected void injectSql(MapperBuilderAssistant builderAssistant, Class<?> mapperClass, Class<?> modelClass,
      TableInfo table) {

  }
}
