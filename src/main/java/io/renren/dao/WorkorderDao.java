package io.renren.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.renren.dao.BaseDao;
import io.renren.entity.WorkorderEntity;
import io.renren.utils.Query;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-13 20:56:38
 */
public interface WorkorderDao extends BaseDao<WorkorderEntity> {
	
	WorkorderEntity  queryObject(String orderid);
	List<Map<String, Object>> queryWorkorder(Map<String, Object> map);
	List<Map<String, Object>> searcher(Query query);
	Map<String,Object> queryObject1(String orderid);
}
