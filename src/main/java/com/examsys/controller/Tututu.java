package com.examsys.controller;

import java.awt.Font;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.examsys.po.Custom;
import com.examsys.service.CustomService;

@Controller
public class Tututu {
	
	@Resource
	private CustomService customService;
	@RequestMapping("/ttuu")
	@ResponseBody
	public String genBarChart(HttpSession session) throws Exception{
		List<Custom> list = customService.getList();
		 int wsm=0;
		 int ysm=0;
		 int gjz=0;
		 int zxz=0;
		 int sd=0;
		 int ybm=0;
		 for(Custom cs:list){
			 if("0".equals(cs.getCustomStatu())){
				 wsm++;
			 }else if("1".equals(cs.getCustomStatu())){
				 ysm++;
			 }else if("2".equals(cs.getCustomStatu())){
				 gjz++;
			 }else if("3".equals(cs.getCustomStatu())){
				 zxz++;
			 }else if("4".equals(cs.getCustomStatu())){
				 sd++;
			 }else if("5".equals(cs.getCustomStatu())){
				 ybm++;
			 }
		 }
		 DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		 	dataset.addValue(wsm, "客户状态", "新增未上门");
			dataset.addValue(ysm, "客户状态", "新增已上门");
			dataset.addValue(gjz, "客户状态", "销售跟进中");
			dataset.addValue(zxz, "客户状态", "咨询跟进中");
			dataset.addValue(sd, "客户状态", "死单");
			dataset.addValue(ybm, "客户状态", "已报名");
		
			// 解决图片中文乱码问题
		 //创建主题样式  
	       StandardChartTheme standardChartTheme=new StandardChartTheme("CN");  
	       //设置标题字体  
	       standardChartTheme.setExtraLargeFont(new Font("隶书",Font.BOLD,20));  
	       //设置图例的字体  
	       standardChartTheme.setRegularFont(new Font("宋体",Font.PLAIN,15));  
	       //设置轴向的字体  
	       standardChartTheme.setLargeFont(new Font("宋体",Font.PLAIN,15));  
	       //应用主题样式  
	       ChartFactory.setChartTheme(standardChartTheme);  
		
	       
	       
			JFreeChart chart=ChartFactory.createBarChart3D("客户状态图", "状态", "人数量", dataset,
					PlotOrientation.VERTICAL, true, true, true);
			// 显示每个柱的数值，并修改该数值的字体属性
		/*	BarRenderer3D renderer=new BarRenderer3D();
			renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
			renderer.setBaseItemLabelsVisible(true);
			
			renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
			renderer.setItemLabelAnchorOffset(10D);  
			
			// 设置平行柱的之间距离
			renderer.setItemMargin(0.4);
			//创建主题样式  
		       StandardChartTheme standardChartTheme=new StandardChartTheme("CN");  
		       //设置标题字体  
		       standardChartTheme.setExtraLargeFont(new Font("隶书",Font.BOLD,20));  
		       //设置图例的字体  
		       standardChartTheme.setRegularFont(new Font("宋书",Font.PLAIN,15));  
		       //设置轴向的字体  
		       standardChartTheme.setLargeFont(new Font("宋书",Font.PLAIN,15));  
		       //应用主题样式  
		       ChartFactory.setChartTheme(standardChartTheme); */ 
			/*TextTitle textTitle = chart.getTitle();
			textTitle.setFont(new Font("宋体", Font.BOLD, 20));*/
			String fileName=ServletUtilities.saveChartAsPNG(chart, 700, 500, null,session);
			System.out.println(fileName);
			return fileName;
	}

}
