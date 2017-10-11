package io.renren.service;

import io.renren.entity.OrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-13 15:51:09
 */
public interface OrderService {
	
	OrderEntity queryObject(String orderid);
	
	List<OrderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OrderEntity order);
	
	void update(OrderEntity order);
	
	void delete(String orderid);
	
	void deleteBatch(String[] orderids);
}
