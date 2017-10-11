package io.renren.dao;

import java.util.List;
import java.util.Map;

import io.renren.entity.JobEntity;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-13 13:59:53
 */
public interface JobDao extends BaseDao<JobEntity> {
	List<Map<String, Object>> queryMap(Map<String, Object> map);
	List<Map<String, Object>> jobMap(Map<String, Object> map);
}
