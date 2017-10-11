package io.renren.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.renren.dao.EmployeeDao;
import io.renren.entity.EmployeeEntity;

import io.renren.service.EmployeeService;
import io.renren.utils.RRException;
import io.renren.utils.validator.Assert;



@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public EmployeeEntity queryObject(Long empid){
		return employeeDao.queryObject(empid);
	}
	
	@Override
	public List<EmployeeEntity> queryList(Map<String, Object> map){
		return employeeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return employeeDao.queryTotal(map);
	}
	
	@Override
	public void save(EmployeeEntity employee){
		employeeDao.save(employee);
	}
	
	@Override
	public void update(EmployeeEntity employee){
		employeeDao.update(employee);
	}
	
	@Override
	public void delete(Long empid){
		employeeDao.delete(empid);
	}
	
	@Override
	public void deleteBatch(Long[] empids){
		employeeDao.deleteBatch(empids);
	}

	@Override
	public long login(String mobile, String password) {
		// TODO Auto-generated method stub
		//获取登陆者的对象
	
		//UserEntity user = queryByMobile(mobile);
		EmployeeEntity user = queryByPhone(mobile);
		Assert.isNull(user, "手机号或密码错误");
		
		//密码错误
		/*if(!user.getEmppassword().equals(DigestUtils.sha256Hex(password))){
			throw new RRException("手机号或密码错误");
		}*/
		if(!user.getEmppassword().equals(password)){
			throw new RRException("手机号或密码错误");
		}

		return user.getEmpid();
		
	}

	@Override
	public EmployeeEntity queryByPhone(String phone) {
		// TODO Auto-generated method stub
		return employeeDao.queryByPhone(phone);
	}

	@Override
	public List<EmployeeEntity> queryObjectByCondition(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return employeeDao.queryObjectByCondition(map);
	}

	@Override
	public List<EmployeeEntity> queryBack(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return employeeDao.queryBack(map);
	}
	
	

	
}
