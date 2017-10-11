package io.renren.dao;

import java.util.List;
import java.util.Map;

import io.renren.entity.ActivityEntity;

/**
 * InnoDB free: 11264 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-07 14:37:32
 */
public interface ActivityDao extends BaseDao<ActivityEntity> {
	List<Map<String,Object>> queryActivity(Map<String,Object> map);
	List<Map<String,Object>> backSee(Map<String, Object> map);
	List<ActivityEntity> queryLists(Map<String, Object> map);
}
