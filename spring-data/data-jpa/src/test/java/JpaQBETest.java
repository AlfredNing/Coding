import com.nq.config.JpaConfig;
import com.nq.po.Customer;
import com.nq.repository.CustomerQBERepository;
import com.nq.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Alfred.Ning
 * @since 2024年02月06日 23:52:00
 */
@ContextConfiguration(classes = JpaConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class JpaQBETest {

  @Autowired
  private CustomerQBERepository customerQBERepository;

  @Test
  public void test() {
    Customer customer = new Customer();
    customer.setId(1L);

    Example<Customer> example = Example.of(customer);
    System.out.println(customerQBERepository.findAll(example));
  }

  @Test
  public void test02() {
    Customer customer = new Customer();
    customer.setAddress("xi");
    ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase("address");

    Example<Customer> example = Example.of(customer, matcher);
    System.out.println(customerQBERepository.findAll(example));
  }
}
