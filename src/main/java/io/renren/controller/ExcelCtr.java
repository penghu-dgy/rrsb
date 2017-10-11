package io.renren.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



import io.renren.entity.ActivityEntity;
import io.renren.entity.EmployeeEntity;
import io.renren.entity.EnterpriseEntity;
import io.renren.service.ActivityService;
import io.renren.service.CustomerService;
import io.renren.service.EmployeeService;
import io.renren.service.EnterpriseService;
import io.renren.service.NoticeService;
import io.renren.service.PayService;
import io.renren.utils.ShiroUtils;

@Controller
@RequestMapping("ExcelCtr")
public class ExcelCtr {
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private PayService payService;
	
	@Autowired
	private CustomerService custService;
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private EnterpriseService entService;
	
	
	
	
	//导出审批EXcel
	@RequestMapping("activityExcel")
	public void activity(HttpServletRequest request, HttpServletResponse response) throws IOException{
		/*Map<String , Object> query=new HashMap<String , Object>();*/
		EnterpriseEntity ent=new EnterpriseEntity();
			Long u_id=ShiroUtils.getUserId();
	        ent.setEntemp(u_id);
	        Long c=entService.getEntId(ent);
	        Map<String, Object> m=new HashMap<String , Object>();
	        m.put("entId", c);
	        /* query.put("entId", c);*/

		
		String[] excelHeader = { "申请人","审批人", "电话","开始时间","结束时间","时长（天数）","原因","申请时间"};
		/*String string;*/
		List<Map<String, Object>> list = activityService.backSee(m);

		System.out.println("size="+list.size());
		
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("Campaign");    
        HSSFRow row = sheet.createRow((int) 0);    
        HSSFCellStyle style = wb.createCellStyle(); 
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        for (int i = 0; i < excelHeader.length; i++) {    
            HSSFCell cell = row.createCell(i);    
            cell.setCellValue(excelHeader[i]);    
            cell.setCellStyle(style);    
            sheet.autoSizeColumn(i);    
        }
        for (int i = 0; i < list.size(); i++) {    
            row = sheet.createRow(i + 1);    
            row.createCell(0).setCellValue((String)list.get(i).get("actPulisher"));    
            row.createCell(1).setCellValue((String)list.get(i).get("actChecker"));    
            row.createCell(2).setCellValue((String)list.get(i).get("phone")); 
            row.createCell(3).setCellValue((String)list.get(i).get("beginTime")); 
            row.createCell(4).setCellValue((String)list.get(i).get("endTime")); 
            row.createCell(5).setCellValue((long)list.get(i).get("days")); 
            row.createCell(6).setCellValue((String)list.get(i).get("actReason")); 
            row.createCell(7).setCellValue((String)list.get(i).get("actPushTime")); 
           
        }
        response.setContentType("applicationnd.ms-excel");    
        response.setHeader("Content-disposition", "attachment;filename=activity.xls");    
        OutputStream ouputStream = response.getOutputStream();    
        wb.write(ouputStream);    
        ouputStream.flush();    
        ouputStream.close();
	}
	
	
	
	@RequestMapping("empExcel")
	public void emp(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String[] excelHeader = { "姓名", "手机号", "过期时间"};
		Long u_id=ShiroUtils.getUserId();
		EnterpriseEntity ent=new EnterpriseEntity();
        ent.setEntemp(u_id);
        Long c=entService.getEntId(ent);
        Map<String ,Object> map = new HashMap<String , Object>();
        map.put("empEnt", c);
		List<EmployeeEntity> list = empService.queryList(map);
		System.out.println("size="+list.size());
		
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("Campaign");    
        HSSFRow row = sheet.createRow((int) 0);    
        HSSFCellStyle style = wb.createCellStyle(); 
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        for (int i = 0; i < excelHeader.length; i++) {    
            HSSFCell cell = row.createCell(i);    
            cell.setCellValue(excelHeader[i]);    
            cell.setCellStyle(style);    
            sheet.autoSizeColumn(i);    
        }
        for (int i = 0; i < list.size(); i++) {    
            row = sheet.createRow(i + 1);    
            row.createCell(0).setCellValue(list.get(i).getEmpname());    
            row.createCell(1).setCellValue(list.get(i).getEmpphone());    
            row.createCell(2).setCellValue(list.get(i).getEmpexpiretime());  
            
        }
        response.setContentType("applicationnd.ms-excel");    
        response.setHeader("Content-disposition", "attachment;filename=emp.xls");    
        OutputStream ouputStream = response.getOutputStream();    
        wb.write(ouputStream);    
        ouputStream.flush();    
        ouputStream.close();
	}
	
	
	
	
	@RequestMapping("payExcel")
	public void pay(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		Long u_id=ShiroUtils.getUserId();
		EnterpriseEntity ent=new EnterpriseEntity();
        ent.setEntemp(u_id);
        Long c=entService.getEntId(ent);
		String[] excelHeader = { "员工姓名", "电话", "支付时间","过期时间","支付类型","支付金额"};
		Map<String,Object> map =new HashMap<String ,Object>();
        map.put("entId", c);
		
		List<Map<String, Object>> list = payService.payExcel(map);

		System.out.println("size="+list.size());
		
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("Campaign");    
        HSSFRow row = sheet.createRow((int) 0);    
        HSSFCellStyle style = wb.createCellStyle(); 
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        for (int i = 0; i < excelHeader.length; i++) {    
            HSSFCell cell = row.createCell(i);    
            cell.setCellValue(excelHeader[i]);    
            cell.setCellStyle(style);    
            sheet.autoSizeColumn(i);    
        }
        for (int i = 0; i < list.size(); i++) {    
            row = sheet.createRow(i + 1);    
            row.createCell(0).setCellValue((String)list.get(i).get("empname"));    
            row.createCell(1).setCellValue((String)list.get(i).get("empphone"));    
            row.createCell(2).setCellValue((String)list.get(i).get("payTime"));
            row.createCell(3).setCellValue((String)list.get(i).get("expireTime"));
            row.createCell(4).setCellValue((String)list.get(i).get("zflx"));
            System.out.println("aaaaaaaaaaaaaaaaa"+list.get(i).get("payMoney"));
            row.createCell(5).setCellValue(list.get(i).get("payMoney")==null?0:(long)list.get(i).get("payMoney"));
            
        }
        response.setContentType("applicationnd.ms-excel");    
        response.setHeader("Content-disposition", "attachment;filename=pay.xls");    
        OutputStream ouputStream = response.getOutputStream();    
        wb.write(ouputStream);    
        ouputStream.flush();    
        ouputStream.close();
	}
	
	
	
	
	@RequestMapping("custExcel")
	public void cust(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String[] excelHeader = { "客户姓名", "客户类型", "所在省","市","区","详细地址","手机号","添加人","添加时间"};
		
		Long u_id=ShiroUtils.getUserId();
		EnterpriseEntity ent=new EnterpriseEntity();
        ent.setEntemp(u_id);
        Long c=entService.getEntId(ent);
		
        Map<String , Object> map=new HashMap<String ,Object>();
        map.put("entId", c);
		
		List<Map<String ,Object>> list = custService.custExcel(map);

		System.out.println("size="+list.size());
		
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("Campaign");    
        HSSFRow row = sheet.createRow((int) 0);    
        HSSFCellStyle style = wb.createCellStyle(); 
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        for (int i = 0; i < excelHeader.length; i++) {    
            HSSFCell cell = row.createCell(i);    
            cell.setCellValue(excelHeader[i]);    
            cell.setCellStyle(style);    
            sheet.autoSizeColumn(i);    
        }
        for (int i = 0; i < list.size(); i++) {    
            row = sheet.createRow(i + 1);    
            row.createCell(0).setCellValue((String)list.get(i).get("custName"));    
            row.createCell(1).setCellValue((String)list.get(i).get("custType"));    
            row.createCell(2).setCellValue((String)list.get(i).get("custProvince"));    
            row.createCell(3).setCellValue((String)list.get(i).get("custCity"));    
            row.createCell(4).setCellValue((String)list.get(i).get("custArea"));    
            row.createCell(5).setCellValue((String)list.get(i).get("custAddress"));    
            row.createCell(6).setCellValue((String)list.get(i).get("custPhone"));    
            row.createCell(7).setCellValue((String)list.get(i).get("empName"));    
            row.createCell(8).setCellValue((String)list.get(i).get("custTime"));
           
        }
        response.setContentType("applicationnd.ms-excel");    
        response.setHeader("Content-disposition", "attachment;filename=cust.xls");    
        OutputStream ouputStream = response.getOutputStream();    
        wb.write(ouputStream);    
        ouputStream.flush();    
        ouputStream.close();
	}
	
	
	
	
	

}
