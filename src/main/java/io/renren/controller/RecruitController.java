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

import io.renren.entity.EnterpriseEntity;
import io.renren.entity.RecruitEntity;
import io.renren.service.EnterpriseService;
import io.renren.service.RecruitService;
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
@RequestMapping("recruit")
public class RecruitController {
	@Autowired
	private RecruitService recruitService;
	@Autowired
	private EnterpriseService entService;
	/**
	 * 列表
	 * 通过=ShiroUtils.getUserId() 获取u_id
	 * 通过uid获取entid
	 * 通过entid获取recruit列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("recruit:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

        Long u_id=ShiroUtils.getUserId();
        EnterpriseEntity ent=new EnterpriseEntity();
        ent.setEntemp(u_id);
         Long c=entService.getEntId(ent);

        //Map<String,Object> map=new HashMap<String,Object>();
        
        query.put("recruitEnt", c);
        
		List<RecruitEntity> recruitList = recruitService.queryList(query);
		int total = recruitService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(recruitList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{recruitid}")
	@RequiresPermissions("recruit:info")
	public R info(@PathVariable("recruitid") Long recruitid){
		RecruitEntity recruit = recruitService.queryObject(recruitid);
		
		return R.ok().put("recruit", recruit);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("recruit:save")
	public R save(@RequestBody RecruitEntity recruit){
		recruitService.save(recruit);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("recruit:update")
	public R update(@RequestBody RecruitEntity recruit){
		recruitService.update(recruit);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("recruit:delete")
	public R delete(@RequestBody Long[] recruitids){
		recruitService.deleteBatch(recruitids);
		
		return R.ok();
	}
	
}
