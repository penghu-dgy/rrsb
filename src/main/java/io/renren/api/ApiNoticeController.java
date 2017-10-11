package io.renren.api;

import io.renren.utils.annotation.IgnoreAuth;
import io.renren.utils.annotation.LoginUser;
import io.renren.entity.EmployeeEntity;
import io.renren.entity.NoticeEntity;
import io.renren.entity.UserEntity;
import io.renren.service.NoticeService;
import io.renren.service.UserService;
import io.renren.utils.DateUtils;
import io.renren.utils.R;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * API测试接口
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:47
 */
@RestController
@RequestMapping("/api")
public class ApiNoticeController {
	
	@Autowired
    private NoticeService noticeService;

    /**
     * 企业对于的所有公告信息
     * @param emp
     * @return
     * @author penghu 2017-4-11
     */
    @GetMapping("noticeListByEntId")
    public R noticeListByEntId(@LoginUser EmployeeEntity emp,HttpServletResponse response){
    	System.out.println("noticeListByEntId");
    	System.out.println("ent ID="+emp.getEmpent());
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("noticeentid", emp.getEmpent());//获取到的员工所对应企业id作为查询条件查找对应的notice记录
    	List<NoticeEntity> list =noticeService.queryList(map);
    	//解决跨域的问题
    	response.setHeader("Access-Control-Allow-Origin","*");
    	
        return R.ok().put("data", list);
    }
    
    

    
    
/*
    @PostMapping("addNotice")
    public R addNotice(@LoginUser EmployeeEntity emp,@RequestParam("pic") MultipartFile[] files,HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException{
    	response.setHeader("Access-Control-Allow-Origin","*");
    	
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String address = "";
		System.out.println("files="+files);
		System.out.println("....length="+files.length);
		if(files.length>0){
			Map<String,Object> url_map = FileUtils.uploadPics(files,request,"renren-web","","");
			address = url_map.get("sub_url").toString();
		}
		
		
		String url = "";
		//将文件保存在服务器中 （先建一个文件夹来保存文件，这个文件夹希望和项目名称在同一级目录下）
		String path = request.getSession().getServletContext().getRealPath("upload");
		String path2 = request.getSession().getServletContext().getRealPath("renren-web");
		System.out.println("path="+path);
		System.out.println("path2="+path2);
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort();
		
		System.out.println("basePath="+basePath);
		//在服务器上的物理地址
		String filePath = path.split("renren-web")[0]+"pics";
		
		File dirFile  = new File(filePath);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		System.out.println("length="+files.length);
	
			for(MultipartFile file : files){
				String fileName = file.getOriginalFilename();
				System.out.println("fileName="+fileName);
				String format=fileName.substring(fileName.lastIndexOf(".")+1);
				System.out.println("fileNameFormat="+format);
				//获取UUID
				UUID id=UUID.randomUUID();
				String uuid = id.toString().replace("-", "");
				//拼接一个完整的字符串
				String _file = filePath+"\\"+uuid+"."+format;
				System.out.println("_file="+_file);
				//在服务器端保存起来
				file.transferTo(new File(_file));
			
				String s = FileUtils.createThumbPic(new File(_file), filePath);
				System.out.println("sfile_name="+s);
				//这个路径是缩略图的完整的地址
				url =basePath+ "/pics/"+uuid+"_sub."+format;
				String original_url =basePath+ "/pics/"+uuid+"."+format;
				System.out.println("original_url="+original_url);
				System.out.println("url="+url);
		}
		
		
		
		NoticeEntity notice = new NoticeEntity();
		notice.setNoticecontent(content);
		notice.setNoticetitle(title);
		
		notice.setNoticeemp(emp.getEmpid());
    	notice.setNoticeempname(emp.getEmpname());
    	notice.setNoticeentid(emp.getEmpent());
    	notice.setNoticetime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
    	notice.setNoticeaddress(address);
    	noticeService.save(notice);
		System.out.println("insert successfully  ");
		
		
		
		
        return R.ok();
    }*/
    
    /**
     * 添加公告信息
     * @param emp
     * @param notice
     * @return
     * @author penghu 2017-4-11
     */
    
    @PostMapping("noticeAdd")
    public R noticeAdd(@LoginUser EmployeeEntity emp,NoticeEntity notice){
    	
    	System.out.println("noticetitle="+notice.getNoticetitle()+" noticecontent="+notice.getNoticecontent());
    	notice.setNoticeemp(emp.getEmpid());
    	notice.setNoticeempname(emp.getEmpname());
    	notice.setNoticeentid(emp.getEmpent());
    	notice.setNoticetime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
    	noticeService.save(notice);
    	//解决跨域的问题
    	/*参数：HttpServletResponse response
    	response.setHeader("Access-Control-Allow-Origin","*");*/
    	return R.ok();
    }
    
    /**
     * 根据noticeId获取notice详情
     * @param notice
     * @return
     * @author penghu 2017-4-11
     */
    
    @GetMapping("noticeDetailByNoticeId")
    public R noticeDetailByNoticeId(NoticeEntity notice,HttpServletResponse response){
    	System.out.println("-------------");
    	NoticeEntity n = noticeService.queryObject(notice.getNoticeid());
    	//解决跨域的问题
    	response.setHeader("Access-Control-Allow-Origin","*");
    	return R.ok().put("data", n);
    }

   
}
