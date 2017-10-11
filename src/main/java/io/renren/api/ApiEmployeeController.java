package io.renren.api;

import io.renren.utils.annotation.IgnoreAuth;
import io.renren.utils.annotation.LoginUser;
import io.renren.entity.EmployeeEntity;
import io.renren.entity.EnterpriseEntity;
import io.renren.entity.TokenEntity;
import io.renren.service.EmployeeService;
import io.renren.service.EnterpriseService;
import io.renren.service.TokenService;
import io.renren.service.UserService;
import io.renren.service.impl.EnterpriseServiceImpl;
import io.renren.utils.DateUtils;
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
public class ApiEmployeeController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private EnterpriseService enterpriseService;
    
    

    /**
     * 通过员工empent获取员工列表
     *  何奔
     *  4/13 
   **/
    
    
    @PostMapping("employeeList")
    public HashMap<String , Object> employeeList(@LoginUser EmployeeEntity emp){
    	
    	
    	Map<String , Object> map= new HashMap<String, Object>();
    	map.put("empent", emp.getEmpent());
    	/*System.out.println("test success"+emp.getEmpent());*/
    	List <EmployeeEntity> lis=employeeService.queryObjectByCondition(map);
    	List<String[]> emptypes =new ArrayList<String[]>();
    		/*System.out.println("i=========="+lis);*/
    	
    	for(EmployeeEntity a:lis){
    		String[] arr;
    		arr=a.getEmptype().split("");
    		emptypes.add(arr);
    	}
    	
    	map.put("emptypes", emptypes);
    	map.put("data", lis);
    	map.put("user", emp);
        return R.ok(map);
    }
    
    /*
     * 何奔
     * 2017/5/31
     * 更新员工信息
     */
    
    @RequestMapping("updateEmp")
    public R updateEmp(@LoginUser EmployeeEntity emp, EmployeeEntity e){
    	e.setEmpid(emp.getEmpid());
    	employeeService.update(e);
		return R.ok();
    	
    }
    
    
    /**
     * 加入企业详情界面传入数据
     * 更新员工状态密码等字段    
     * 完善员工信息
     * 何奔
     * 4/14
     */
 @PostMapping("finishEmployeeInfo")
    
    public R finishEmployeeInfo(@LoginUser EmployeeEntity emp ,String empName,String empPassword,String empEnt,String empDetail ){

    	long empent=Integer.parseInt(empEnt);
    	emp.setEmpname(empName);
    	emp.setEmppassword(empPassword);
    	emp.setEmpdetail(empDetail);
    	emp.setEmpent(empent);
    	String empState="0";
    	emp.setEmpstate(empState);
    	emp.setEmptype("0000");
    	employeeService.update(emp);
    	
    	
    	return R.ok();
    }
    
 
 /*
  * 刷新，重新获取员工状态值并返回
  * 何奔
  * 4/14
  */
 @PostMapping("refreshEmpState")
 
 public R refreshEmpState(@LoginUser EmployeeEntity emp ){
	 
	
 	
 	return R.ok(emp.getEmpstate());
 }
 
 
 /*
  * 更新员工密码
  * 1.通过手机号验证码登录
  * 2.若库表中该账号存在则获取改账号对应员工ID
  * 3.通过员工ID更新员工密码
  * 4.通过员工ID获取一条token
  * 5.返回token
  * 何奔
  * 4/15
  */
 
 @IgnoreAuth
 @PostMapping("UpdateEmpPwd")
 public R UpdateEmpPwd(String empPhone , String Yzm,String Password){
 	//System.out.println("mobile="+empPhone+" Yzm="+Yzm + "  pwd"+Password);
     Assert.isBlank(empPhone, "手机号不能为空");
     Assert.isBlank(Yzm, "验证码不能为空");
     Map<String,Object> map = new HashMap<String,Object>();
     
     map.put("empphone", empPhone);
     map.put("empyzm",Yzm);
     List<EmployeeEntity> list = employeeService.queryObjectByCondition(map);
     Assert.isNull(list, "不存在");
     
     long userId  =list.get(0).getEmpid();
  
//    System.out.println("list0");
     EmployeeEntity employee = list.get(0);
     
     employee.setEmppassword(Password);
//     System.out.println("list1");
     employeeService.update(employee);
     //生成token
     Map<String, Object> r_map = tokenService.createToken(userId);
     //System.out.println("list2");
     r_map.put("empState", employeeService.queryObject(userId).getEmpstate());
     return R.ok(r_map);
 }
 
 /*
  * 通过token获取用户信息并传回
  * 何奔
  * 4/17
  */
 
 @PostMapping("getEmpInfo")
 public HashMap<String , Object> getEmpInfo(@LoginUser EmployeeEntity emp){
 	Map<String , Object> map= new HashMap<String, Object>();
 	//System.out.println(emp.getEmpname());
 	map.put("user", emp);
     return R.ok(map);
 }
 
 /*
  * 获取选择的员工的信息
  * 何奔
  * 2017/5.10
  */
 
 @PostMapping("getChooseEmpInfo")
 public R getChooseEmpInfo(@LoginUser EmployeeEntity emp,long chooseempid){
 	Map<String , Object> map= new HashMap<String, Object>();
 	
 	map.put("data", employeeService.queryObject(chooseempid));
     return R.ok(map);
 }
 
 
 

 
 @PostMapping("updateEmpState")
 
 public R updateEmpState(@LoginUser EmployeeEntity emp ,String code ,String empid ){
 	
 	/**
 	 * 审核或撤销加入企业操作
 	 * 
 	 * 1.如果传入值为success，表示该员工被通过，改变员工状态、加入时间
 	 * 2.穿入值为fail，则是将员工企业ID置为1--默认无企业 且将员工状态置为0
 	 * 3.如果传入code和empid都为0则将emp的企业id置0 员工状态置1
 	 *  何奔
 	 */	
 	
 	EmployeeEntity E = new EmployeeEntity();
 	long a=Integer.parseInt(empid);
 	E.setEmpid(a);
 	
 	if("success".equals(code)){
 		
 			String currentTime = DateUtils.format(new Date(), DateUtils.DATE_PATTERN);
	    	
	    	E.setEmphiretime(currentTime);
	    	
	    	E.setEmpexpiretime(DateUtils.getTimeDelayDay(currentTime, 30));
	    	E.setEmpstate("1");
	    	employeeService.update(E);
 		
 	}else if("fail".equals(code)){
 		E.setEmptype("0000");
 		E.setEmpent((long) 1);
 		E.setEmpstate("0");
 		employeeService.update(E);
 	}
 	
 	
 	
 	else if("0".equals(empid) && "0".equals(code)){
 		
 		emp.setEmpent((long) 1);
 		emp.setEmpstate("0");
 		employeeService.update(emp);
 	}
 	
 	else{
 		return R.error("未知错误");
 	}
 	
 	return R.ok();
 }
 
 /*
  * 修改员工身份
  * 何奔
  * 2017.5.10
  */
 
 @PostMapping("updateEmployeeType")
 public R updateEmployeeType(@LoginUser EmployeeEntity emp,long chooseempid,String empType){
	 
	 EmployeeEntity E =new EmployeeEntity();
	 
	 E.setEmpid(chooseempid);
	 E.setEmptype(empType);
	 employeeService.update(E);
	return R.ok();
	 
 }
  

}
