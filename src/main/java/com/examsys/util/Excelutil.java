package com.examsys.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.examsys.po.Employee;


public class Excelutil {

	//表格导出
	public static Workbook fillExcelDataWithTemplate(List<Employee> list,String templateFileName)throws Exception{
		InputStream inp=Excelutil.class.getResourceAsStream("/com/examsys/excel/"+templateFileName);
		POIFSFileSystem fs=new POIFSFileSystem(inp);
		Workbook wb=new HSSFWorkbook(fs);
		Sheet sheet=wb.getSheetAt(0);
		// 获取列数
		int cellNums=sheet.getRow(0).getLastCellNum();
		int rowIndex=1;
		/*while(rs.next()){
			Row row=sheet.createRow(rowIndex++);
			for(int i=0;i<cellNums;i++){
				row.createCell(i).setCellValue(rs.getObject(i+1).toString());
			}
		}*/
		
		for(Employee employee:list){
			Row row=sheet.createRow(rowIndex++);
			for(int i=0;i<cellNums;i++){
				if(i==0){
					row.createCell(i).setCellValue(employee.getId());
				}else if(i==1){
					row.createCell(i).setCellValue(employee.getUsername());
				}else if(i==2){
					row.createCell(i).setCellValue(employee.getNickname());
				}else if(i==3){
					row.createCell(i).setCellValue(employee.getRealname());
				}else if(i==4){
					row.createCell(i).setCellValue(employee.getJobInfo().getJob());
				}else if(i==5){
					row.createCell(i).setCellValue(employee.getDepartment().getDname());
				}else if(i==6){
					row.createCell(i).setCellValue(employee.getPhoneNo());
				}else if(i==7){
					row.createCell(i).setCellValue(employee.getOfficeTel());
				}else if(i==8){
					row.createCell(i).setCellValue(employee.getWorkStatu());
				}
			}
		}
		return wb;
	}
	public static void export(HttpServletResponse response,Workbook wb,String fileName)throws Exception{
		response.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("utf-8"),"iso8859-1"));
		response.setContentType("application/ynd.ms-excel;charset=UTF-8");
		OutputStream out=response.getOutputStream();
		wb.write(out);
		out.flush();
		out.close();
	}
	
	public static String formatCell(HSSFCell hssfCell){
		if(hssfCell==null){
			return "";
		}else{
			if(hssfCell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
				 String value="";
				if (HSSFDateUtil.isCellDateFormatted(hssfCell)) {  
	                //如果是date类型则 ，获取该cell的date值  
	                Date date = HSSFDateUtil.getJavaDate(hssfCell.getNumericCellValue());  
	                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
	                value = format.format(date);;  
	            }else {
				 BigDecimal big=new BigDecimal(hssfCell.getNumericCellValue());  
	              value = big.toString();
				 if(null!=value&&!"".equals(value.trim())){  
                     String[] item = value.split("[.]");  
                     if(1<item.length&&"0".equals(item[1])){  
                         value=item[0];  
                     }  
                }  }
				return value;
				
			}/* else if(hssfCell.getCellType()==HSSFCell.ENCODING_UNCHANGED){
				return String.valueOf(hssfCell.getDateCellValue());
			}*/
				return String.valueOf(hssfCell.getStringCellValue());
			
				
				/*if (HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
					Date d = cell.getDateCellValue();
					DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");*/
				
		
		}
	}
	public static java.sql.Date strToDate(String strDate) {  
        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");  
        java.util.Date d = new java.util.Date();  
        try {  
            d = format.parse(strDate); 
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        java.sql.Date date = new java.sql.Date(d.getTime());  
        return date;  
	}
	public static void main(String[] args) {
		Date strToDate = Excelutil.strToDate("2015-02-06");
		System.out.println(strToDate);
		
	}
}
