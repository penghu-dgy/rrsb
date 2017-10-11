package io.renren.service;

import io.renren.entity.EnterpriseEntity;

import java.util.List;
import java.util.Map;

/**
 * InnoDB free: 11264 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-07 14:37:32
 */
public interface EnterpriseService {
	List<EnterpriseEntity> queryListByCondition(EnterpriseEntity enterprise);
	
	EnterpriseEntity queryObject(Long entid);
	
	List<EnterpriseEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(EnterpriseEntity enterprise);
	
	void update(EnterpriseEntity enterprise);
	
	void delete(Long entid);
	
	void deleteBatch(Long[] entids);
	
	long getEntId(EnterpriseEntity enterprise);
}
