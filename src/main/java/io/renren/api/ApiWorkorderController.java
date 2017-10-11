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
import io.renren.entity.WorkorderEntity;
import io.renren.service.WorkorderService;
import io.renren.utils.R;
@RestController
@RequestMapping("/api")
public class ApiWorkorderController {
	@Autowired
    private WorkorderService workorderService;
    /**
     * 根据状态（1发布中 2、执行中 3已完成）获取工单发布中客户所在公司的工单列表列表以及工单的状态
     *flag=1发布中   flag=2执行中    flag=3已完成    flag=4我的发布   flag=5我的配送   flag=6我的施工
     */
    @PostMapping("workOrderList")
    public R workOrderList(@LoginUser EmployeeEntity user,String flag,String search){
    	Map<String,Object> map = new HashMap<String,Object>();
    	//获取员工所在企业Id
    	long[] ids = null;
    	//发布中
    	if(flag==null||flag.equals("")){
    		flag=1+"";
    	}
    	if(Integer.parseInt(flag)==1){
    		ids=new long[4];	
    		ids[0]=0;
    		ids[1]=1;
    		ids[2]=2;
    		ids[3]=3;
    	}
    	//执行中
    	if(Integer.parseInt(flag)==2){
    		ids=new long[2];
    		ids[0]=4;
    		ids[1]=5;
    	}
    	//已完成
    	if(Integer.parseInt(flag)==3){
    		ids=new long[1];
    		ids[0]=6;
    	}
    	//我的发布
    	if(Integer.parseInt(flag)==4){
    		ids=new long[7];
    		ids[0]=0;
    		ids[1]=1;
    		ids[2]=2;
    		ids[3]=3;
    		ids[4]=4;
    		ids[5]=5;
    		ids[6]=6;
    		map.put("orderemp",user.getEmpid());
    	}
    	//我的配送
    	if(Integer.parseInt(flag)==5){
    		ids=new long[2];
    		ids[0]=1;
    		ids[1]=4;
    		map.put("distributionid",user.getEmpid());
    	}
    	//我的施工
    	if(Integer.parseInt(flag)==6){
    		ids=new long[3];
    		ids[0]=2;
    		ids[1]=4;
    		ids[2]=5;
    		map.put("constructionid",user.getEmpid());
    	}
     	map.put("ids", ids);
    	map.put("entid", user.getEmpent());
    	map.put("search", search);
    	List<Map<String, Object>> list=workorderService.queryWorkorder(map);
        return R.ok().put("data", list);
    }
    
    /**
     * 根据状态（1发布中 2、执行中 3已完成）获取工单发布中的工单列表列表
     */
    /**
     * 根据状态（1发布中 2、执行中 3已完成）获取工单发布中客户所在公司的工单列表列表以及工单的状态
     *
     */
   // 发布中(0未配送未施工 1未施工配送中 2未配送施工中    3未施工配送完成)  执行中（ 4配送中施工中  5配送完成施工中 ）6执行完成
    //0代表对配送的操作 1代表对施工的操作
    @PostMapping("updateOrderState")
    public R updateOrderState(@LoginUser EmployeeEntity user,Long flag,String orderid,Long orderstate){
    	SimpleDateFormat format=new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss");
    	WorkorderEntity workorderEntity = new WorkorderEntity();
    	workorderEntity.setOrderid(orderid);
    	workorderEntity.setOrderstate(orderstate);
    	if(orderstate==0){
    		// 撤销施工的情形
    		if(flag==1){
    			workorderEntity.setConstructionid((long) 0);
    		}
    		// 撤销的配送情形
    		if(flag==0){
    			workorderEntity.setDistributionid((long) 0);
    		}
    	}
    	if(orderstate==1){
    		// 撤销施工的情形
    		if(flag==1){
    			workorderEntity.setConstructionid((long) 0);
    		}
    		//配送中未施工情形
    		if(flag==0){
    			workorderEntity.setDistributionid(user.getEmpid());
    			workorderEntity.setDistributionstarttime(format.format(new Date()));
    			workorderEntity.setDistributionname(user.getEmpname());
    			workorderEntity.setDistrubutiontel(user.getEmpphone());
    		}
    	}
		if(orderstate==2){
			// 撤销的配送情形
    		if(flag==0){
    			workorderEntity.setDistributionid((long) 0);
    		}
    		//施工中情形
    		if(flag==1){
    			workorderEntity.setConstructionid(user.getEmpid());
    			workorderEntity.setConstructionstarttime(format.format(new Date()));
    		    workorderEntity.setConstructionname(user.getEmpname());
    		    workorderEntity.setConstrutiontel(user.getEmpphone());
    		}
		}
		if(orderstate==3){
			//配送完成情形
			if(flag==0){
				workorderEntity.setDistributionendtime(format.format(new Date()));
			}
			// 撤销施工的情形
			if(flag==1){
				workorderEntity.setConstructionid((long) 0);
			}
		}
		if(orderstate==4){
			//申请配送情形
			if(flag==0){
				workorderEntity.setDistributionid(user.getEmpid());
				workorderEntity.setDistributionstarttime(format.format(new Date()));
				workorderEntity.setDistributionname(user.getEmpname());
				workorderEntity.setDistrubutiontel(user.getEmpphone());
			}
			if(flag==1){
				//施工中情形
				workorderEntity.setConstructionid(user.getEmpid());
				workorderEntity.setConstructionstarttime(format.format(new Date()));
				workorderEntity.setConstructionname(user.getEmpname());
				workorderEntity.setConstrutiontel(user.getEmpphone());
			}
		}
		if(orderstate==5){
			//配送完成情形
			if(flag==0){
				workorderEntity.setDistributionendtime(format.format(new Date()));
			}
			if(flag==1){
				//
				workorderEntity.setConstructionid(user.getEmpid());
				workorderEntity.setConstructionstarttime(format.format(new Date()));
				workorderEntity.setConstructionname(user.getEmpname());
				workorderEntity.setConstrutiontel(user.getEmpphone());
			}
		}
		if(orderstate==6){
			if(flag==0){
			//impossible	
			}
			//配送完成情形
			if(flag==1){
				workorderEntity.setConstructionendtime(format.format(new Date()));
			}
     }
    	workorderService.update(workorderEntity);
        return R.ok();
    }
    
    @PostMapping("getOrderByOrderId")
    public R orderInfo(@LoginUser EmployeeEntity user,String orderid){
    	WorkorderEntity order = workorderService.queryObject(orderid);
        return R.ok().put("data", order);
    }

    
    @PostMapping("deleteOrderByOrderId")
    public R deleteOrderByOrderId(@LoginUser EmployeeEntity user,String orderid){
    	workorderService.delete(orderid);  
    	return R.ok();
    }
    
    @PostMapping("addOrder")
    //bz2 配送人员id bz3施工人员·id
    public R addOrder(@LoginUser EmployeeEntity user,WorkorderEntity workorder){
    	//员工id
    	workorder.setEntid(user.getEmpent());
    	//企业id
    	workorder.setOrderemp(user.getEmpid());
    	System.out.println(user.getEmpent());
    	workorder.setOrderstate((long) 0);
    	workorder.setOrderid(new Date().getTime()+"");
    	workorder.setOrderempname(user.getEmpname());
    	workorder.setOrderemptel(user.getEmpphone());
    	//默认为0
    	workorder.setConstructionid((long) 0);
    	workorder.setDistributionid((long) 0);
    	SimpleDateFormat format=new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss");
    	//排序准则bz1
    	workorder.setBz1(format.format(new Date()));
    	workorderService.save(workorder);
    	return R.ok();
    }
    
    //修改订单(此时id的状态必须为0)
    @PostMapping("alterOrder")
    public R alterOrder(@LoginUser EmployeeEntity user,WorkorderEntity workorder){
    	workorder.setEntid(user.getEmpent());
    	//企业id
    	workorder.setOrderemp(user.getEmpid());
    	System.out.println(user.getEmpent());
    	workorder.setOrderstate((long) 0);
    	workorderService.update(workorder);
    	return R.ok();
    	
    }
    
    //获取到订单的数量
    @PostMapping("getCount")
    public R getCount(@LoginUser EmployeeEntity user){
    	Map<String, Object> result=new HashMap<>();
    	Map<String,Object> map = new HashMap<String,Object>();
        	long[] ids = null;
    		ids=new long[4];
    		//发布中
    		ids[0]=0;
    		ids[1]=1;
    		ids[2]=2;
    		ids[3]=3;
    	Map<String,Object> map1 = new HashMap<String,Object>();
    	 map1.put("ids", ids);
         map1.put("entid", user.getEmpent());
    	 result.put("fbz", workorderService.queryWorkorder(map1).size());    
    	   //执行中
    		ids=new long[2];
    		ids[0]=4;
    		ids[1]=5;
    		Map<String,Object> map2 = new HashMap<String,Object>();
    		map2.put("ids", ids);
            map2.put("entid", user.getEmpent());
       	 result.put("zxz", workorderService.queryWorkorder(map2).size());
    	//已完成
    	
    		ids=new long[1];
    		ids[0]=6;
    		Map<String,Object> map3 = new HashMap<String,Object>();
    		map3.put("ids", ids);
            map3.put("entid", user.getEmpent());
       	    result.put("ywc", workorderService.queryWorkorder(map3).size());
    	//我的发布
    		ids=new long[7];
    		ids[0]=0;
    		ids[1]=1;
    		ids[2]=2;
    		ids[3]=3;
    		ids[4]=4;
    		ids[5]=5;
    		ids[6]=6;
    		Map<String,Object> map4 = new HashMap<String,Object>();
    		map4.put("orderemp",user.getEmpid());
    		map4.put("ids", ids);
            map4.put("entid", user.getEmpent());
       	 result.put("wdfb", workorderService.queryWorkorder(map4).size());
    	//我的配送
    
    		ids=new long[2];
    		ids[0]=1;
    		ids[1]=4;
    		Map<String,Object> map5 = new HashMap<String,Object>();
    		map5.put("distributionid",user.getEmpid());
    		map5.put("ids", ids);
            map5.put("entid", user.getEmpent());
       	 result.put("wdps", workorderService.queryWorkorder(map5).size());
    	//我的施工
       	
    		ids=new long[3];
    		ids[0]=2;
    		ids[1]=4;
    		ids[2]=5;
    		Map<String,Object> map6 = new HashMap<String,Object>();
    		map6.put("constructionid",user.getEmpid());
    		map6.put("ids", ids);
    		map6.put("entid", user.getEmpent());
       	 result.put("wdsg", workorderService.queryWorkorder(map6).size());
    	return R.ok().put("data", result);
    }
    
  
    
}