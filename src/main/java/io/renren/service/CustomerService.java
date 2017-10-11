package io.renren.service;

import io.renren.entity.CustomerEntity;
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
public interface CustomerService {
	
	CustomerEntity queryObject(Long custid);
	
	List<CustomerEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CustomerEntity customer);
	
	void update(CustomerEntity customer);
	
	void delete(Long custid);
	
	void deleteBatch(Long[] custids);
	
	//模糊查询的
	public List<CustomerEntity> findAll(Map<String,Object>parameter);

	List<Map<String , Object>> queryLists(Map<String, Object> map);
	
	List<Map<String, Object>> custExcel(Map<String, Object> map);
	
	CustomerEntity selectByPhone(Map<String , Object> map);
}
