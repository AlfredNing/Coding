import com.alfred.mp.beans.Employee;
import com.alfred.mp.mapper.EmployeeMapper;
import com.baomidou.mybatisplus.plugins.Page;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.sql.DataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Alfred.Ning
 * @since 2023年09月16日 22:07:00
 */
public class TestMPAppInterceptor {

  private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
  EmployeeMapper employeeMapper = applicationContext.getBean("employeeMapper", EmployeeMapper.class);

  @Test
  public void testJdbc() throws SQLException {
    DataSource dataSource = applicationContext.getBean("dataSource", DataSource.class);
    Connection conn = dataSource.getConnection();
    System.out.println(conn);
    conn.close();
  }

  /**
   * 测试分页插件
   */
  @Test
  public void testPage() throws SQLException {
    Page<Employee> page = new Page<>(1, 3);
    List<Employee> employees = employeeMapper.selectPage(page, null);
    System.out.println(employees);

    System.out.println("总条数" + page.getTotal());
    System.out.println("当前页码" + page.getCurrent());
    System.out.println("总页码" + page.getPages());
    System.out.println("每页显示条数" + page.getSize());
    System.out.println("是否有上一页" + page.hasPrevious());
    System.out.println("是否有下一页" + page.hasNext());

    // 将查询结果封装
  }

  /**
   * sql分析插件
   *
   * @throws SQLException
   */
  @Test
  public void testDelete() throws SQLException {
    employeeMapper.delete(null);
  }

  /**
   * 性能分析插件
   *
   * @throws SQLException
   */
  @Test
  public void testPerformance() throws SQLException {
    Employee employee = new Employee();
    employee.setAge(23);
    employee.setGender("1");
    employee.setEmail("2342@163.com");
    employeeMapper.insert(employee);
  }


  /**
   * 测试乐观锁
   *
   * @throws SQLException
   */
  @Test
  public void testOptimisticLock() throws SQLException {
    Employee employee = new Employee();
    employee.setId(13L);
    employee.setAge(23);
    employee.setGender("1");
    employee.setEmail("2342@163.com");
    employee.setLastName("Songsi");
    employee.setVersion(1);
    employeeMapper.updateById(employee);

  }

  /**
   * 逻辑删除
   *
   * @throws SQLException
   */
  @Test
  public void testDeleteById() throws SQLException {
    Employee employee = new Employee();
    employee.setId(13L);
    employee.setAge(23);
    employee.setGender("1");
    employee.setEmail("2342@163.com");
    employee.setLastName("Songsi");
    employee.setVersion(1);
    Integer integer = employeeMapper.deleteById(13);
    System.out.println(integer);
  }

  /**
   * 测试公共字段属性
   *
   * @throws SQLException
   */
  @Test
  public void testMetaObject() throws SQLException, InterruptedException {
    Employee employee = new Employee();
    employee.setAge(27);
    employee.setGender("1");
    employee.setEmail("6666lisa-lisa@163.com");
    employee.setLastName("Lisa");
    Integer integer = employeeMapper.insert(employee);
    System.out.println(integer);
    TimeUnit.SECONDS.sleep(10);
    employee.setLastName("List---Lisa");
  }

  @Test
  public void testMetaObject02() throws SQLException, InterruptedException {
    Employee employee = new Employee();
//    employee.setId(16);
//    employee.setAge(27);
//    employee.setGender("1");
//    employee.setEmail("6666lisa-lisa@163.com");
//    employee.setLastName("Lisa");
//    employee.setLastName("List---Lisa");
//    employee.setVersion(1);
    employeeMapper.deleteById(15);
  }
}
