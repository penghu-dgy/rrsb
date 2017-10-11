package io.renren.service;

import io.renren.entity.NoticeEntity;

import java.util.List;
import java.util.Map;

/**
 * InnoDB free: 11264 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-11 14:57:11
 */
public interface NoticeService {
	
	NoticeEntity queryObject(Long noticeid);
	
	List<NoticeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(NoticeEntity notice);
	
	void update(NoticeEntity notice);
	
	void delete(Long noticeid);
	
	void deleteBatch(Long[] noticeids);
	
}
