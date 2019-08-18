package com.dyp.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dyp.dao.DbTableFieldMapper;
import com.dyp.entity.DbTableField;
import com.dyp.service.IDbTableFieldService;
//import org.springframework.stereotype.Service;
import com.alibaba.dubbo.config.annotation.Service;

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
