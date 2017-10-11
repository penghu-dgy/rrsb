package io.renren.dao;

import java.util.List;

import io.renren.entity.EnterpriseEntity;

/**
 * InnoDB free: 11264 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-07 14:37:32
 */
public interface EnterpriseDao extends BaseDao<EnterpriseEntity> {

	long getEntId(EnterpriseEntity enterprise);
	
	List<EnterpriseEntity> queryListByCondition(EnterpriseEntity enterprise);
	

	
}
