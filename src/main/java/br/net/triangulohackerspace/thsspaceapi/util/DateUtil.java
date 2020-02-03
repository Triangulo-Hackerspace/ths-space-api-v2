package br.net.triangulohackerspace.thsspaceapi.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getNowDate() {
		String expectedPattern = "dd/MM/yyyy HH:mm:ss";
		DateFormat df = new SimpleDateFormat(expectedPattern);
		return df.format(new Date());
	}
	
	public static Date getAtualDate() {
		return new Date();
	}
	
	public static Date getPlusDateByDay(int days) {
		return new Date(new Date().getTime()+((1000*24*60*60)*days));  
	}
}
