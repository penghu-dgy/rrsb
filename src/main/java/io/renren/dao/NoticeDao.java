package io.renren.dao;

import java.util.List;
import java.util.Map;

import io.renren.entity.NoticeEntity;

/**
 * InnoDB free: 11264 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-11 14:57:11
 */
public interface NoticeDao extends BaseDao<NoticeEntity> {
	List<Map<String,Object>> queryBack(Map<String,Object> map);
}
