package service;

import com.mavenweb.mapper.DqReportItemMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DqReportItemMapperTest {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        DqReportItemMapper dqReportItemMapper = (DqReportItemMapper)ctx.getBean("dqReportItemMapper");
        String userName = dqReportItemMapper.getMaxID();
        System.out.println(userName);
    }
    @Test
    public void getMaxIdTest(){
        //   String maxID = dqReportItemService.getMaxID();
        // System.out.println(maxID); dqReportItemService


    }
}
