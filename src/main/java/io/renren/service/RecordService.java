package io.renren.service;

import io.renren.entity.RecordEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * InnoDB free: 11264 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-07 14:37:32
 */
public interface RecordService {
	
	RecordEntity queryObject(Long recordid);
	
	List<RecordEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(RecordEntity record);
	
	void update(RecordEntity record);
	
	void delete(Long recordid);
	
	void deleteBatch(Long[] recordids);
	
	List<Map<String, Object>> queryListAndEmp(Map<String, Object> map);
}
