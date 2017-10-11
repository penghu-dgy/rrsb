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

import io.renren.entity.OperationEntity;
import io.renren.service.OperationService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * InnoDB free: 11264 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-07 14:37:32
 */
@Controller
@RequestMapping("operation")
public class OperationController {
	@Autowired
	private OperationService operationService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("operation:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<OperationEntity> operationList = operationService.queryList(query);
		int total = operationService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(operationList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{operid}")
	@RequiresPermissions("operation:info")
	public R info(@PathVariable("operid") Long operid){
		OperationEntity operation = operationService.queryObject(operid);
		
		return R.ok().put("operation", operation);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("operation:save")
	public R save(@RequestBody OperationEntity operation){
		operationService.save(operation);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("operation:update")
	public R update(@RequestBody OperationEntity operation){
		operationService.update(operation);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("operation:delete")
	public R delete(@RequestBody Long[] operids){
		operationService.deleteBatch(operids);
		
		return R.ok();
	}
	
}
