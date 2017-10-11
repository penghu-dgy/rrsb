package io.renren.api;

import io.renren.utils.annotation.IgnoreAuth;
import io.renren.utils.annotation.LoginUser;
import io.renren.entity.EmployeeEntity;
import io.renren.entity.OrderEntity;
import io.renren.service.OrderService;
import io.renren.utils.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API测试接口
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:47
 */

@RestController
@RequestMapping("/api")
public class ApiOrderController {
	
//	@Autowired
//    private OrderService orderService;
//
//    /**
//     * 根据状态（1发布中 2、执行中 3已完成）获取工单发布中客户所在公司的工单列表列表
//     */
//    @PostMapping("orderList")
//    public R orderList(@LoginUser EmployeeEntity user,Long oderstate,HttpServletResponse response){
//    	Map<String,Object> map = new HashMap<String,Object>();
//    	//获取员工所在企业Id
//    	response.setHeader("Access-Control-Allow-Origin","*");
//    	System.out.println("123"+user.getEmpent());
//    	map.put("entId", user.getEmpent());
//    	map.put("orderstate", oderstate);
//    	List<OrderEntity> list = orderService.queryList(map);
//        return R.ok().put("data", list);
//    }
//    
//    /**
//     * 根据状态（1发布中 2、执行中 3已完成）获取工单发布中的工单列表列表
//     */
//    @PostMapping("updateOrderState")
//    public R updateOrderState(@LoginUser EmployeeEntity user,String orderid,Long orderstate){
//    	OrderEntity order = new OrderEntity();
//    	order.setOrderid(orderid);
//    	order.setOrderstate(orderstate);
//    	orderService.update(order);
//    
//        return R.ok();
//    }
//    
//    @PostMapping("getOrderByOrderId")
//    public R userInfo(@LoginUser EmployeeEntity user,String orderid){
//    	
//    	OrderEntity order = orderService.queryObject(orderid);
//    
//        return R.ok().put("data", order);
//    }
//    
//    @PostMapping("orderAdd")
//    public R orderAdd(@LoginUser EmployeeEntity user,OrderEntity order){
//    	orderService.save(order);;
//    	return R.ok();
//    }

   
}
