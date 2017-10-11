package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.ActivityDao;
import io.renren.entity.ActivityEntity;
import io.renren.service.ActivityService;



@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
	@Autowired
	private ActivityDao activityDao;
	
	@Override
	public ActivityEntity queryObject(Long actid){
		return activityDao.queryObject(actid);
	}
	
	@Override
	public List<ActivityEntity> queryList(Map<String, Object> map){
		return activityDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return activityDao.queryTotal(map);
	}
	
	@Override
	public void save(ActivityEntity activity){
		activityDao.save(activity);
	}
	
	@Override
	public void update(ActivityEntity activity){
		activityDao.update(activity);
	}
	
	@Override
	public void delete(Long actid){
		activityDao.delete(actid);
	}
	
	@Override
	public void deleteBatch(Long[] actids){
		activityDao.deleteBatch(actids);
	}

	@Override
	public List<Map<String,Object>> queryActivity(Map<String,Object> map) {
		return activityDao.queryActivity(map);
	}

	@Override
	public List<ActivityEntity> queryLists(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return activityDao.queryLists(map);
	}

	@Override
	public List<Map<String, Object>> backSee(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return activityDao.backSee(map);
	}

}
