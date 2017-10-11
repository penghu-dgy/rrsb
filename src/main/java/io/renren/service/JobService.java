package io.renren.service;

import io.renren.entity.JobEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-13 13:59:53
 */
public interface JobService {
	
	JobEntity queryObject(Long jobId);
	
	List<JobEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(JobEntity job);
	
	void update(JobEntity job);
	
	void delete(Long jobId);
	
	void deleteBatch(Long[] jobIds);
	
	List<Map<String, Object>> queryMap(Map<String, Object> map);
	
	List<Map<String, Object>> jobMap(Map<String, Object> map);
}
