package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.WorkDao;
import io.renren.entity.WorkEntity;
import io.renren.service.WorkService;



@Service("workService")
public class WorkServiceImpl implements WorkService {
	@Autowired
	private WorkDao workDao;
	
	@Override
	public WorkEntity queryObject(Long workid){
		return workDao.queryObject(workid);
	}
	
	@Override
	public List<WorkEntity> queryList(Map<String, Object> map){
		return workDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return workDao.queryTotal(map);
	}
	
	@Override
	public void save(WorkEntity work){
		workDao.save(work);
	}
	
	@Override
	public void update(WorkEntity work){
		workDao.update(work);
	}
	
	@Override
	public void delete(Long workid){
		workDao.delete(workid);
	}
	
	@Override
	public void deleteBatch(Long[] workids){
		workDao.deleteBatch(workids);
	}

	@Override
	public void deleteCustBatch(Long workcust) {
		workDao.deleteCustBatch(workcust);
		
	}

	
	
	
}
