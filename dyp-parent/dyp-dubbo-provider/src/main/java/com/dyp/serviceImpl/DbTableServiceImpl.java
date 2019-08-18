package com.dyp.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dyp.dao.DbTableMapper;
import com.dyp.entity.DbTable;
import com.dyp.service.IDbTableService;
//import org.springframework.stereotype.Service;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 * 数据表 服务实现类
 * </p>
 *
 * @author dyp
 * @since 2019-07-28
 */
@Service
public class DbTableServiceImpl extends ServiceImpl<DbTableMapper, DbTable> implements IDbTableService {
	
}
