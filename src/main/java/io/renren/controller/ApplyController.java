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

import io.renren.entity.ApplyEntity;
import io.renren.service.ApplyService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-11 17:00:06
 */
@Controller
@RequestMapping("apply")
public class ApplyController {
	@Autowired
	private ApplyService applyService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("apply:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<Map<String, Object>> applyList = applyService.bgList(query);
		int total = applyService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(applyList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{appId}")
	@RequiresPermissions("apply:info")
	public R info(@PathVariable("appId") Long appId){
		ApplyEntity apply = applyService.queryObject(appId);
		
		return R.ok().put("apply", apply);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("apply:save")
	public R save(@RequestBody ApplyEntity apply){
		applyService.save(apply);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("apply:update")
	public R update(@RequestBody ApplyEntity apply){
		applyService.update(apply);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("apply:delete")
	public R delete(@RequestBody Long[] appIds){
		applyService.deleteBatch(appIds);
		
		return R.ok();
	}
	
}
