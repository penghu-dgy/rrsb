package io.renren.dao;

import io.renren.entity.WorkEntity;

/**
 * InnoDB free: 11264 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-07 14:37:31
 */
public interface WorkDao extends BaseDao<WorkEntity> {
	
	
	//删除客户时删除对应的工作记录
	int deleteCustBatch(Object id);
}
