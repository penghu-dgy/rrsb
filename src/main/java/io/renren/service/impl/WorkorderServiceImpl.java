package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.WorkorderDao;
import io.renren.entity.WorkorderEntity;
import io.renren.service.WorkorderService;
import io.renren.utils.Query;



@Service("workorderService")
public class WorkorderServiceImpl implements WorkorderService {
	@Autowired
	private WorkorderDao workorderDao;
	
	@Override
	public Map<String,Object> queryObject1(String orderid){
		return workorderDao.queryObject1(orderid);
	}
	
	@Override
	public List<WorkorderEntity> queryList(Map<String, Object> map){
		return workorderDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return workorderDao.queryTotal(map);
	}
	
	@Override
	public void save(WorkorderEntity workorder){
		workorderDao.save(workorder);
	}
	
	@Override
	public void update(WorkorderEntity workorder){
		workorderDao.update(workorder);
	}
	
	@Override
	public void delete(String orderid){
		workorderDao.delete(orderid);
	}
	
	@Override
	public void deleteBatch(String[] orderids){
		workorderDao.deleteBatch(orderids);
	}

	@Override
	public List<Map<String, Object>> queryWorkorder(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return workorderDao.queryWorkorder(map);
	}

	@Override
	public List<Map<String, Object>> searcher(Query query) {
		// TODO Auto-generated method stub
		return workorderDao.searcher(query);
	}

	@Override
	public WorkorderEntity queryObject(String orderid) {
		// TODO Auto-generated method stub
		return workorderDao.queryObject(orderid);
	}




	
}
