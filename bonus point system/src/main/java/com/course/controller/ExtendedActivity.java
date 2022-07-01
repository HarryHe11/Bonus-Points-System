package com.course.controller;

import com.course.pojo.PointObject;
import com.course.utils.FileUtils;
import com.course.utils.JsonUtils;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * @author lixuy
 * Created on 2019-04-11
 */
@Component
//参加扩展活动
public class ExtendedActivity {

    public void extendedActivity(){
        String file = FileUtils.readFile("score");
        PointObject pointObject = JsonUtils.jsonToPojo(file, PointObject.class);//序列化，string转换成object
        Calendar last_time = pointObject.getExchangePointTime();
        System.out.println("Got last_time: " + last_time); 
        if(last_time==null){
            //首次对可兑换积分加分
            pointObject.setExchangePointTime();
            last_time = Calendar.getInstance();
        }
        Calendar this_time = Calendar.getInstance();
        if(((this_time.get(Calendar.YEAR)-last_time.get(Calendar.YEAR))==1&&this_time.get(Calendar.MONTH)>=last_time.get(Calendar.MONTH)&&
            this_time.get(Calendar.DATE)>=last_time.get(Calendar.DATE))||this_time.get(Calendar.YEAR)-last_time.get(Calendar.YEAR)>1)//上次积分时间与本次的差值是否大于一年？
        {
            pointObject.setExchangeScore(0);//积分置零
        }
        String content = JsonUtils.objectToJson(pointObject);
        FileUtils.writeFile("score", content);
        System.out.println("======被拦截的extendedActivity方法执行======");
    }

}
