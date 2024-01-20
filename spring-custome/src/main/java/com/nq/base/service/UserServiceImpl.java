package com.nq.base.service;

import com.nq.base.dao.UserDao;
import com.nq.base.po.User;
import java.util.List;
import java.util.Map;
import lombok.Setter;

/**
 * @author Alfred.Ning
 * @since 2024年01月18日 22:38:00
 */
public class UserServiceImpl implements UserService{

  @Setter
  private UserDao userDao;

  @Override
  public List<User> queryUserList(Map<String, Object> param) {
    return userDao.queryUserList(param);
  }
}
