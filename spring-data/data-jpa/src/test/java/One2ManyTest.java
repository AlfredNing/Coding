import com.nq.config.JpaConfig;
import com.nq.po.Account;
import com.nq.po.Customer;
import com.nq.po.Message;
import com.nq.repository.CustomerRepository;
import com.nq.repository.MessageRepository;
import java.util.Arrays;
import java.util.List;
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
public class One2ManyTest {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private MessageRepository messageRepository;


  @Test
  public void test(){
    Customer customer = new Customer();
    customer.setCustomerName("wanggwu");
    customer.setAddress("shanghai");
    List<Message> messages = Arrays.asList(
        new Message("message_01"),
        new Message("message_02")
    );
    customer.setMessages(messages);
    customerRepository.save(customer);
  }

  @Test
  @Transactional(readOnly = true)
  public void testQ(){
    Customer customer = new Customer();
    customer.setId(3L);
    List<Message> messages = messageRepository.findByCustomer(customer);
    System.out.println(messages);
  }
}
