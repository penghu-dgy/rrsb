package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.ApplyDao;
import io.renren.entity.ApplyEntity;
import io.renren.service.ApplyService;



@Service("applyService")
public class ApplyServiceImpl implements ApplyService {
	@Autowired
	private ApplyDao applyDao;
	
	@Override
	public ApplyEntity queryObject(Long appId){
		return applyDao.queryObject(appId);
	}
	
	@Override
	public List<ApplyEntity> queryList(Map<String, Object> map){
		return applyDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return applyDao.queryTotal(map);
	}
	
	@Override
	public void save(ApplyEntity apply){
		applyDao.save(apply);
	}
	
	@Override
	public void update(ApplyEntity apply){
		applyDao.update(apply);
	}
	
	@Override
	public void delete(Long appId){
		applyDao.delete(appId);
	}
	
	@Override
	public void deleteBatch(Long[] appIds){
		applyDao.deleteBatch(appIds);
	}
	
	@Override
	public List<Map<String, Object>> applyselect(Map<String, Object> map){
		return applyDao.applyselect(map);
	}

	@Override
	public List<Map<String, Object>> bgList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return applyDao.bgList(map);
	}
	
}
