package com.nq.base.dao;

import com.nq.base.po.User;
import java.util.List;
import java.util.Map;

/**
 * @author Alfred.Ning
 * @since 2024年01月18日 08:42:00
 */
public interface UserDao {
  List<User> queryUserList(Map<String,Object> param);

}
