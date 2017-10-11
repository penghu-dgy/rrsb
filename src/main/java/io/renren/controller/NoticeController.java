package io.renren.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import io.renren.entity.EmployeeEntity;
import io.renren.entity.EnterpriseEntity;
import io.renren.entity.NoticeEntity;
import io.renren.service.EmployeeService;
import io.renren.service.EnterpriseService;
import io.renren.service.NoticeService;
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
@RequestMapping("notice")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;

	@Autowired
	private EnterpriseService entService;
	
	@Resource
	private EmployeeService empser;
	/**
	 * 列表
	 * 1.通过ShiroUtils.getUser()获取登录者id
	 * 2.通过登录者id获取其公司信息
	 * 3.查找公司id对应的员工id
	 * 4.通过员工id查找公告信息
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("notice:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		
		int total = noticeService.queryTotal(query);
		 Long u_id=ShiroUtils.getUserId();
	       EnterpriseEntity ent=new EnterpriseEntity();
	       ent.setEntemp(u_id);
	        Long c=entService.getEntId(ent);
	        query.put("noticeentid", c);
	        List<NoticeEntity> noticeList = noticeService.queryList(query);
		PageUtils pageUtil = new PageUtils(noticeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	
	@ResponseBody
	@RequestMapping("/list1")
	@RequiresPermissions("notice:list")
	public R list1(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
	    List<NoticeEntity> noticeList = noticeService.queryList(query);

		int total = noticeService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(noticeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{noticeid}")
	@RequiresPermissions("notice:info")
	public R info(@PathVariable("noticeid") Long noticeid){
		NoticeEntity notice = noticeService.queryObject(noticeid);
		
		return R.ok().put("notice", notice);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("notice:save")
	public R save(@RequestBody NoticeEntity notice){
		long u_id =ShiroUtils.getUserId();
		EmployeeEntity e=empser.queryObject(u_id);
		
		
		notice.setNoticeempname(e.getEmpname());
		notice.setNoticeemp(e.getEmpid());
		notice.setNoticeentid(e.getEmpent());
		String a=new Date().toLocaleString();
		notice.setNoticetime(a);
		noticeService.save(notice);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("notice:update")
	public R update(@RequestBody NoticeEntity notice){
		noticeService.update(notice);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("notice:delete")
	public R delete(@RequestBody Long[] noticeids){
		noticeService.deleteBatch(noticeids);
		
		return R.ok();
	}
	
}
