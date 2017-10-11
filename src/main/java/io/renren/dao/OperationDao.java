package io.renren.dao;

import java.util.List;
import java.util.Map;

import io.renren.entity.OperationEntity;

/**
 * InnoDB free: 11264 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-07 14:37:32
 */
public interface OperationDao extends BaseDao<OperationEntity> {
	List<Map<String, Object>> queryOperate(Map<String, Object> map);
}
