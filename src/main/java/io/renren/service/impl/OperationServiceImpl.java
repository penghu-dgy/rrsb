package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.OperationDao;
import io.renren.entity.OperationEntity;
import io.renren.service.OperationService;



@Service("operationService")
public class OperationServiceImpl implements OperationService {
	@Autowired
	private OperationDao operationDao;
	
	@Override
	public OperationEntity queryObject(Long operid){
		return operationDao.queryObject(operid);
	}
	
	@Override
	public List<OperationEntity> queryList(Map<String, Object> map){
		return operationDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return operationDao.queryTotal(map);
	}
	
	@Override
	public void save(OperationEntity operation){
		operationDao.save(operation);
	}
	
	@Override
	public void update(OperationEntity operation){
		operationDao.update(operation);
	}
	
	@Override
	public void delete(Long operid){
		operationDao.delete(operid);
	}
	
	@Override
	public void deleteBatch(Long[] operids){
		operationDao.deleteBatch(operids);
	}

	@Override
	public List<Map<String, Object>> queryOperate(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return operationDao.queryOperate(map);
	}
	
}
