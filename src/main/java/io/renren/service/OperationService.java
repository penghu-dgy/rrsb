package io.renren.service;

import io.renren.entity.OperationEntity;

import java.util.List;
import java.util.Map;

/**
 * InnoDB free: 11264 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-07 14:37:32
 */
public interface OperationService {
	
	OperationEntity queryObject(Long operid);
	
	List<OperationEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OperationEntity operation);
	
	void update(OperationEntity operation);
	
	void delete(Long operid);
	
	void deleteBatch(Long[] operids);
	
	List<Map<String, Object>> queryOperate(Map<String, Object> map);
}
