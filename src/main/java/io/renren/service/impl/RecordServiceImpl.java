package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.renren.dao.RecordDao;
import io.renren.entity.RecordEntity;
import io.renren.service.RecordService;



@Service("recordService")
public class RecordServiceImpl implements RecordService {
	@Autowired
	private RecordDao recordDao;
	
	@Override
	public RecordEntity queryObject(Long recordid){
		return recordDao.queryObject(recordid);
	}
	
	@Override
	public List<RecordEntity> queryList(Map<String, Object> map){
		return recordDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return recordDao.queryTotal(map);
	}
	
	@Override
	public void save(RecordEntity record){
		recordDao.save(record);
	}
	
	@Override
	public void update(RecordEntity record){
		recordDao.update(record);
	}
	
	@Override
	public void delete(Long recordid){
		recordDao.delete(recordid);
	}
	
	@Override
	public void deleteBatch(Long[] recordids){
		recordDao.deleteBatch(recordids);
	}

	@Override
	public List<Map<String, Object>> queryListAndEmp(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return recordDao.queryListAndEmp(map);
	}
	
}
