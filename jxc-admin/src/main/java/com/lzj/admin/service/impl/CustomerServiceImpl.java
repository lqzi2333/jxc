package com.lzj.admin.service.impl;

import com.lzj.admin.pojo.Customer;
import com.lzj.admin.mapper.CustomerMapper;
import com.lzj.admin.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户表 服务实现类
 * </p>
 *
 * @author lqc
 * @since 2023-03-13
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

}
