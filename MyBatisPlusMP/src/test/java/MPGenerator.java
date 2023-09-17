import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Alfred.Ning
 * @since 2023年09月17日 15:29:00
 */
public class MPGenerator {

  public static void main(String[] args) {

    // 生成的表
    String[] listTable = {"tbl_employee"};  //

    // 全局配置
    GlobalConfig globalConfig = new GlobalConfig();

    globalConfig.setActiveRecord(false)  // 设置AR模式
        .setAuthor("Alfred.Ning")
        .setOutputDir("D:\\java_arch\\Coding\\MyBatisPlusMP\\src\\main\\java") // 生成路径
        .setFileOverride(true) // 文件覆盖
        .setIdType(IdType.AUTO) // 主键策略
        .setServiceName("%sService") // 设置生成接口是否有I
        .setBaseResultMap(true) // 基本resultMap
        .setMapperName("%sDao")  // mapper类后缀名
        .setEnableCache(false) // 二级缓存
        .setBaseColumnList(true); // 设置sql片段

    // 数据源配置
    DataSourceConfig dataSourceConfig = new DataSourceConfig();
    dataSourceConfig.setDbType(DbType.MYSQL)
        .setDriverName("com.mysql.jdbc.Driver")
        .setUrl("jdbc:mysql://localhost:3307/mp?characterEncoding=utf8")
        .setUsername("root")
        .setPassword("ningqiang");

    //策略配置
    ArrayList<TableFill> tableFills = new ArrayList<>();
    tableFills.add(new TableFill("create_date", FieldFill.INSERT));
    tableFills.add(new TableFill("update_date", FieldFill.INSERT_UPDATE));
    StrategyConfig stConfig = new StrategyConfig();
    stConfig.setCapitalMode(true) // 全局大写命名
        .setDbColumnUnderline(true) //表名 字段名 是否使用下滑 线命名
        .setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
        .setInclude(listTable) //生成的表
        .setVersionFieldName("version") // 版本号名称
        .setLogicDeleteFieldName("deleted") // 逻辑删除
        .setEntityLombokModel(true) // 开启lombok
        .setTableFillList(tableFills) // 添加表字段填充
        .setTablePrefix("tbl_"); // 表前缀

    //包名策略
    PackageConfig pkConfig = new PackageConfig();
    pkConfig.setParent("com.alfred.mp")
        .setMapper("mapper")
        .setService("service")
        .setController("controller")
        .setEntity("beans")
        .setXml("mapper");

    AutoGenerator ag = new
        AutoGenerator().setGlobalConfig(globalConfig)
        .setDataSource(dataSourceConfig)
        .setStrategy(stConfig)
        .setPackageInfo(pkConfig);
    //  .setTemplateEngine(new FreemarkerTemplateEngine()) 使用Freemarker引擎模板，默认的是Velocity引擎模板
    ag.execute();
  }

}
