package com.alfred.mp.service.impl;

import com.alfred.mp.beans.Employee;
import com.alfred.mp.mapper.EmployeeMapper;
import com.alfred.mp.service.EmployeeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Alfred.Ning
 * @since 2023-09-17
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
