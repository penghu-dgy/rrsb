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

import io.renren.entity.EnterpriseEntity;
import io.renren.service.EnterpriseService;
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
@RequestMapping("enterprise")
public class EnterpriseController {
	@Autowired
	private EnterpriseService enterpriseService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("enterprise:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<EnterpriseEntity> enterpriseList = enterpriseService.queryList(query);
		int total = enterpriseService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(enterpriseList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{entid}")
	@RequiresPermissions("enterprise:info")
	public R info(@PathVariable("entid") Long entid){
		EnterpriseEntity enterprise = enterpriseService.queryObject(entid);
		
		return R.ok().put("enterprise", enterprise);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("enterprise:save")
	public R save(@RequestBody EnterpriseEntity enterprise){
		enterpriseService.save(enterprise);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("enterprise:update")
	public R update(@RequestBody EnterpriseEntity enterprise){
		enterpriseService.update(enterprise);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("enterprise:delete")
	public R delete(@RequestBody Long[] entids){
		enterpriseService.deleteBatch(entids);
		
		return R.ok();
	}
	
}
