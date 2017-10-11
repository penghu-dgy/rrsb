package io.renren.dao;


import java.util.List;
import java.util.Map;

import io.renren.entity.PayEntity;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-13 15:36:00
 */
public interface PayDao extends BaseDao<PayEntity> {
	List<Map<String,Object>> queryByCondition(Map<String,Object> map);
	List<Map<String,Object>> payExcel(Map<String,Object> map);

	List<Map<String,Object>> queryBack(Map<String,Object> map);
}
