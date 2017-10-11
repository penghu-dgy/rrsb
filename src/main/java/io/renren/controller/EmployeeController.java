package io.renren.controller;

import java.util.HashMap;
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

import io.renren.entity.EmployeeEntity;
import io.renren.entity.EnterpriseEntity;
import io.renren.service.EmployeeService;
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
 * @date 2017-04-07 14:37:31
 */
@Controller
@RequestMapping("employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EnterpriseService entService;
	
	/**
	 * 列表
	 * 获取u_id
	 * 通过u_id获取entpriseEntity
	 * 
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("employee:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
     System.out.println(query);
       Long u_id=ShiroUtils.getUserId();
       EnterpriseEntity ent=new EnterpriseEntity();
       ent.setEntemp(u_id);
        Long c=entService.getEntId(ent);
        
        query.put("empent", c);
		List<EmployeeEntity> employeeList = employeeService.queryBack(query);
		int total = employeeList.size();
		
		//通过shiro获取用户id，此id与雇员表的用户id相同
		
		//System.out.println("`````````````"+u_id);
		
		
		PageUtils pageUtil = new PageUtils(employeeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	
	@ResponseBody
	@RequestMapping("/list1")
	@RequiresPermissions("employee:list")
	public R list1(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<EmployeeEntity> employeeList = employeeService.queryList(query);
		int total = employeeService.queryTotal(query);
		
		//通过shiro获取用户id，此id与雇员表的用户id相同
		
		//System.out.println("`````````````"+u_id);
		
		
		PageUtils pageUtil = new PageUtils(employeeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{empid}")
	@RequiresPermissions("employee:info")
	public R info(@PathVariable("empid") Long empid){
		EmployeeEntity employee = employeeService.queryObject(empid);
		
		return R.ok().put("employee", employee);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("employee:save")
	public R save(@RequestBody EmployeeEntity employee){
		employeeService.save(employee);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("employee:update")
	public R update(@RequestBody EmployeeEntity employee){
		employeeService.update(employee);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("employee:delete")
	public R delete(@RequestBody Long[] empids){
		employeeService.deleteBatch(empids);
		
		return R.ok();
	}
	
}
