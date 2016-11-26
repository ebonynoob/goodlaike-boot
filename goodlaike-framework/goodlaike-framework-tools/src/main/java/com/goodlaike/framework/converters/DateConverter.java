package com.goodlaike.framework.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 日期转换器
 *
 * @author Jail Hu
 */
public class DateConverter implements Converter<String, Date> {

	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 支持 long 与 yyyy-MM-dd HH:mm:ss
	 */
	@Override
	public Date convert(String source) {
		try {
			return new Date(Long.parseLong(source));
		} catch (NumberFormatException e) {
		}
		try {
			return sdf.parse(source);
		} catch (ParseException e) {
			return null;
		}
	}
}
