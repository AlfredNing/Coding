import com.nq.config.JpaConfig;
import com.nq.po.Account;
import com.nq.po.Customer;
import com.nq.repository.CustomerRepository;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alfred.Ning
 * @since 2024年02月07日 00:59:00
 */
@ContextConfiguration(classes = JpaConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class One2OneTest {

  @Autowired
  private CustomerRepository customerRepository;
  @Test
  public void test(){
    Customer customer = new Customer();
    customer.setCustomerName("lisi");
    customer.setAddress("xian");
    Account account = new Account();
    account.setAccountName("nq");
    customer.setAccount(account);
    customerRepository.save(customer);
  }

  @Test
  @Transactional(readOnly = true)
  public void testQ(){
    Optional<Customer> byId = customerRepository.findById(1L);
    System.out.println(byId);
  }
}
