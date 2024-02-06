import com.nq.config.JpaConfig;
import com.nq.po.Customer;
import com.nq.repository.CustomerQBERepository;
import com.nq.repository.CustomerQueryDSLRepository;
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
public class QuerydslPredicateExecutorTest {

  @Autowired
  private CustomerQueryDSLRepository customerQueryDSLRepository;

  @Test
  public void test() {

  }

  @Test
  public void test02() {

  }
}
