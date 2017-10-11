package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.EnterpriseDao;
import io.renren.entity.EnterpriseEntity;
import io.renren.service.EnterpriseService;



@Service("enterpriseService")
public class EnterpriseServiceImpl implements EnterpriseService {
	@Autowired
	private EnterpriseDao enterpriseDao;
	
	@Override
	public EnterpriseEntity queryObject(Long entid){
		return enterpriseDao.queryObject(entid);
	}
	
	@Override
	public List<EnterpriseEntity> queryList(Map<String, Object> map){
		return enterpriseDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return enterpriseDao.queryTotal(map);
	}
	
	@Override
	public void save(EnterpriseEntity enterprise){
		enterpriseDao.save(enterprise);
	}
	
	@Override
	public void update(EnterpriseEntity enterprise){
		enterpriseDao.update(enterprise);
	}
	
	@Override
	public void delete(Long entid){
		enterpriseDao.delete(entid);
	}
	
	@Override
	public void deleteBatch(Long[] entids){
		enterpriseDao.deleteBatch(entids);
	}

	@Override
	public long getEntId(EnterpriseEntity enterprise){
		
		return enterpriseDao.getEntId(enterprise);
		
	}

	@Override
	public List<EnterpriseEntity> queryListByCondition(EnterpriseEntity enterprise) {
		// TODO Auto-generated method stub
		return enterpriseDao.queryListByCondition(enterprise);
	}
}
