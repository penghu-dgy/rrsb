package io.renren.api;

import io.renren.utils.annotation.LoginUser;
import io.renren.entity.EmployeeEntity;
import io.renren.entity.WorkEntity;
import io.renren.service.WorkService;
import io.renren.utils.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * 客户的工作记录：
 * @author fengxiongqiang:
 * @date 2017-04-11：
 */
@RestController
@RequestMapping("/api")
public class ApiWorkController {

    @Autowired
    private WorkService workService;

    /**
     * 添加工作记录
     * @throws IOException 
     * @throws IllegalStateException 
     */
   
    @PostMapping("workAdd")
    public R workAdd(@LoginUser EmployeeEntity emp,WorkEntity workEntity,@RequestParam("pic") MultipartFile[] files,HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException{
    	response.setHeader("Access-Control-Allow-Origin","*"); 	
    	System.out.println("==========添加工作记录======================"+workEntity.getWorkcust());
    	//获得当前的跟进人（员工）Id
    	long workemp = emp.getEmpid();
    	workEntity.setWorkemp(workemp);
    	//添加的时间为当前的时间
    	Date d = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String worktime = sdf.format(d);
    	workEntity.setWorktime(worktime);
    	
    	
    	//保存图片的路径
    	String url = "";
		//将文件保存在服务器中 （先建一个文件夹来保存文件，这个文件夹希望和项目名称在同一级目录下）
		String path = request.getSession().getServletContext().getRealPath("upload");
		
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort();
		
		System.out.println("=======地址======basePath="+basePath);
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
			if(fileName!=""){
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
			}
			workEntity.setWorkimage(url);
			
		}
    	workService.save(workEntity);
        return R.ok().put("url",url);
    }
    
    
    @PostMapping("workAddnew")
    public R workAddnew(@LoginUser EmployeeEntity emp,WorkEntity workEntity){
    	long workemp = emp.getEmpid();
    	workEntity.setWorkemp(workemp);
    	//添加的时间为当前的时间
    	Date d = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String worktime = sdf.format(d);
    	workEntity.setWorktime(worktime);
    	workService.save(workEntity);
    	
    	
    	return R.ok();
    }
    
    /**
     * 
     *删除工作记录
     */
    @GetMapping("workDelete")
    public R customerDelete(WorkEntity workEntity,HttpServletResponse response){	
    	response.setHeader("Access-Control-Allow-Origin","*");	
    	System.out.println("=================删除工作记录====="+workEntity.getWorkid());
    	workService.delete(workEntity.getWorkid());	
        return R.ok();
    }
    
     

}
