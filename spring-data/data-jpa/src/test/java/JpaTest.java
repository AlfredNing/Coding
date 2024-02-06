import com.nq.config.JpaConfig;
import com.nq.po.Customer;
import com.nq.repository.CustomerRepository;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.TypedSort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Alfred.Ning
 * @since 2024年02月06日 22:30:00
 */
@ContextConfiguration(classes = JpaConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class JpaTest {

  @Autowired
  private CustomerRepository customerRepository;

  @Test
  public void testQuery() {
    Optional<Customer> byId = customerRepository.findById(1L);
    System.out.println(byId.get());
  }

  @Test
  public void testInsert() {
    Customer customer = new Customer();
    customer.setCustomerName("lisi");
    customer.setAddress("南京");
    Customer save = customerRepository.save(customer);
    System.out.println(save);
  }

  @Test
  public void testDelete() {
    Customer customer = new Customer();
    customer.setId(2L);
    customer.setCustomerName("lisi");
    customerRepository.delete(customer);
  }


  @Test
  public void testPaging() {
    Page<Customer> customers = customerRepository.findAll(PageRequest.of(0, 2));
    System.out.println(customers.getTotalElements());
    System.out.println(customers.getTotalPages());
    System.out.println(customers.getContent());
  }



  @Test
  public void testSort() {
    Sort sort = Sort.by("id").descending();
    Iterable<Customer> customers = customerRepository.findAll(sort);
    System.out.println(customers);
  }

  @Test
  public void testSortType() {
    TypedSort<Customer> sortType = Sort.sort(Customer.class);
    Sort sort = sortType.by(Customer::getId).descending();
    Iterable<Customer> customers = customerRepository.findAll(sort);
    System.out.println(customers);
  }
}
