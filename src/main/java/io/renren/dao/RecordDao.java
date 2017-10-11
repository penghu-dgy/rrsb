package io.renren.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.renren.entity.RecordEntity;

/**
 * InnoDB free: 11264 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-07 14:37:32
 */
public interface RecordDao extends BaseDao<RecordEntity> {
	List<Map<String, Object>> queryListAndEmp(Map<String, Object> map);
}
