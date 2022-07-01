package com.course;

import com.course.controller.TestDesign;
import com.course.pojo.PointObject;
import com.course.utils.FileUtils;
import com.course.utils.JsonUtils;


import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//在此处引入你要调用的包
import com.course.controller.Level;
import com.course.controller.FillInformation;
import com.course.controller.ExtendedActivity;
import com.course.controller.Login;
import com.course.controller.FollowUp;
import com.course.controller.EvaluateReport;
import com.course.controller.BloodSugar;
import com.course.controller.BfzNote;
import com.course.controller.YdgnNote;
import com.course.controller.ResearchRecruitment;
/**
 * @author lixuy
 * Created on 2019-04-10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/*.xml"})
public class TestInterceptor {
	@Autowired
	TestDesign testDesign;
	@Autowired
    ExtendedActivity extendedActivity;
	@Autowired
    BloodSugar bloodSugar;
	@Autowired
    YdgnNote ydgnNote;
	@Autowired
	FillInformation fillinformation;
	@Autowired
	EvaluateReport evaluateReport;
	@Autowired
	ResearchRecruitment researchRecruitment;
	@Autowired
	Level level;
	@Autowired
	FollowUp followUp;
	@Autowired 
	Login login;
	@Autowired
	BfzNote bfzNote;

	public static void clearScreen(){  
        try{
            
            System.out.print("\033[H\033[2J");  
            System.out.flush(); 
        } catch (Exception e) { 
            System.out.println("Got an exception!"); 
        } 
    }  

    //检验当前积分情况
    private int assertScore(){
        try {
            String file = FileUtils.readFile("score");
            PointObject pointObject = JsonUtils.jsonToPojo(file,PointObject.class);
			System.out.println("------------------------------------------------");
			System.out.println("---------------------SCORE----------------------"); 
			System.out.println("------------------------------------------------");
            System.out.println("Grow Score: "+pointObject.getGrowScore());
            System.out.println("Exchange Score: "+pointObject.getExchangeScore());
            System.out.println("Total Score: "+pointObject.getScoreTotal());
			System.out.println("------------------------------------------------");
            System.out.println("Login: "+pointObject.getLogin());
            System.out.println("Fill in Information: "+pointObject.getFillInformation());
            System.out.println("Record Blood Sugar: "+pointObject.getBloodSugar());
            System.out.println("Record Comlication: "+pointObject.getbfzNote());
            System.out.println("Generate Evaluation Report: "+pointObject.getEvaluateReport());
            System.out.println("Monitor Islet Function: "+pointObject.getydgn());
            System.out.println("Get Follow-up: "+pointObject.getFollowup());
            System.out.println("Attend Extended Activity: "+pointObject.getExtendedactivity());
            System.out.println("Participate in Research Recruitment: "+pointObject.getResearchRecruitment());
			System.out.println("------------------------------------------------");
            return pointObject.getScoreTotal();
    // \end{step 3}
        }catch (Exception e){
            e.printStackTrace();
        }
		return 0;        
    }
    
    //记录成长积分
    private int assertGrowScore() {
		try {
			String file = FileUtils.readFile("score");
			PointObject pointObject = JsonUtils.jsonToPojo(file, PointObject.class);
			return pointObject.getGrowScore();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
    //记录可兑换积分的情况
	private int assertExchangeScore() {
		try {
			String file = FileUtils.readFile("score");
			PointObject pointObject = JsonUtils.jsonToPojo(file, PointObject.class);
			return pointObject.getExchangeScore();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

    // \begin{step4: 增加单元测试代码}
	/* 1. 登陆平台的测试方法  */
	@Test
	public void loginTest() {  //空参方法
		try {
			clearScreen();
			int score1=assertScore();
			login.login();
			int score2=assertScore();
		}catch (Exception e) {
			
		}
	}

	//2. 测试填写个人资料
	@Test
	public void fillinformation() {
		try {
			clearScreen();
			int score1 = assertScore();
			fillinformation.fillInformation();
			int score2 = assertScore();
			assertEquals(2, score2-score1);
		}catch (Exception e) {
		}
	}

	//3. 测试血糖记录
	@Test
    public void bloodSugar(){
        try{
			clearScreen();
            int score1 = assertScore();
            bloodSugar.bloodSugar();
            int score2 = assertScore();
        }catch(Exception e){}
        }
	
	//4. 测试并发症记录填写	
	@Test
	public void bfzNoteTest() {  //空参方法
		try {
			clearScreen();
			int score1=assertScore();
			bfzNote.bfzNote();
			int score2=assertScore();
			
		}catch (Exception e) {
		}
	}
	
	//5. 测试生成评估报告
	@Test
	public void evaluateReport() {
		try {
			clearScreen();
			int score1=assertScore();
			evaluateReport.evaluateReport();
			int score2=assertScore();
			assertEquals(2, score2-score1);
		}catch (Exception e) {
		}	
	}

	//6.测试胰岛功能记录
	@Test
    public void ydgnNote(){
        try{
			clearScreen();
            int score1 = assertScore();
            ydgnNote.ydgnNote();
            int score2 = assertScore();
    	}catch (Exception e) {
		}
	}

    //7. 测试门诊随访
    @Test
    public void followUpTest() {  //空参方法
    	try {
			clearScreen();
    		int score1=assertScore();
    		followUp.followUp();
    		int score2=assertScore();
    		assertEquals(3, score2-score1);
    	}catch (Exception e) {
			// TODO: handle exception
		}
    }
    
	//8. 测试参加扩展活动
    @Test
    public void extendActivity(){
        try{
			clearScreen();
            int score1 = assertScore();
            extendedActivity.extendedActivity();
            int score2 = assertScore();
            assertEquals(5, score2-score1);
        }catch(Exception e){
            // TODO: handle exception
        }
    }    

    //9. 测试参加科研招募
    @Test
    public void researchRecruitment() {
    	try {
			clearScreen();
    		int score1=assertScore();
    		researchRecruitment.researchRecruitment();
    		int score2=assertScore();

    	}catch (Exception e) {
			// TODO: handle exception
		}
    }

    //10. 扩展功能，测试成长积分评级
    @Test
    public void level() {
        try {
			clearScreen();
            int score1=assertScore();
            level.level();
            int score2=assertScore();
        }catch (Exception e) {
            // TODO: handle exception
        }
    }
}
    // \end{step4}

