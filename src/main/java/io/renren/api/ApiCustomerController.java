package io.renren.api;

import io.renren.utils.annotation.LoginUser;
import io.renren.entity.CustomerEntity;
import io.renren.entity.EmployeeEntity;
import io.renren.entity.WorkEntity;
import io.renren.service.CustomerService;
import io.renren.service.EmployeeService;
import io.renren.service.WorkService;
import io.renren.utils.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 客户的增删改查：
 * @author fengxiongqiang:
 * @date 2017-04-11：
 */
@RestController
@RequestMapping("/api")
public class ApiCustomerController {

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private WorkService workService;
    
    @Autowired
    private EmployeeService employeeService;

    /**
     * 添加
     */
   
    @GetMapping("customerAdd")
    public R customerAdd(@LoginUser EmployeeEntity emp,CustomerEntity customerEntity){
    	
    //	System.out.println("=================添加客户=========="+customerEntity.getCustname());
    	//获得当前的跟进人（员工）Id
    	//System.out.println("asdasdasdasd"+emp.getEmpname());
    	Map<String ,Object> map=new HashMap<String , Object>();
    	
    	map.put("custphone", customerEntity.getCustphone());
    	map.put("entId", emp.getEmpent());
    	
    	//System.out.println(map);
    	CustomerEntity cu=customerService.selectByPhone(map);
    	if(cu != null){
    		return R.error(101,"该客户已被添加");
    	}
    	
    	
    	long custemp =  emp.getEmpid();
    	customerEntity.setCustemp(custemp);
    	String citys = customerEntity.getCustprovince();
    	String [] city = citys.split(" ");
    	//System.out.println(city[0]+"========="+city[1]+"======"+city[2]);
    	customerEntity.setCustprovince(city[0]);
    	customerEntity.setCustcity(city[1]);
    	customerEntity.setCustarea(city[2]);
    	//设置当前时间
    	Date d = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String custtime = sdf.format(d);
    	customerEntity.setCusttime(custtime);	
    	customerService.save(customerEntity);
        return R.ok();
    }
    
    /**
     * 删除
     */
   
    @GetMapping("customerDelete")
    public R customerDelete(CustomerEntity customerEntity){
    	//System.out.println("=================删除客户====="+customerEntity.getCustid());
    	customerService.delete(customerEntity.getCustid());
    	//删除当前客户的工作记录
    	workService.deleteCustBatch(customerEntity.getCustid());
        return R.ok();
    }
    
    /**
     * 修改
     */
    @GetMapping("customerUpdate")
    public R customerUpdate(CustomerEntity customerEntity){  	
    	
    //	System.out.println("=================修改客户信息====="+customerEntity.getCustid());	
    	String citys = customerEntity.getCustprovince();
    	String [] city = citys.split(" ");
    	customerEntity.setCustprovince(city[0]);
    	customerEntity.setCustcity(city[1]);
    	customerEntity.setCustarea(city[2]);
    	customerService.update(customerEntity);
        return R.ok();
    }
    
    /**
     * 单个客户的详情资料
     */
    @GetMapping("customerDetailByCustId")
    public R customerDetailByCustId(CustomerEntity customerEntity){
    	
    	
    	//该客户的基本信息(本表的一些字段)
    	CustomerEntity customer = customerService.queryObject(customerEntity.getCustid());
    	
    	//该客户所对应的员工信息
    	EmployeeEntity employee =  employeeService.queryObject(customer.getCustemp());
    	
    	//该客户所对应的工作记录
    	Map<String,Object> map = new HashMap<String,Object>();   	
    	map.put("workcust", customerEntity.getCustid());
    	List<WorkEntity> list = workService.queryList(map);
    	
    	Map<String,Object> res =new  HashMap<String,Object>();
    	res.put("customer", customer);
    	res.put("list", list);
    	res.put("employee", employee);
    	return R.ok().put("data",res);
    }
    
    
    /**
     * 客户列表查询
     * 关键字查询获取客户信息列表（通过手机号或者是地址或者客户姓名）
     */
    @GetMapping("searchCustomerList")
    public R searchCustomerList(@LoginUser EmployeeEntity emp,String search,HttpServletResponse response){
    	
    	response.setHeader("Access-Control-Allow-Origin","*");
    	//System.out.println("=================列表查询======================="+emp.getEmpid());
    	//获得当前的跟进人（员工）Id的客户
    	long custemp =  emp.getEmpid();
    	R map = new R();
    	map.put("custemp", custemp);
    	//模糊查询的条件
    	map.put("search",search);
    	List<CustomerEntity> list = customerService.findAll(map);
    	for(CustomerEntity c:list){
    		System.out.println(c.getCustname());
    	}
    	return R.ok().put("data", list);
    }
    
    
    

	@PostMapping("customerList")
	public R customerList(@LoginUser EmployeeEntity employeeEntity){
		HashMap<String, Object> map=new HashMap<>();
		map.put("custemp", employeeEntity.getEmpid());
		List<CustomerEntity> list=customerService.queryList(map);
		return R.ok().put("data", list);
	}
	
	@PostMapping("getCustomerById")
	public R customerList(@LoginUser EmployeeEntity employeeEntity,String custId){
      
		CustomerEntity customerEntity=customerService.queryObject(Long.parseLong(custId));
		return R.ok().put("data", customerEntity);
	}
    
    

}
