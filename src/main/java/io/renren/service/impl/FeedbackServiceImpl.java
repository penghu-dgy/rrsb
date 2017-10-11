package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.FeedbackDao;
import io.renren.entity.FeedbackEntity;
import io.renren.service.FeedbackService;



@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	private FeedbackDao feedbackDao;
	
	@Override
	public FeedbackEntity queryObject(Long feedbackid){
		return feedbackDao.queryObject(feedbackid);
	}
	
	@Override
	public List<FeedbackEntity> queryList(Map<String, Object> map){
		return feedbackDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return feedbackDao.queryTotal(map);
	}
	
	@Override
	public void save(FeedbackEntity feedback){
		feedbackDao.save(feedback);
	}
	
	@Override
	public void update(FeedbackEntity feedback){
		feedbackDao.update(feedback);
	}
	
	@Override
	public void delete(Long feedbackid){
		feedbackDao.delete(feedbackid);
	}
	
	@Override
	public void deleteBatch(Long[] feedbackids){
		feedbackDao.deleteBatch(feedbackids);
	}

	@Override
	public void saveapi(Map<String, Object> map) {
		feedbackDao.saveapi(map);
	}
	
}
