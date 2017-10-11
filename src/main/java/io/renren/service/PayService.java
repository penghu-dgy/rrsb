package io.renren.service;

import io.renren.entity.PayEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-21 09:46:01
 */
public interface PayService {
	
	List<Map<String,Object>> queryByCondition(Map<String,Object> map);
	
	
	PayEntity queryObject(Long payid);
	
	List<PayEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PayEntity pay);
	
	List<Map<String,Object>> queryBack(Map<String,Object> map);
	
	void update(PayEntity pay);
	
	void delete(Long payid);
	
	void deleteBatch(Long[] payids);
		
	List<Map<String,Object>> payExcel(Map<String,Object> map);
}
