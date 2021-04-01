package com.springbootexample.springsecurityjwt.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateUtils {

	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String T_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	public static String format(LocalDate date){
		if(date == null) return null;
		return date.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
	}

	public static String format(LocalDate date, String formatStr){
		if(date == null) return null;
		LocalDateTime dateTime = date.atTime(0, 0, 0);
		return dateTime.format(DateTimeFormatter.ofPattern(formatStr));
	}

	public static String format(LocalDateTime dateTime){
		if(dateTime == null) return null;
		return dateTime.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT));
	}

	public static String tFormat(LocalDateTime dateTime){
		if(dateTime == null) return null;
		return dateTime.format(DateTimeFormatter.ofPattern(T_DATE_TIME_FORMAT));
	}

	public static String format(LocalDateTime dateTime, String formatStr){
		if(dateTime == null) return null;
		return dateTime.format(DateTimeFormatter.ofPattern(formatStr));
	}

	public static LocalDate parseDate(String dateStr){
		if(dateStr == null) return null;
		return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
	}

	public static LocalDate parseDate(String dateStr, String dateFormatStr){
		if(dateStr == null) return null;
		return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(dateFormatStr));
	}

	public static LocalDateTime parseDateTime(String dateStr){
		if(dateStr == null) return null;
		return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
	}

	public static LocalDateTime parseDateTime(String dateStr, String dateTimeFormatStr){
		if(dateStr == null) return null;
		return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(dateTimeFormatStr));
	}

}
