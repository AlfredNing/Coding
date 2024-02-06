package com.nq.config;

import com.alibaba.druid.pool.DruidDataSource;
import java.util.Optional;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.hibernate.dialect.MySQL57Dialect;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jca.support.LocalConnectionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author Alfred.Ning
 * @since 2024年02月06日 19:18:00
 */
@Configuration
@EnableJpaRepositories("com.nq.repository")
@EnableJpaAuditing
public class JpaConfig {

  @Bean
  public DataSource dataSource() {
    DruidDataSource druidDataSource = new DruidDataSource();
    druidDataSource.setUsername("root");
    druidDataSource.setPassword("ningqiang");
    druidDataSource.setUrl(
        "jdbc:mysql://localhost:3307/springdatajpa?characterEncoding=utf8&amp;serverTimezone=Asia/Shanghai&amp;characterEncoding=utf-8");
    druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
    return druidDataSource;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
    hibernateJpaVendorAdapter.setGenerateDdl(true);
    hibernateJpaVendorAdapter.setShowSql(true);

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(hibernateJpaVendorAdapter);
    factory.setPackagesToScan("com.nq.po");
    factory.setDataSource(dataSource());
//    factory.setJpaDialect(new HibernateJpaDialect());
//    Properties properties = new Properties();
//    properties.put("hibernate.dialect","org.hibernate.dialect.MySQL57Dialect");
//    properties.put("hibernate.connection.url","jdbc:mysql://localhost:3307/springdatajpa?characterEncoding=utf8&amp;serverTimezone=Asia/Shanghai&amp;characterEncoding=utf-8");
//    factory.setJpaProperties(properties);
    return factory;
  }

  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
    jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
    return jpaTransactionManager;
  }

  @Bean
  public AuditorAware<String> auditorAware() {
    return new AuditorAware<>() {
      @Override
      public Optional<String> getCurrentAuditor() {
        return Optional.of("nq");
      }
    };
  }
}
