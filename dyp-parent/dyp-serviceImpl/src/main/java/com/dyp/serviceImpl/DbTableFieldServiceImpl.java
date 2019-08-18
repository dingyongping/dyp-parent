package com.dyp.serviceImpl;

import com.dyp.entity.DbTableField;
import com.dyp.dao.DbTableFieldMapper;
import com.dyp.service.IDbTableFieldService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 设置表字段表 服务实现类
 * </p>
 *
 * @author dyp
 * @since 2019-07-28
 */
@Service
public class DbTableFieldServiceImpl extends ServiceImpl<DbTableFieldMapper, DbTableField> implements IDbTableFieldService {
	
}
