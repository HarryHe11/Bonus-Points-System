package com.course.service;


import java.util.Calendar;

import com.course.pojo.PointObject;
import com.course.utils.FileUtils;
import com.course.utils.JsonUtils;

/**
 * @author lixuy
 * Created on 2019-04-11
 */
//类名与方法名须与controller层拦截的方法一致
public class Level {

    public void level(){
    	String file = FileUtils.readFile("score");
		PointObject pointObject = JsonUtils.jsonToPojo(file, PointObject.class);
		Integer growScore = pointObject.getGrowScore();
		// 获取score文件中所记录的日期，即上一次等级评估的日期
		int year = pointObject.getyear(); 
		int month = pointObject.getmonth();
		// 获取此刻的日期
		Calendar newcal = Calendar.getInstance();
		int newyear = newcal.get(Calendar.YEAR); 
		int newmonth = newcal.get(Calendar.MONTH) + 1;
		int newday = newcal.get(Calendar.DAY_OF_MONTH); 
		System.out.println("此刻日期： ："+ newyear + '.' +newmonth+'.'+newday);
		if (year == newyear && month == newmonth) {
			//如果处于同一个月则进行评级,默认当月初次登录，默认等级为'C'
			if (0 <= growScore && growScore <= 10) {
				pointObject.setlevel('C');
				System.out.println("您的成长积分评级为 ："+pointObject.getlevel());
			} else if (10 < growScore && growScore <= 25) {
				pointObject.setlevel('B');
				System.out.println("您的成长积分评级为 ："+pointObject.getlevel());
			} else if (25 < growScore) {
				pointObject.setlevel('A');
				System.out.println("您的成长积分评级为 ："+pointObject.getlevel());
			}
		} 
		// 将时间更新为此刻的日期
		pointObject.setyear(); 
		pointObject.setmonth();
		pointObject.setday();
		String content = JsonUtils.objectToJson(pointObject);
		FileUtils.writeFile("score", content);
        System.out.println("+++++level成长积分评级计算方法执行+++++");
    }

}
