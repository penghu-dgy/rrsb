package io.renren.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.renren.utils.annotation.LoginUser;
import io.renren.entity.EmployeeEntity;
import io.renren.entity.OperationEntity;
import io.renren.service.OperationService;
import io.renren.utils.R;
@RestController
@RequestMapping("/api")
public class ApiOperationController {
   
	@Autowired
    public OperationService operationService;
	 /**
     * 根据工单id查找相应的操作信息
     */
	@PostMapping("operateDetail")
	public R getOperationByoperId(@LoginUser EmployeeEntity employeeEntity,String orderId,String operDesc){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("operOrder", orderId);
		map.put("operDesc", operDesc);
		List<Map<String, Object>> list=operationService.queryOperate(map);
		return R.ok().put("data", list);
	}
	
	
	@PostMapping("operateDesc")
	public R insertOperateDesc(@LoginUser EmployeeEntity employeeEntity,String operOrder,String operDesc){
		OperationEntity operationEntity=new OperationEntity();
		//工单主键
		operationEntity.setOperid(new Date().getTime());
		operationEntity.setOperdesc(operDesc);
		operationEntity.setOperemp(employeeEntity.getEmpid());
		operationEntity.setOperorder(operOrder);
		operationEntity.setBz1(employeeEntity.getEmpname());
		SimpleDateFormat format=new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss");
		operationEntity.setOpertime(format.format(new Date()));
		operationService.save(operationEntity);
		return R.ok();
	}
	
	//获取配送人员和施工人员信息
	@PostMapping("distructionEmp")
	public R distributionEmp(String orderId,String operDesc){
		Map<String, Object> map=new HashMap<>();
		map.put("orderId", orderId);
		map.put("operDesc", operDesc);
		List<Map<String, Object>> list=operationService.queryOperate(map);
		//施工和配送人员为当前的,即id最大的
		System.out.println(list.get(0));
		if(list.size()>0)
		return R.ok().put("data", list.get(0));
		else
		return R.ok();
	}

	
	
}
