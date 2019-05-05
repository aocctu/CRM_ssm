package com.examsys.controller;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.core.util.JsonUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.examsys.po.Department;
import com.examsys.po.Employee;
import com.examsys.po.JobInfo;
import com.examsys.service.DepartmentService;
import com.examsys.service.EmployeeService;
import com.examsys.service.JobInfoService;
import com.examsys.util.Excelutil;

@Controller
@RequestMapping("/excel")
public class ExcelController {
	
	@Resource
	private EmployeeService employeeService;
	
	@Resource
	private DepartmentService departmentService;
	
	@Resource
	private JobInfoService jobInfoService;
	//导出
	@RequestMapping("/out")
	public String getItemList(HttpServletResponse response,HttpServletRequest request) throws Exception{
		String []ids=request.getParameter("ids").split(",");
		List<Employee> list=new ArrayList<>();
		for(int i=0;i<ids.length;i++){
			Employee employee = employeeService.get(Integer.valueOf(ids[i]).intValue());
			list.add(employee);
		}
		Workbook wb = Excelutil.fillExcelDataWithTemplate(list, "SptypeExporTemplate.xls");
		Excelutil.export(response, wb, "员工信息数据表.xls");
		return null;
	}
	//导入
	@RequestMapping("/inser")
	public String inList(HttpServletRequest request,
			@RequestParam(name="userUploadFile",required=true) MultipartFile excelFile) throws Exception{
		//CustomResult result=new CustomResult();
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		//File uploadfile=new File(userUploadFile)
		  String path = "D:\\Documents\\Downloads";  
	        //容错处理  
	        File dir = new File(path);  
	        if(!dir.exists()) {  
	            dir.mkdirs();  
	        }  
	        String fileName = excelFile.getOriginalFilename();//report.xls  
	        String fileName2 = excelFile.getName();//excelFile  
	          
	        InputStream fis = excelFile.getInputStream();
		//POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream(userUploadFile));
		
		HSSFWorkbook wb=new HSSFWorkbook(fis);
		
		HSSFSheet hssfSheet=wb.getSheetAt(0);  // 获取第一个Sheet页
		if(hssfSheet!=null){
			for(int rowNum=1;rowNum<=hssfSheet.getLastRowNum();rowNum++){
				HSSFRow hssfRow=hssfSheet.getRow(rowNum);
				if(hssfRow==null){
					continue;
				}
				 Employee employee=new Employee();
				 employee.setUsername(Excelutil.formatCell(hssfRow.getCell(1)));
				 employee.setPass(Excelutil.formatCell(hssfRow.getCell(2)));
				 employee.setNickname(Excelutil.formatCell(hssfRow.getCell(3)));
				 employee.setRealname(Excelutil.formatCell(hssfRow.getCell(4)));
				 Department department = departmentService.get(Integer.parseInt(Excelutil.formatCell(hssfRow.getCell(5))));
				JobInfo jobInfo = jobInfoService.get(Integer.parseInt(Excelutil.formatCell(hssfRow.getCell(6))));
				employee.setJobInfo(jobInfo);
				employee.setDepartment(department);
				/* employee.getJobInfo().setId(Integer.parseInt(Excelutil.formatCell(hssfRow.getCell(4))));
				 employee.getDepartment().setId(Integer.parseInt(Excelutil.formatCell(hssfRow.getCell(5))));*/
				 employee.setPhoneNo(Excelutil.formatCell(hssfRow.getCell(7)));
				 employee.setOfficeTel(Excelutil.formatCell(hssfRow.getCell(8)));
				 employee.setWorkStatu(Excelutil.formatCell(hssfRow.getCell(9)));
				 boolean flag = employeeService.add(employee);
					
					if(flag){
						jsonDatas.put("status", 1);//设置状态为1，表示操作成功
					}
			}}
		return "jsonDatas";
	}

		
		
		
}
