import com.nq.user.entity.SampleTable;
import com.nq.user.mapper.SampleTableMapper;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

/**
 * @author Alfred.Ning
 * @since 2023��09��16�� 21:21:00
 */
public class MBGTest {

  public static void main(String[] args) {
    SqlSessionFactoryBuilder sessionFactoryBuilder = new SqlSessionFactoryBuilder();
    InputStream is = MBGTest.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
    SqlSessionFactory sessionFactory = sessionFactoryBuilder.build(is);
    SqlSession sqlSession = sessionFactory.openSession();
    SampleTableMapper mapper = sqlSession.getMapper(SampleTableMapper.class);
    // java����ͨ��Mapper��������
    MapperHelper mapperHelper = new MapperHelper();
    Configuration configuration = sqlSession.getConfiguration();
    mapperHelper.processConfiguration(configuration);
    for (SampleTable sampleTable : mapper.selectAll()) {
      System.out.println(sampleTable);
    }
  }

}
