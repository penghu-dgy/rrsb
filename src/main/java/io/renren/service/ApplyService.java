package io.renren.service;

import io.renren.entity.ApplyEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-11 17:00:06
 */
public interface ApplyService {
	
	ApplyEntity queryObject(Long appId);
	
	List<ApplyEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ApplyEntity apply);
	
	void update(ApplyEntity apply);
	
	void delete(Long appId);
	
	void deleteBatch(Long[] appIds);
	
	List<Map<String, Object>> applyselect(Map<String, Object> map);
	
	List<Map<String , Object>> bgList(Map<String , Object> map);
}
