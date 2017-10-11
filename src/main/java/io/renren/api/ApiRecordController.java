package io.renren.api;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.renren.utils.annotation.LoginUser;
import io.renren.entity.EmployeeEntity;
import io.renren.entity.RecordEntity;
import io.renren.service.RecordService;
import io.renren.utils.R;
@RestController
@RequestMapping("/api")
public class ApiRecordController {
	
	@Autowired
	private RecordService recordService;
	
	@PostMapping("addRecord")
	public R addRecord(@LoginUser EmployeeEntity employeeEntity,RecordEntity recordEntity,
			@RequestParam("pic") MultipartFile[] files,HttpServletRequest request,HttpServletResponse response){
		
		String url = "";
		//将文件保存在服务器中 （先建一个文件夹来保存文件，这个文件夹希望和项目名称在同一级目录下）
		//String path = request.getSession().getServletContext().getRealPath("upload");
		String path =request.getServletContext().getRealPath("/");
		System.out.println("path"+path);
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort();
		
		System.out.println("=======地址======basePath="+basePath);
		//根据项目名称获取文件的上下文路径，
		String filePath = path.split("renren-web")[0]+"pics";
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
				try {
					file.transferTo(new File(_file));
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//将文件的信息保存在数据库中
				//将文件在服务器中的完整路径返回给页面
				url =basePath+ "/pics/"+uuid+"."+format;	
				System.out.println("url="+url);
			}

		}
        recordEntity.setRecordaddress(url);
        recordEntity.setRecordtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        recordEntity.setRecordemp(employeeEntity.getEmpid());
        recordService.save(recordEntity);
		return R.ok();
	}
	
	@PostMapping("getRecordListByOrderId")
	public R RecordList(@LoginUser EmployeeEntity employeeEntity,String recordorderid){
		Map<String, Object> map=new HashMap<>();
		map.put("recordorderid", recordorderid);
		List<Map<String, Object>> list=recordService.queryListAndEmp(map);
		return R.ok().put("data", list).put("emp", employeeEntity);
	}
	
	@PostMapping("deleteRecordById")
	public R deleteRecordById(@LoginUser EmployeeEntity employeeEntity,String recordId){
		recordService.delete(Long.parseLong(recordId));
		return R.ok();
	}
	
}
