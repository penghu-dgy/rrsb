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

import io.renren.entity.ApplyEntity;
import io.renren.entity.JobEntity;
import io.renren.service.ApplyService;
import io.renren.service.JobService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-11 17:00:06
 */
@Controller
@RequestMapping("job")
public class JobController {
	@Autowired
	private JobService jobService;
	
	@Autowired
	private ApplyService applyService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("job:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
        //System.out.println("``````````````````");
        //long userId= ShiroUtils.getUserId();
        //query.put("userId", userId);
		List<JobEntity> jobList = jobService.queryList(query);
		int total = jobService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(jobList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/getInfo")
	public R infoApply(Long jobId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("jobId", jobId);
		List<Map<String, Object>> apply = applyService.applyselect(map);
		System.out.println(apply.get(0));
		System.out.println("````````````````"+jobId);
		return R.ok().put("apply", apply);
	}
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{jobId}")
	@RequiresPermissions("job:info")
	public R info(@PathVariable("jobId") Long jobId){
		JobEntity job = jobService.queryObject(jobId);
		
		return R.ok().put("job", job);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("job:save")
	public R save(@RequestBody JobEntity job){
		jobService.save(job);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("job:update")
	public R update(@RequestBody JobEntity job){
		System.out.println("jobId="+job.getJobId());
		System.out.println("jobState="+job.getJobState());
		jobService.update(job);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("job:delete")
	public R delete(@RequestBody Long[] jobIds){
		jobService.deleteBatch(jobIds);
		
		return R.ok();
	}
	
}
