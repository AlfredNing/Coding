//import com.baomidou.mybatisplus.mapper.Condition;
//import com.baomidou.mybatisplus.mapper.EntityWrapper;
//import com.baomidou.mybatisplus.plugins.Page;
//import com.ning.entity.Employee;
//import com.ning.mapper.EmployeeMapper;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.List;
//import javax.sql.DataSource;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
///**
// * @author Alfred.Ning
// * @since 2023年09月16日 22:07:00
// */
//public class TestMPApp {
//
//  private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
////  EmployeeMapper employeeMapper = applicationContext.getBean("employeeMapper", EmployeeMapper.class);
//
//  @Test
//  public void testJdbc() throws SQLException {
//    DataSource dataSource = applicationContext.getBean("dataSource", DataSource.class);
//    Connection conn = dataSource.getConnection();
//    System.out.println(conn);
//    conn.close();
//  }
//
//  @Test
//  public void testInsert() throws SQLException {
//    Employee employee = new Employee();
////    employee.setAge(23);
//    employee.setEmail("16234234@qq.com");
//    employee.setLastName("ning");
////    employee.setGender(1);
//    // insert会进行非空判断
//    Integer insert = employeeMapper.insert(employee);
//    // 全量插入
//    Integer integer = employeeMapper.insertAllColumn(employee);
//    System.out.println(employee.getId());
//
//  }
//
//  @Test
//  public void testUpdate() throws SQLException {
//    employeeMapper.updateById(new Employee());
//    employeeMapper.updateAllColumnById(new Employee());
//  }
//
//  @Test
//  public void testQuery() throws SQLException {
////    employeeMapper.selectById(1);
////    employeeMapper.selectOne(new Employee());
//    List<Employee> employees = employeeMapper.selectPage(new Page<Employee>(2, 2), null);
//    System.out.println(employees);
//  }
//
//  @Test
//  public void testDelete() throws SQLException {
//    Integer integer = employeeMapper.deleteById(2);
//    System.out.println(integer);
//  }
//
//
//  @Test
//  public void testWrapper() throws SQLException {
//    List<Employee> employees = employeeMapper.selectPage(new Page<Employee>(1, 2), new EntityWrapper<Employee>());
//
//    List<Employee> employees1 = employeeMapper.selectList(new EntityWrapper<Employee>()
//        .eq("gender", 0)
//        .like("last_name", "Whi")
//        .or()
//        .like("email", "black")
//    );
//    System.out.println(employees);
//  }
//
//}
