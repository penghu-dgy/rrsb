package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.JobDao;
import io.renren.entity.JobEntity;
import io.renren.service.JobService;



@Service("jobService")
public class JobServiceImpl implements JobService {
	@Autowired
	private JobDao jobDao;
	
	@Override
	public JobEntity queryObject(Long jobId){
		return jobDao.queryObject(jobId);
	}
	
	@Override
	public List<JobEntity> queryList(Map<String, Object> map){
		return jobDao.queryList(map);
	}
	
	@Override
	public List<Map<String, Object>> queryMap(Map<String, Object> map){
		return jobDao.queryMap(map);
	}
	
	@Override
	public List<Map<String, Object>> jobMap(Map<String, Object> map){
		return jobDao.jobMap(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return jobDao.queryTotal(map);
	}
	
	@Override
	public void save(JobEntity job){
		jobDao.save(job);
	}
	
	@Override
	public void update(JobEntity job){
		jobDao.update(job);
	}
	
	@Override
	public void delete(Long jobId){
		jobDao.delete(jobId);
	}
	
	@Override
	public void deleteBatch(Long[] jobIds){
		jobDao.deleteBatch(jobIds);
	}
	
}
