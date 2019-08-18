package com.dyp.serviceImpl;

import com.dyp.entity.DbTable;
import com.dyp.dao.DbTableMapper;
import com.dyp.service.IDbTableService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
