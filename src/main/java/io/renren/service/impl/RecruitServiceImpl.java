package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.RecruitDao;
import io.renren.entity.RecruitEntity;
import io.renren.service.RecruitService;



@Service("recruitService")
public class RecruitServiceImpl implements RecruitService {
	@Autowired
	private RecruitDao recruitDao;
	
	@Override
	public RecruitEntity queryObject(Long recruitid){
		return recruitDao.queryObject(recruitid);
	}
	
	@Override
	public List<RecruitEntity> queryList(Map<String, Object> map){
		return recruitDao.queryList(map);
	}
	
	@Override
	public List<Map<String, Object>> queryMap(Map<String, Object> map){
		return recruitDao.queryMap(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return recruitDao.queryTotal(map);
	}
	
	@Override
	public void save(RecruitEntity recruit){
		recruitDao.save(recruit);
	}
	
	@Override
	public void update(RecruitEntity recruit){
		recruitDao.update(recruit);
	}
	
	@Override
	public void delete(Long recruitid){
		recruitDao.delete(recruitid);
	}
	
	@Override
	public void deleteBatch(Long[] recruitids){
		recruitDao.deleteBatch(recruitids);
	}
	
}
