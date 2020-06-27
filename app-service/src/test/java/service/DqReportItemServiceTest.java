package service;

import com.mavenweb.service.DqReportItemService;
import com.mavenweb.utils.SpringJUnitUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;



public class DqReportItemServiceTest extends SpringJUnitUtils {

    @Autowired
    private  DqReportItemService dqReportItemService ;

    @Test
    public void getMaxIdTest(){
        String maxID = dqReportItemService.getMaxID();
        System.out.println(maxID) ;
    }


    public static class DqReportItemLocalServiceTest extends SpringJUnitUtils {
    }
}
