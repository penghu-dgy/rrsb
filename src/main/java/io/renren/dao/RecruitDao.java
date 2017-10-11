package io.renren.dao;

import java.util.List;
import java.util.Map;

import io.renren.entity.RecruitEntity;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-14 11:37:00
 */
public interface RecruitDao extends BaseDao<RecruitEntity> {
	List<Map<String, Object>> queryMap(Map<String, Object> map);
}
