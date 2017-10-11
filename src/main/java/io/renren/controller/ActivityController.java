package io.renren.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import io.renren.entity.ActivityEntity;
import io.renren.entity.EnterpriseEntity;
import io.renren.service.ActivityService;
import io.renren.service.EnterpriseService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * InnoDB free: 11264 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-07 14:37:32
 */
@Controller
@RequestMapping("activity")
public class ActivityController {
	@Autowired
	private ActivityService activityService;
	@Autowired
	private EnterpriseService entService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("activity:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
        Long u_id=ShiroUtils.getUserId();
        EnterpriseEntity ent=new EnterpriseEntity();
        ent.setEntemp(u_id);
        System.out.println("u_id===**==="+u_id);
         Long c=entService.getEntId(ent);
         
         query.put("entId", c);

		List<Map<String, Object>> activityList = activityService.backSee(query);
		int total = activityService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(activityList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/list1")
	@RequiresPermissions("activity:list")
	public R list1(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ActivityEntity> activityList = activityService.queryList(query);
		int total =activityService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(activityList, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{actid}")
	@RequiresPermissions("activity:info")
	public R info(@PathVariable("actid") Long actid){
		ActivityEntity activity = activityService.queryObject(actid);
		
		return R.ok().put("activity", activity);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("activity:save")
	public R save(@RequestBody ActivityEntity activity){
		activityService.save(activity);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("activity:update")
	public R update(@RequestBody ActivityEntity activity){
		activityService.update(activity);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("activity:delete")
	public R delete(@RequestBody Long[] actids){
		activityService.deleteBatch(actids);
		
		return R.ok();
	}
	
}
