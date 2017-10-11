package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CustomerDao;
import io.renren.entity.CustomerEntity;
import io.renren.service.CustomerService;



@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public CustomerEntity queryObject(Long custid){
		return customerDao.queryObject(custid);
	}
	
	@Override
	public List<CustomerEntity> queryList(Map<String, Object> map){
		return customerDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return customerDao.queryTotal(map);
	}
	
	@Override
	public void save(CustomerEntity customer){
		customerDao.save(customer);
	}
	
	@Override
	public void update(CustomerEntity customer){
		customerDao.update(customer);
	}
	
	@Override
	public void delete(Long custid){
		customerDao.delete(custid);
	}
	
	@Override
	public void deleteBatch(Long[] custids){
		customerDao.deleteBatch(custids);
	}

	@Override
	public List<CustomerEntity> findAll(Map<String, Object> parameter) {	
		return customerDao.findAll(parameter);
	}

	@Override
	public List<Map<String , Object>> queryLists(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return customerDao.queryLists(map);
	}

	@Override
	public List<Map<String, Object>> custExcel(Map<String, Object> map) {
		
		return customerDao.custExcel(map);
	}

	@Override
	public CustomerEntity selectByPhone(Map<String , Object> map) {
		// TODO Auto-generated method stub
		return customerDao.selectByPhone(map);
	}

	
	
}
