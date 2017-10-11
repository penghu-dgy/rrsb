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

import io.renren.entity.WorkEntity;
import io.renren.service.WorkService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * InnoDB free: 11264 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-07 14:37:31
 */
@Controller
@RequestMapping("work")
public class WorkController {
	@Autowired
	private WorkService workService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("work:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<WorkEntity> workList = workService.queryList(query);
		int total = workService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(workList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{workid}")
	@RequiresPermissions("work:info")
	public R info(@PathVariable("workid") Long workid){
		WorkEntity work = workService.queryObject(workid);
		
		return R.ok().put("work", work);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("work:save")
	public R save(@RequestBody WorkEntity work){
		workService.save(work);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("work:update")
	public R update(@RequestBody WorkEntity work){
		workService.update(work);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("work:delete")
	public R delete(@RequestBody Long[] workids){
		workService.deleteBatch(workids);
		
		return R.ok();
	}
	
}
