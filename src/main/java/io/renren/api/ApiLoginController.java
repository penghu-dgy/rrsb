package io.renren.api;

import io.renren.utils.annotation.IgnoreAuth;
import io.renren.entity.EmployeeEntity;
import io.renren.service.EmployeeService;
import io.renren.service.TokenService;
import io.renren.service.UserService;
import io.renren.utils.R;
import io.renren.utils.validator.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
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
public class ApiLoginController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private EmployeeService employeeService;

    /**
     * 登录
     */
    @IgnoreAuth
    @PostMapping("login")
    
    public R login(String mobile, String password, HttpServletResponse response){
    	
    	response.setHeader("Access-Control-Allow-Origin","*");
    	
    	//System.out.println("mobile="+mobile+" password="+password);
        Assert.isBlank(mobile, "手机号不能为空");
        Assert.isBlank(password, "密码不能为空");

        //用户登录
        //long userId = userService.login(mobile, password);
        //penghu
        long userId = employeeService.login(mobile, password);

        //生成token
        Map<String, Object> map = tokenService.createToken(userId);
        
        map.put("user", employeeService.queryObject(userId));
        System.out.println("aaaaaaaaaaaaaaaaaa"+map);
        return R.ok(map);
    }
    

    /**
     * 实现通过验证码登陆
     * @param empPhone
     * @param empYzm
     * @return
     * @author penghu 2017-4-7
     */
    @IgnoreAuth
    @PostMapping("loginByYzm")
    public R loginByYzm(String empPhone , String empYzm, HttpServletResponse response){
    	
    	response.setHeader("Access-Control-Allow-Origin","*");
    	
    	
    	System.out.println("mobile="+empPhone+" password="+empYzm);
        Assert.isBlank(empPhone, "手机号不能为空");
        Assert.isBlank(empYzm, "验证码不能为空");
        Map<String,Object> map = new HashMap<String,Object>();
        
        map.put("empphone", empPhone);
        map.put("empyzm",empYzm);
        List<EmployeeEntity> list = employeeService.queryObjectByCondition(map);
        Assert.isNull(list, "不存在");
        
        long userId  =list.get(0).getEmpid();
        //生成token
        Map<String, Object> r_map = tokenService.createToken(userId);
        r_map.put("user", employeeService.queryObject(userId));
       
        return R.ok(r_map);
    }

    /**
     * 向employee数据库插入一条数据
     * @param empPhone
     * @param empYzm
     * @return
     * @author penghu 2017-4-7
     */
    @IgnoreAuth
    @PostMapping("resigsterEmp")
    public R resigsterEmp(String empPhone , String empYzm , HttpServletResponse response){
    	
    	response.setHeader("Access-Control-Allow-Origin","*");
    	
    	System.out.println("mobile="+empPhone+" password="+empYzm);
        Assert.isBlank(empPhone, "手机号不能为空");
        Assert.isBlank(empYzm, "验证码 不能为空");
        EmployeeEntity e = new EmployeeEntity();
        e.setEmpphone(empPhone);
        e.setEmpyzm(empYzm);
        employeeService.save(e);//向库表中插入一条数据
        return R.ok();
    }
    
    
    
    
    
    @IgnoreAuth
    @PostMapping("sendYzm")
    public R sendYzm(String empPhone,HttpServletResponse response){
    	
    	response.setHeader("Access-Control-Allow-Origin","*");
    	
    	System.out.println("mobile="+empPhone);
        Assert.isBlank(empPhone, "手机号不能为空");
        //判断该手机号是否存在对应的用户
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("empphone", empPhone);
        List<EmployeeEntity> list=  employeeService.queryObjectByCondition(map);
        if(list.size()==0){
        	//向employee表中插入一条记录
        	EmployeeEntity employee = new EmployeeEntity();
        	employee.setEmpstate("0");
        	employee.setEmpphone(empPhone);
        	employee.setEmpyzm("123");
        	employee.setEmptype("0000"); 
        	//验证码为当前时间+10分钟
        	long currentTime = new Date().getTime();
        	currentTime  +=60*1000*10;
        	Date d = new Date(currentTime);
        	SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	System.out.println("aaa="+sdf.format(d));
        	employee.setEmpyzmexpiretime(sdf.format(d));
        	employeeService.save(employee);
        }else{
        	//修改对应的手机号码的验证码
        	
        	EmployeeEntity emp = list.get(0);
        	emp.setEmpyzm("123");
        	employeeService.update(emp);
        	
        }
        
        return R.ok();
    }
    
    
    
    @IgnoreAuth
    @PostMapping("LoginSendYzm")
    public R LoginSendYzm(String empPhone){
    	
    	System.out.println("mobile="+empPhone);
        Assert.isBlank(empPhone, "手机号不能为空");
        //判断该手机号是否存在对应的用户
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("empphone", empPhone);
        List<EmployeeEntity> list=  employeeService.queryObjectByCondition(map);
        if(list.size()==0){
        	//向employee表中插入一条记录
        	/*EmployeeEntity employee = new EmployeeEntity();
        	employee.setEmpstate("0");
        	employee.setEmpphone(empPhone);
        	employee.setEmpyzm("123");
        	employee.setEmptype("0000"); 
        	//验证码为当前时间+10分钟
        	long currentTime = new Date().getTime();
        	currentTime  +=60*1000*10;
        	Date d = new Date(currentTime);
        	SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	System.out.println("aaa="+sdf.format(d));
        	employee.setEmpyzmexpiretime(sdf.format(d));
        	employeeService.save(employee);*/
        	return R.error("该账号不存在，请注册");
        }else{
        	//修改对应的手机号码的验证码
        	
        	EmployeeEntity emp = list.get(0);
        	emp.setEmpyzm("123");
        	employeeService.update(emp);
        	
        }
        
        return R.ok();
    }
  

}
