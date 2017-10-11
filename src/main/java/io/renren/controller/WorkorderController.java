package io.renren.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.entity.WorkorderEntity;
import io.renren.service.EmployeeService;
import io.renren.service.WorkorderService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-17 15:37:03
 */
@RestController
@RequestMapping("workorder")
public class WorkorderController {
	@Autowired
	private WorkorderService workorderService;
	@Autowired
	private EmployeeService e;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
       // System.out.println(query);
        long uid=ShiroUtils.getUserId();
        
        query.put("entId", e.queryObject(uid).getEmpent());
		
        List<Map<String, Object>> workorderList = workorderService.searcher(query);
		
		int total = workorderService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(workorderList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	
	
	@RequestMapping("/list1")
	public R list1(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
       // System.out.println(query);
       
        List<WorkorderEntity> workorderList = workorderService.queryList(query);
		
		int total = workorderService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(workorderList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	
	
	/**
	 * 信息
	 */
	@GetMapping("/info/{orderId}")
	public R info(@PathVariable("orderId") String orderId){
		System.out.println("```````````````"+orderId);
		Map<String,Object> workorder = workorderService.queryObject1(orderId);
		
		return R.ok().put("workorder", workorder);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("workorder:save")
	public R save(@RequestBody WorkorderEntity workorder){
		workorderService.save(workorder);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("workorder:update")
	public R update(){
		WorkorderEntity workorder=new WorkorderEntity();
		workorderService.update(workorder);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("workorder:delete")
	public R delete(@RequestBody String[] orderids){
		workorderService.deleteBatch(orderids);
		
		return R.ok();
	}
	
}
