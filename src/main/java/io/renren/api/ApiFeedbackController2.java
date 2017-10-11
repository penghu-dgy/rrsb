package io.renren.api;

import io.renren.utils.annotation.IgnoreAuth;
import io.renren.utils.annotation.LoginUser;
import io.renren.entity.EmployeeEntity;
import io.renren.entity.EnterpriseEntity;
import io.renren.entity.TokenEntity;
import io.renren.service.EmployeeService;
import io.renren.service.EnterpriseService;
import io.renren.service.FeedbackService;
import io.renren.service.TokenService;
import io.renren.service.UserService;
import io.renren.service.impl.EnterpriseServiceImpl;
import io.renren.utils.R;
import io.renren.utils.validator.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * API登录授权
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:31
 */
@RestController
@RequestMapping("/api")
public class ApiFeedbackController2 {


    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private FeedbackService fbs;
  

   /**
    * 
    * @param feed
    * @return
    * 添加一条反馈
    */
    
    
    @PostMapping("addFeedBack")
    public HashMap<String , Object> employeeList(@LoginUser EmployeeEntity emp , @RequestParam Map<String, Object> params){
    	
     	params.put("empid", emp.getEmpid());
     	
    	fbs.saveapi(params);
   
    	//System.out.println(params);
    	
        return R.ok();
    }
    
 

}
