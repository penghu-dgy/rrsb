package io.renren.service;

import io.renren.entity.WorkorderEntity;
import io.renren.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-13 20:56:38
 */
public interface WorkorderService {

	WorkorderEntity queryObject(String orderid);
	
	Map<String,Object> queryObject1(String orderid);
	
	
	List<WorkorderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(WorkorderEntity workorder);
	
	void update(WorkorderEntity workorder);
	
	void delete(String orderid);
	
	void deleteBatch(String[] orderids);
	
	List<Map<String, Object>> queryWorkorder(Map<String, Object> map);

	List<Map<String, Object>> searcher(Query query);
}
