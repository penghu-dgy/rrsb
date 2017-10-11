package io.renren.dao;

import java.util.Map;

import io.renren.entity.FeedbackEntity;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-24 09:11:33
 */
public interface FeedbackDao extends BaseDao<FeedbackEntity> {
	void saveapi(Map<String, Object> map);
}
