package io.renren.service;

import io.renren.entity.RecruitEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-14 11:37:00
 */
public interface RecruitService {
	
	RecruitEntity queryObject(Long recruitid);
	
	List<RecruitEntity> queryList(Map<String, Object> map);
	
	List<Map<String, Object>> queryMap(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(RecruitEntity recruit);
	
	void update(RecruitEntity recruit);
	
	void delete(Long recruitid);
	
	void deleteBatch(Long[] recruitids);
}
