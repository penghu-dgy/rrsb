package io.renren.service;

import io.renren.entity.ActivityEntity;
import io.renren.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * InnoDB free: 11264 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-07 14:37:32
 */
public interface ActivityService {
	
	List<Map<String,Object>> queryActivity(Map<String,Object> map);
	
	ActivityEntity queryObject(Long actid);
	
	List<ActivityEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ActivityEntity activity);
	
	void update(ActivityEntity activity);
	
	void delete(Long actid);
	
	void deleteBatch(Long[] actids);

	List<ActivityEntity> queryLists(Map<String,Object> map);

	List<Map<String,Object>> backSee(Map<String,Object> map);
}
