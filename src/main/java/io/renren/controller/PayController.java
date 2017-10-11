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
import io.renren.entity.PayEntity;
import io.renren.service.EmployeeService;
import io.renren.service.EnterpriseService;
import io.renren.service.PayService;
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
@RequestMapping("pay")
public class PayController {
	@Autowired
	private PayService payService;
	
	@Autowired
	private EnterpriseService entService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("pay:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
        Long u_id=ShiroUtils.getUserId();
        EnterpriseEntity ent=new EnterpriseEntity();
        ent.setEntemp(u_id);
         Long c=entService.getEntId(ent);

         query.put("entId", c);
		List<Map<String, Object>> payList = payService.queryBack(query);
		int total = payService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(payList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	
	@ResponseBody
	@RequestMapping("/list1")
	public R list1(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
        List<PayEntity> payList = payService.queryList(query);
		int total = payService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(payList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{payid}")
	@RequiresPermissions("pay:info")
	public R info(@PathVariable("payid") Long payid){
		PayEntity pay = payService.queryObject(payid);
		
		return R.ok().put("pay", pay);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("pay:save")
	public R save(@RequestBody PayEntity pay){
		payService.save(pay);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("pay:update")
	public R update(@RequestBody PayEntity pay){
		payService.update(pay);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("pay:delete")
	public R delete(@RequestBody Long[] payids){
		payService.deleteBatch(payids);
		
		return R.ok();
	}
	
}
