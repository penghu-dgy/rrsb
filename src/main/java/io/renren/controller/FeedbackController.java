package io.renren.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.entity.FeedbackEntity;
import io.renren.service.FeedbackService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-24 09:11:33
 */
@RestController
@RequestMapping("feedback")
public class FeedbackController {
	@Autowired
	private FeedbackService feedbackService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("feedback:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<FeedbackEntity> feedbackList = feedbackService.queryList(query);
		int total = feedbackService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(feedbackList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{feedbackid}")
	@RequiresPermissions("feedback:info")
	public R info(@PathVariable("feedbackid") Long feedbackid){
		FeedbackEntity feedback = feedbackService.queryObject(feedbackid);
		
		return R.ok().put("feedback", feedback);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("feedback:save")
	public R save(@RequestBody FeedbackEntity feedback){
		feedbackService.save(feedback);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("feedback:update")
	public R update(@RequestBody FeedbackEntity feedback){
		feedbackService.update(feedback);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("feedback:delete")
	public R delete(@RequestBody Long[] feedbackids){
		feedbackService.deleteBatch(feedbackids);
		
		return R.ok();
	}
	
}
