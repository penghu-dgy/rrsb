package io.renren.dao;

import java.util.List;
import java.util.Map;

import io.renren.entity.EmployeeEntity;

/**
 * InnoDB free: 11264 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-07 14:37:31
 */
public interface EmployeeDao extends BaseDao<EmployeeEntity> {
	EmployeeEntity queryByPhone(String phone);
	List<EmployeeEntity> queryObjectByCondition(Map<String,Object> map);
	List<EmployeeEntity> queryObject2(Map<String,Object> map);
	long login(String mobile, String password);
	List<EmployeeEntity> queryBack(Map<String,Object> map);
}
