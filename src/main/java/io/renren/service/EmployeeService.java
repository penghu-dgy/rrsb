package io.renren.service;

import io.renren.entity.EmployeeEntity;

import java.util.List;
import java.util.Map;

/**
 * InnoDB free: 11264 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-07 14:37:31
 */
public interface EmployeeService {

	
	List<EmployeeEntity> queryObjectByCondition(Map<String,Object> map);
	List<EmployeeEntity> queryBack(Map<String,Object> map);
	
	EmployeeEntity queryByPhone(String phone);
	
	long login(String mobile, String password);
	
	EmployeeEntity queryObject(Long empid);
	
	List<EmployeeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(EmployeeEntity employee);
	
	void update(EmployeeEntity employee);
	
	void delete(Long empid);
	
	void deleteBatch(Long[] empids);
}
