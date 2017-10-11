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
import io.renren.entity.EmployeeEntity;
import io.renren.entity.EnterpriseEntity;
import io.renren.entity.JobEntity;
import io.renren.service.EnterpriseService;
import io.renren.service.JobService;

import io.renren.utils.DateUtils;
import io.renren.utils.R;

@RestController
@RequestMapping("/api")
public class ApiJobController {
	@Autowired
	private JobService jobservice;
	
	@Autowired
	private EnterpriseService entSerivce;
	
	
	/**
	 * 	展示招聘信息 	
	 * @param emp
	 * @return
	 * @author xiongwenjin 2017-4-12
	 */
    @GetMapping("jobList")
	public R jobList(@LoginUser JobEntity job,String city, String bz1, HttpServletResponse response){
    	
    	
    		Map<String, Object> map=new HashMap<String,Object>();
    		
    		map.put("city",city);
    		map.put("bz1",bz1);
    		map.put("jobState","2");
    		System.out.println("city="+city);
    		System.out.println("jobState="+job.getJobState());
    		
    		//跨域问题
    		response.setHeader("Access-Control-Allow-Origin","*");
    		
    		List<Map<String, Object>> ap=jobservice.queryMap(map);
    		System.out.println("ap="+ap);
    		return R.ok().put("data",ap);
    	
    		
	}
    
    /**
     * 发布招聘信息
     * @param emp
     * @param recruit
     * @return
     * @author xiongwenjin 2017-4-12
     */
    @GetMapping("jobAdd")
    public R jobAdd(@LoginUser EmployeeEntity emp, JobEntity job, HttpServletResponse response){
    	System.out.println("hello");
    	System.out.println("PubId="+emp.getEmpid());
    	
    	
    	
    	//获取企业ID
    	Long entid = emp.getEmpent();
    	//通过企业ID 能获取到一条企业的对象
    	EnterpriseEntity entity = entSerivce.queryObject(entid);
    	
    	job.setPubId(emp.getEmpid());
    	job.setPubName(emp.getEmpname());
    	job.setPubPhone(emp.getEmpphone());
    	job.setCompany(entity.getEntname());//获取企业名称
    	
    	System.out.println("Company="+entity.getEntname());
    	
    	job.setJobState("1");
    	String startTime = DateUtils.format(new Date(),DateUtils.DATE_PATTERN);
    	job.setJobPubTime(startTime);
    	//将开始时间增加6天
    	String jobExpireTime = DateUtils.getTimeDelayDay(startTime, 6);
    	job.setJobExpireTime(jobExpireTime);
    	jobservice.save(job);
    	response.setHeader("Access-Control-Allow-Origin","*");
    	return R.ok();
    }
    
    /**
     * 查看详情
     * @param recruit
     * @return
     * @author xiongwenjin 2017-4-12
     */
    @GetMapping("jobDetails")
    public R jobDetails(Long jobId, HttpServletResponse response){
    	Map<String,Object> map = new HashMap<String,Object>();
    	System.out.println("jobId="+jobId);
    	
    	
    	map.put("jobId",jobId);
    	
    	List<Map<String,Object>> list=jobservice.queryMap(map);
    	System.out.println("job="+list);
    	response.setHeader("Access-Control-Allow-Origin","*");
    	return R.ok().put("data", list);
    }
    
    @GetMapping("myJobList")
    public R myJobList(@LoginUser EmployeeEntity emp, HttpServletResponse response){
    	Map<String, Object> map = new HashMap<String, Object>();
    	System.out.println("pubId="+emp.getEmpid());
    	map.put("pubId", emp.getEmpid());
    	List<Map<String, Object>> list = jobservice.queryMap(map);
    	response.setHeader("Access-Control-Allow-Origin","*");
    	
    	for(Map<String,Object> maps :list){
    		
    		if(maps.get("job_state").equals("1")){
    			maps.put("job_state", "申请中");
    		}
    		else if(((String)maps.get("job_state")).equals("2")){
    			maps.put("job_state", "已通过");
    		}
    		else if(((String)maps.get("job_state")).equals("3")){
    			maps.put("job_state", "未通过");
    		}
    		 else{
    			 maps.put("job_state", "已过期");
    		 }
    		System.out.println("maps="+maps);
    		System.out.println("job_state="+maps.get("job_state"));
    	}
    	
    	return R.ok().put("data", list);
    }
    
    
}
