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

import io.renren.entity.CustomerEntity;
import io.renren.entity.EnterpriseEntity;
import io.renren.service.CustomerService;
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
@RequestMapping("customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private EnterpriseService entService;
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("customer:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		System.out.println(params);
        Query query = new Query(params);
        Long u_id=ShiroUtils.getUserId();
        EnterpriseEntity ent=new EnterpriseEntity();
        ent.setEntemp(u_id);
        Long c=entService.getEntId(ent);
        
        //Map<String , Object> map=new HashMap<String , Object>();
        query.put("entId", c);
		List<Map<String , Object>> customerList = customerService.queryLists(query);
		int total =customerService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(customerList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{custid}")
	@RequiresPermissions("customer:info")
	public R info(@PathVariable("custid") Long custid){
		CustomerEntity customer = customerService.queryObject(custid);
		
		return R.ok().put("customer", customer);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("customer:save")
	public R save(@RequestBody CustomerEntity customer){
		customerService.save(customer);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("customer:update")
	public R update(@RequestBody CustomerEntity customer){
		customerService.update(customer);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("customer:delete")
	public R delete(@RequestBody Long[] custids){
		customerService.deleteBatch(custids);
		
		return R.ok();
	}
	
}
