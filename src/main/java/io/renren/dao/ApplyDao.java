package io.renren.dao;

import java.util.List;
import java.util.Map;

import io.renren.entity.ApplyEntity;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-11 17:00:06
 */
public interface ApplyDao extends BaseDao<ApplyEntity> {
	List<Map<String, Object>> applyselect(Map<String, Object> map);
	List<Map<String , Object>> bgList(Map<String , Object> map);
}
