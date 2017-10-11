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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.renren.utils.annotation.LoginUser;
import io.renren.entity.ActivityEntity;
import io.renren.entity.EmployeeEntity;
import io.renren.service.ActivityService;
import io.renren.service.EmployeeService;
import io.renren.utils.R;

@RestController
@RequestMapping("/api")
public class ApiActivityController {
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private EmployeeService employeeService;
	
	
	
	@GetMapping("employeeAllList")
	public R employeeAllList(@LoginUser EmployeeEntity emp, Long entid){
		//System.out.println("entid="+entid);
		Long empid = emp.getEmpid();
		//res.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empent",entid);
		
		List<EmployeeEntity> list = employeeService.queryList(map);
		map.put("userid", empid);
		map.put("data", list);
		return  R.ok(map);
		
	}
	
	//@IgnoreAuth
  /*  @PostMapping("uploadPic")
    public R uploadPic(@RequestParam("pic") MultipartFile[] files,HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException{
    	response.setHeader("Access-Control-Allow-Origin","*");
		String username = request.getParameter("username");
		String url = "";
		//将文件保存在服务器中 （先建一个文件夹来保存文件，这个文件夹希望和项目名称在同一级目录下）
		String path = request.getSession().getServletContext().getRealPath("upload");
		
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort();
		
		//System.out.println("basePath="+basePath);
		String filePath = path.split("renren-web")[0]+"pics";
		//System.out.println("servletPath="+path);
		//System.out.println("username="+username);
		//先判断文件夹是否存在，如果不存在就创建一个，存在不管
		File dirFile  = new File(filePath);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		for(MultipartFile file : files){
			String fileName = file.getOriginalFilename();
			//System.out.println("fileName="+fileName);
			String format=fileName.substring(fileName.lastIndexOf(".")+1);
			//System.out.println("fileNameFormat="+format);
			//获取UUID
			UUID id=UUID.randomUUID();
			String uuid = id.toString().replace("-", "");
			//拼接一个完整的字符串
			String _file = filePath+"\\"+uuid+"."+format;
			System.out.println("_file="+_file);
			//在服务器端保存起来
			file.transferTo(new File(_file));
			//将文件的信息保存在数据库中
			
			url =basePath+ "/pics/"+uuid+"."+format;
			//System.out.println("url="+url);
		}
		
        return R.ok().put("url",url);
    }
	*/
	
	/**
	 * 通过actid查找一条活动
	 * @param res
	 * @param aid
	 * @return
	 * @author liyaguang 2017-04-14
	 */
	@GetMapping("activityById")
	public R activityById(HttpServletResponse res, Long actid){
		res.setHeader("Access-Control-Allow-Origin", "*");
		//ActivityEntity act = activityService.queryObject(actid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("actid", actid);
		List<Map<String, Object>> list = activityService.queryActivity(map);
		
	//System.out.println("aaa```````"+ list.get(0).get("actChecker"));
	//System.out.println(list);
		map.put("list", list);
		map.put("check", employeeService.queryObject((Long) list.get(0).get("actChecker")).getEmpname());
		return R.ok().put("data", map);
	}
	
	/**
	 * 向activity数据表插入一条数据
	 * @param token
	 * @param act
	 * @return
	 * @author liyaguang 2017-4-10
	 */
	/*@PostMapping("activityAdd")
	public R activityAdd(@RequestParam("pic") MultipartFile[] files,HttpServletRequest req,HttpServletResponse res, @LoginUser EmployeeEntity emp, ActivityEntity act)throws IllegalStateException, IOException{
		res.setHeader("Access-Control-Allow-Origin", "*");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String actpublishtime = sdf.format(date);
		long id = emp.getEmpid();
		act.setActpublisher(id);
		act.setActstate((long) 2);
		act.setActpublishtime(actpublishtime);
		//System.out.println("files="+files.toString());
		if(files != null){
			String url = "";
			//将文件保存在服务器中 （先建一个文件夹来保存文件，这个文件夹希望和项目名称在同一级目录下）
			String path = req.getSession().getServletContext().getRealPath("upload");
			
			String basePath = req.getScheme() + "://"
					+ req.getServerName() + ":" + req.getServerPort();
			
			//System.out.println("basePath="+basePath);
			String filePath = path.split("renren-web")[0]+"pics";
			//System.out.println("servletPath="+path);
			//先判断文件夹是否存在，如果不存在就创建一个，存在不管
			File dirFile  = new File(filePath);
			if(!dirFile.exists()){
				dirFile.mkdirs();
			}
			for(MultipartFile file : files){
				String fileName = file.getOriginalFilename();
				//System.out.println("fileName1="+(fileName==null));
				//System.out.println("fileName2="+(fileName==""));
				String format=fileName.substring(fileName.lastIndexOf(".")+1);
				//System.out.println("fileNameFormat="+format);
				if(fileName != ""){
					//获取UUID
					UUID uid=UUID.randomUUID();
					String uuid = uid.toString().replace("-", "");
					//拼接一个完整的字符串
					String _file = filePath+"\\"+uuid+"."+format;
					System.out.println("_file="+_file);
					//在服务器端保存起来
					file.transferTo(new File(_file));
					//将文件的信息保存在数据库中
					url =basePath+ "/pics/"+uuid+"."+format;
					//System.out.println("url="+url);
				}
				act.setActaddress(url);
			}
		}
		activityService.save(act);
		return R.ok();
	}*/
	
	
	
	
	@PostMapping("activityAddnew")
	public R activityAdds(@LoginUser EmployeeEntity emp,ActivityEntity act){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String actpublishtime = sdf.format(date);
		long id = emp.getEmpid();
		act.setActpublisher(id);
		act.setActstate((long) 2);
		act.setActpublishtime(actpublishtime);
		activityService.save(act);
		
		return R.ok();
	}
	
	
	/**
	 * 按活动的分类查询activity数据表
	 * @param token
	 * @param type
	 * @return
	 * @author liyaguang 2017-4-10
	 *//*
	@PostMapping("activityListByType")
	@ResponseBody
	public R activityListByType(@LoginUser EmployeeEntity emp, int type){
		Long empid = emp.getEmpid();
		List<ActivityEntity> list = null;
		if(type == 1){
			//我发起的
			list = activityService.queryMyAct(empid);
		} else  if(type == 2){
			//我收到的
			list = activityService.queryMyReceive(empid);
		} else {
			//待我审批
			list = activityService.queryMyCheck(empid);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return R.ok(map);
	}*/
	
	/**
	 * 查询审批列表的信息
	 * @param token
	 * @return
	 * @author liyaguang  2017-4-12
	 */
	@GetMapping("activityAllList")
	public R activityAllList(@LoginUser EmployeeEntity emp,Long type){
		
		Long empid = emp.getEmpid();
		//查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = null;
		if(type==1){
			//我发起的
			//System.out.println("1");
			map.put("actpublisher", empid);
		} else if(type==2){
			//我收到的
		//	System.out.println("2");
			map.put("actchecker", empid);
		} else if(type==3){
			//待我审批的
		//	System.out.println("3");
			map.put("actstate", 2);
			map.put("actchecker", empid);
		} else {
			//我所有的审批
		//	System.out.println("4");
			map.put("actpublisher", empid);
			map.put("actchecker", empid);
		}
		list = activityService.queryActivity(map);
		Map<String,Object> _map = new HashMap<String,Object>();
		R r = R.ok();
		r.put("emp", emp);
		r.put("data", list);
		/*for(Map<String, Object> a:list){
			System.out.println("hahahaha"+a.get("empEnt"));
		}*/
		return r;
	}
	
	
	/**
	 * 修改审批列表的信息
	 * @param token
	 * @return
	 * @author liyaguang  2017-4-12
	 */
	@GetMapping("ActivityUpdate")
	public R update( Long empid, ActivityEntity act){
		
		Long actstate = act.getActstate();
		if(empid != null){
			act.setActchecker(empid);
		} else {
			act.setActstate(actstate);
		}
		activityService.update(act);
		return R.ok();
	}
	
	/**
	 * 修改activity数据表中的活动状态
	 * @param token
	 * @param actid
	 * @param actstate
	 * @return
	 * @author liyaguang 2017-4-10
	 */
	/*@PostMapping("updateState")
	public R updateState(@LoginUser EmployeeEntity emp, Long actid, Long actstate){
		Long empid = emp.getEmpid();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empid", empid);
		map.put("actid", actid);
		map.put("actstate", actstate);
		//activityService.updateState(map);
		return R.ok();
	}*/
	
	/**
	 * 修改activity数据表中的审批人信息
	 * @param emp
	 * @param actid
	 * @param empid
	 * @return
	 * @author liyaguang 2017-4-11
	 */
	/*@PostMapping("updateActChecker")
	public R  updateActChecker(@LoginUser EmployeeEntity emp, Long actid, Long empid){
		Long id = emp.getEmpid();
		Map<String, Object> map = new  HashMap<String, Object>();
		map.put("id", id);
		map.put("actid", actid);
		map.put("empid", empid);
		activityService.updateActPublisher(map);
		return R.ok();
	}*/
	
}
