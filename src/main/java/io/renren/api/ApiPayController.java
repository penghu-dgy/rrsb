package io.renren.api;


import io.renren.utils.annotation.LoginUser;
import io.renren.entity.EmployeeEntity;

import io.renren.entity.PayEntity;
import io.renren.service.EmployeeService;
import io.renren.service.PayService;
import io.renren.utils.DateUtils;
import io.renren.utils.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册
 * @author wulei
 * @email 
 * @date 2017-04-13
 */
@RestController
@RequestMapping("/api")
public class ApiPayController {
	 @Autowired
	    private PayService payService; 
	 @Autowired
	    private EmployeeService empService; 
    
    /**
     * 充值缴费
     * @throws ParseException 
     */
    @GetMapping("addPay")
    public R addPay(@LoginUser EmployeeEntity emp,Long empid,int n,Long paymoney,String zflx,HttpServletResponse response) throws ParseException{
    	PayEntity pay=new PayEntity();
    	Long zfbh=123l;
    	
    	String oldtime = emp.getEmpexpiretime();
    	DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
    	
    	
    	 Date date = new Date();//取时间 
    	 
    	    
    	 String payexpiretime;
    	 
    	    
    	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	    Date oldT =sdf.parse(oldtime);
    	    
    	    String strDateTime=sdf.format(date);
    	    
    	    /*
    	     * 比较时间，如果现在时间大于之前过期时间则已过期，充值使用当前时间重新计算
    	     */
    	    
    	    if(date.getTime()>oldT.getTime()){  
    	    		Calendar calendar  =   Calendar.getInstance();
    	    	    calendar.setTime(date); //需要将date数据转移到Calender对象中操作
    	    	    calendar.add(calendar.MONTH, n);//把月份增加n.正数往后推,负数往前移动 
    	    	    date=calendar.getTime(); 
    	    	    payexpiretime=sdf.format(date);
    	    	
    	    }
    	    else{
    	    	
    	    	Calendar calendar  =   Calendar.getInstance();
	    	    calendar.setTime(oldT); //需要将date数据转移到Calender对象中操作
	    	    calendar.add(calendar.MONTH, n);//把月份增加n.正数往后推,负数往前移动 
	    	    date=calendar.getTime(); 
    	    	payexpiretime=sdf.format(date);
    	    	
    	    }
    	    
    	    
    	
       response.setHeader("Access-Control-Allow-Origin","*");
       
       //插入充值记录
       pay.setPayemp(empid);
       pay.setEntid(emp.getEmpent());
       pay.setPaytime(strDateTime);
       pay.setPaymoney(paymoney);
       pay.setZflx(zflx);
       pay.setPayexpiretime(payexpiretime);
       pay.setZfbh(zfbh);
       payService.save(pay);
       
       //获取充值员工的记录，更新过期时间
       
       EmployeeEntity E=  empService.queryObject(empid);
       E.setEmpexpiretime(payexpiretime);
       empService.update(E);
       
       
       
       return R.ok().put("payId", pay.getPayid());
    }
    
    /**
     * 缴费列表
     */
    @GetMapping("payList")
    public R payList(@LoginUser EmployeeEntity emp,String empid,HttpServletResponse response){
    	response.setHeader("Access-Control-Allow-Origin","*");
    	Map<String,Object> map = new HashMap<String,Object>();
    	System.out.println("entid="+emp.getEmpent());
    	map.put("entid", emp.getEmpent());//企业id
    	map.put("empid",empid);
    	//传一个企业ID获取该企业对应的所有的员工的缴费记录
    	List<Map<String,Object>> list =payService.queryByCondition(map);
        return R.ok().put("data", list);
        
    }
    
    /**
     * 缴费详情
     */
    @GetMapping("payDetail")
    public R payDetail(PayEntity pay,HttpServletResponse response){
    	PayEntity n=payService.queryObject(pay.getPayid());
    	response.setHeader("Access-Control-Allow-Origin","*");
    	return R.ok().put("data", n);
    }
}
