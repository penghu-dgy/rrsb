package io.renren.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.renren.entity.CustomerEntity;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-11 14:23:06
 */
public interface CustomerDao extends BaseDao<CustomerEntity> {
	
	//列表页面模糊查询（通过手机号或者是地址或者客户姓名）
	public List<CustomerEntity> findAll(Map<String,Object>parameter);
	
	List<Map<String , Object>> queryLists(Map<String, Object> map);
	
	List<Map<String, Object>> custExcel(Map<String, Object> map);
	
	CustomerEntity selectByPhone(Map<String, Object> map);
	
}
