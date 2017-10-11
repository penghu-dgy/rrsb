package io.renren.service;

import io.renren.entity.FeedbackEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-24 09:11:33
 */
public interface FeedbackService {
	
	FeedbackEntity queryObject(Long feedbackid);
	
	List<FeedbackEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(FeedbackEntity feedback);
	
	void saveapi(Map<String, Object> map);
	
	void update(FeedbackEntity feedback);
	
	void delete(Long feedbackid);
	
	void deleteBatch(Long[] feedbackids);
}
