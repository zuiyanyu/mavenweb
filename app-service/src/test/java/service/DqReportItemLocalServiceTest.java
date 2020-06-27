package service;

import com.mavenweb.domain.DqReportItemLocal;
import com.mavenweb.service.DqReportItemLocalService;
import com.mavenweb.utils.SpringJUnitUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class DqReportItemLocalServiceTest extends SpringJUnitUtils {

    @Resource
    DqReportItemLocalService dqReportItemLocalService ;

    @Test
    public void updateItemIdTest(){
        DqReportItemLocal dqReportItemLocal = null ;

        dqReportItemLocalService.updateItemId(dqReportItemLocal);
    }
    @Test
    public void getMaxIdTest(){
           String maxID = dqReportItemLocalService.getMaxID();
           System.out.println(maxID);
    }

    @Test
    public void getWillBeUpdatedItemsTest(){
        List<DqReportItemLocal> willBeUpdatedItems = dqReportItemLocalService.getWillBeUpdatedItems();
        willBeUpdatedItems.forEach(System.out::println) ;
    }
}
