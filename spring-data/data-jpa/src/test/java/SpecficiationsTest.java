import com.nq.config.JpaConfig;
import com.nq.po.Customer;
import com.nq.repository.CustomerQBERepository;
import com.nq.repository.CustomerSpecificationsRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.Order;
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
public class SpecficiationsTest {

  @Autowired
  private CustomerSpecificationsRepository customerSpecificationsRepository;

  @Test
  public void test() {

  }

  @Test
  public void test02() {
    List<Customer> specificationsRepositoryAll = customerSpecificationsRepository.findAll(
        (root, query, criteriaBuilder) -> {
          /**
           * root 查询那个表
           * query 查询字段 排序
           * criteriaBuilder 查询条件
           */
          Order order = criteriaBuilder.desc(root.get("id"));
          In<Object> id = criteriaBuilder.in(root.get("id"));
          id.value(1L).value(7L);
          return query.orderBy(order).where(id).getRestriction();
        });
  }
}
