package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;


import io.renren.dao.PayDao;
import io.renren.entity.PayEntity;

import io.renren.service.PayService;



@Service("payService")
public class PayServiceImpl implements PayService {
	@Autowired
	private PayDao payDao;
	
	@Override
	public PayEntity queryObject(Long payid){
		return payDao.queryObject(payid);
	}
	
	@Override
	public List<PayEntity> queryList(Map<String, Object> map){
		return payDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return payDao.queryTotal(map);
	}
	
	@Override
	public void save(PayEntity pay){
		payDao.save(pay);
	}
	
	@Override
	public void update(PayEntity pay){
		payDao.update(pay);
	}
	
	@Override
	public void delete(Long payid){
		payDao.delete(payid);
	}
	
	@Override
	public void deleteBatch(Long[] payids){
		payDao.deleteBatch(payids);
	}

	@Override
	public List<Map<String, Object>> queryByCondition(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return payDao.queryByCondition(map);
	}

	@Override
	public List<Map<String, Object>> payExcel(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return payDao.payExcel(map);
	}

	@Override
	public List<Map<String, Object>> queryBack(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return payDao.queryBack(map);
	}

	
	
}
