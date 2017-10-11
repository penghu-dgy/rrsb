package io.renren.service;

import io.renren.entity.WorkEntity;
import java.util.List;
import java.util.Map;

/**
 * InnoDB free: 11264 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-07 14:37:31
 */
public interface WorkService {
	
	WorkEntity queryObject(Long workid);
	
	List<WorkEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(WorkEntity work);
	
	void update(WorkEntity work);
	
	void delete(Long workid);
	
	void deleteBatch(Long[] workids);
	
	void deleteCustBatch(Long workcust);
}
