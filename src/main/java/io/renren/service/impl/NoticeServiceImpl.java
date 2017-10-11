package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.NoticeDao;
import io.renren.entity.NoticeEntity;
import io.renren.service.NoticeService;



@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeDao noticeDao;
	
	@Override
	public NoticeEntity queryObject(Long noticeid){
		return noticeDao.queryObject(noticeid);
	}
	
	@Override
	public List<NoticeEntity> queryList(Map<String, Object> map){
		return noticeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return noticeDao.queryTotal(map);
	}
	
	@Override
	public void save(NoticeEntity notice){
		noticeDao.save(notice);
	}
	
	@Override
	public void update(NoticeEntity notice){
		noticeDao.update(notice);
	}
	
	@Override
	public void delete(Long noticeid){
		noticeDao.delete(noticeid);
	}
	
	@Override
	public void deleteBatch(Long[] noticeids){
		noticeDao.deleteBatch(noticeids);
	}


	
	
}
