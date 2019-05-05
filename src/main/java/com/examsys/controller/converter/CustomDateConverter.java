package com.examsys.controller.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * created on 2017年2月6日 
 *
 * 自定义日期转换器
 *
 * @author  hwua
 * @version  1.0.0
 */
public class CustomDateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		
		try {
			//进行日期转换
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(source);
			
		} catch (Exception e) {
			e.printStackTrace();
			//进行日期转换
			try {
				return new SimpleDateFormat("yyyy-MM-dd").parse(source);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		
		return null;
	}

}
