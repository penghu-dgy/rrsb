package io.renren.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.utils.annotation.LoginUser;
import io.renren.entity.ApplyEntity;
import io.renren.entity.EmployeeEntity;
import io.renren.entity.JobEntity;
import io.renren.service.ApplyService;
import io.renren.utils.DateUtils;
import io.renren.utils.R;

@RestController
@RequestMapping("/api")
public class ApiApplyController {
	@Autowired
	private ApplyService applyservice;
	
	/*
	 * 添加申请
	 */
	@GetMapping("addApply")
    public R addApply(@LoginUser EmployeeEntity emp, ApplyEntity apply, Long jobId, String jobName, JobEntity job, HttpServletResponse response){
    	System.out.println("jobId="+jobId);
    	System.out.println("jobName="+jobName);
    	System.out.println("empid="+emp.getEmpid());
    	
    	Map<String,Object> map = new HashMap<String,Object>();
    	
    	map.put("jobId", jobId);
    	map.put("empId", emp.getEmpid());
    	List<Map<String,Object>> list = applyservice.applyselect(map);
    	System.out.println("kk="+list);
    	if(list.size()==0)
    	{
	    	apply.setJobId(jobId);
	    	apply.setJobName(jobName);
	    	apply.setEmpId(emp.getEmpid());
	    	apply.setEmpName(emp.getEmpname());
	    	apply.setEmpPhone(emp.getEmpphone());
	    	apply.setEmpTime(DateUtils.format(new Date(),DateUtils.DATE_PATTERN));
	    	System.out.println("jobId="+jobId);
	    	System.out.println("jobName="+jobName);
	    	applyservice.save(apply);
	    	
	    	response.setHeader("Access-Control-Allow-Origin","*");
	    	return R.ok().put("data", "申请成功!");
    	}else
    	{
    		return R.ok().put("data", "您已申请!");
    	}
    	
	}
	
	@GetMapping("applyList")
	public R applyList(Long jobId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("jobId", jobId);
		System.out.println("jobId="+jobId);
		List<Map<String, Object>> list = applyservice.applyselect(map);
		System.out.println("list="+list);
		
		return R.ok().put("data", list);
	}
	
	
	
}
