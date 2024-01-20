package com.nq.base.dao;

import com.nq.base.po.User;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import lombok.Setter;

/**
 * @author Alfred.Ning
 * @since 2024年01月18日 08:43:00
 */
public class UserDaoImpl implements UserDao {

  @Setter
  private DataSource dataSource;

  @Override
  public List<User> queryUserList(Map<String, Object> param) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      connection = dataSource.getConnection();
      String sql = "select * from user where username = ?";

      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, param.get("username").toString());
      resultSet = preparedStatement.executeQuery();
      ArrayList<User> users = new ArrayList<>();
      User result = null;
      Class<User> userClass = User.class;
      while (resultSet.next()) {
        User user = userClass.newInstance();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
          String columnName = metaData.getColumnName(i + 1);
          Field field = userClass.getDeclaredField(columnName);
          field.setAccessible(true);
          field.set(user, resultSet.getObject(i + 1));
        }
        users.add(user);
      }
      return users;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (resultSet != null) {
        try {
          resultSet.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (preparedStatement != null) {
        try {
          preparedStatement.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (connection != null) {
        try {
          connection.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }

    return null;
  }
}
