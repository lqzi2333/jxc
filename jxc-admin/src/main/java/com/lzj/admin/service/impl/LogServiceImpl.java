package com.lzj.admin.service.impl;

import com.lzj.admin.pojo.Log;
import com.lzj.admin.mapper.LogMapper;
import com.lzj.admin.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lqc
 * @since 2023-03-13
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
