import com.nq.jpa.hibernate.po.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Alfred.Ning
 * @since 2024年02月06日 10:49:00
 */
public class JpaTest {

  private SessionFactory sf;

  @Before
  public void init() {
    StandardServiceRegistry registry =
        new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

  }

  @Test
  public void insert() {
    try (Session session = sf.openSession()) {
      Transaction tx = session.beginTransaction();
      Customer customer = new Customer();
      customer.setCustomerName("lisi");
      customer.setAddress("杭州");
      session.save(customer);
      tx.commit();
    }
  }

  @Test
  public void find() {
    try (Session session = sf.openSession()) {
      Transaction tx = session.beginTransaction();
      Customer customer = session.find(Customer.class, 1L);
      System.out.println(customer);
      tx.commit();
    }
  }

  /**
   * 延迟查询
   */
  @Test
  public void test02(){
    try (Session session = sf.openSession()) {
      Transaction tx = session.beginTransaction();
      Customer customer = session.load(Customer.class, 1L);
      System.out.println(customer);
      tx.commit();
    }
  }

  @Test
  public void test03(){
    try (Session session = sf.openSession()) {
      Transaction tx = session.beginTransaction();
      Query<Customer> customer = session.createQuery("select id from Customer", Customer.class);
      System.out.println(customer);
      tx.commit();
    }
  }


  @Test
  public void test04(){
    try (Session session = sf.openSession()) {
      Transaction tx = session.beginTransaction();
      Customer customer = session.createQuery("select c from Customer c where id =:id", Customer.class)
          .setParameter("id", 1L)
          .getSingleResult();

      System.out.println(customer);
      tx.commit();
    }
  }

  @Test
  public void test05(){
    try (Session session = sf.openSession()) {
      Transaction tx = session.beginTransaction();
      Customer customer = new Customer();
      customer.setId(1L);
      customer.setAddress("西安");
      session.update(customer);
      System.out.println(customer);
      tx.commit();
    }
  }

  @Test
  public void test06(){
    try (Session session = sf.openSession()) {
      Transaction tx = session.beginTransaction();
      int i = session.createQuery("update Customer set customerName = :customerName where id=:id")
          .setParameter("customerName", "zhangsan")
          .setParameter("id", 1L)
          .executeUpdate();
      tx.commit();
    }
  }

}