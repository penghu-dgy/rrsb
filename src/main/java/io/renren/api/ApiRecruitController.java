package io.renren.api;



import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.renren.utils.annotation.LoginUser;
import io.renren.entity.EmployeeEntity;

import io.renren.entity.RecruitEntity;
import io.renren.service.RecruitService;
import io.renren.utils.DateUtils;
import io.renren.utils.R;

@RestController
@RequestMapping("/api")
public class ApiRecruitController {
	@Autowired
	private RecruitService recruitservice;
	
	
	/**
	 * 	展示招聘信息 	
	 * @param emp
	 * @return
	 * @author xiongwenjin 2017-4-12
	 */
    @GetMapping("recruitList")
	public R recruitList(@LoginUser EmployeeEntity emp,String entcity,HttpServletResponse response){

		response.setHeader("Access-Control-Allow-Origin","*");
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("recruitemp",emp.getEmpid());
		map.put("entcity", entcity);
		System.out.println("entcity="+entcity);
		
		System.out.println("recruitemp="+emp.getEmpent());
		
		
		List<Map<String, Object>> ap=recruitservice.queryMap(map);
		//跨域问题
		return R.ok().put("data",ap);
	}
    
    /**
     * 发布招聘信息
     * @param emp
     * @param recruit
     * @return
     * @author xiongwenjin 2017-4-12
     */
    @GetMapping("recruitAdd")
    public R recruitAdd(@LoginUser EmployeeEntity emp,RecruitEntity recruit,HttpServletResponse response){
    	response.setHeader("Access-Control-Allow-Origin","*");
    	
    	
    	
    	return R.ok();
    }
    
    /**
     * 查看详情
     * @param recruit
     * @return
     * @author xiongwenjin 2017-4-12
     */
    @GetMapping("recruitDetails")
    public R recruitDetails(Long recruitid, HttpServletResponse response){
    	response.setHeader("Access-Control-Allow-Origin","*");
    	Map<String,Object> map = new HashMap<String,Object>();
    	System.out.println("recruitid="+recruitid);
    	map.put("recruitid",recruitid);
    	List<Map<String,Object>> list=recruitservice.queryMap(map);
    	
    
    	return R.ok().put("data", list);
    }
    
    //上传图片
    @RequestMapping("imgAdd")
	@ResponseBody
	public R  imgAdd(@RequestParam("pic") MultipartFile[] files,@LoginUser EmployeeEntity emp,String uname,HttpServletRequest request, RecruitEntity recruit,HttpServletResponse response) throws IllegalStateException, IOException {
		
		
		response.setHeader("Access-Control-Allow-Origin","*");
		
		String url = "";
		//将文件保存在服务器中 （先建一个文件夹来保存文件，这个文件夹希望和项目名称在同一级目录下）
		String path = request.getSession().getServletContext().getRealPath("upload");
		
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort();
		
		System.out.println("basePath="+basePath);
		//根据项目名称获取文件的上下文路径，
		String filePath = path.split("renren-web")[0]+"pics";
		System.out.println("servletPath="+path);
	
		//先判断文件夹是否存在，如果不存在就创建一个，存在不管
		File dirFile  = new File(filePath);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		for(MultipartFile file : files){
			//获取文件名称
			String fileName = file.getOriginalFilename();
			System.out.println("fileName="+fileName);
			//获取文件格式
			String format=fileName.substring(fileName.lastIndexOf(".")+1);
			System.out.println("fileNameFormat="+format);
			//获取UUID
			UUID id=UUID.randomUUID();
			String uuid = id.toString().replace("-", "");
			//拼接一个完整的文件名称字符串
			String _file = filePath+"\\"+uuid+"."+format;
			System.out.println("_file="+_file);
			//在服务器端保存起来 springmvc框架自己的方法
			file.transferTo(new File(_file));
			//将文件的信息保存在数据库中
			//将文件在服务器中的完整路径返回给页面
			url =basePath+ "/pics/"+uuid+"."+format;
			System.out.println("url="+url);
			recruit.setBz1(url);
		}
		recruit.setRecruitent(emp.getEmpent());
		recruit.setRecruitemp(emp.getEmpid());
		
    	
    	recruit.setRecruittime(DateUtils.format(new Date(),DateUtils.DATE_PATTERN));
   
		
		
		
		recruitservice.save(recruit);	
		return R.ok();

	}
    
    
}
