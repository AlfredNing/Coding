package com.nq.base.service;

import com.nq.base.po.User;
import java.util.List;
import java.util.Map;

/**
 * @author Alfred.Ning
 * @since 2024年01月18日 22:38:00
 */
public interface UserService {
  List<User> queryUserList(Map<String,Object> param);

}
