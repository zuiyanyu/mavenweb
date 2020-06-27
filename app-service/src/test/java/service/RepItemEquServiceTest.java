package service;

import com.mavenweb.domain.RepItemEqu;
import com.mavenweb.service.RepItemEquService;
import com.mavenweb.utils.SpringJUnitUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepItemEquServiceTest extends SpringJUnitUtils {
    @Resource
    RepItemEquService repItemEquService ;

    @Test
    public void getItemIdMapTest(){
        List<Map<String,String>>  itemIdMapList = repItemEquService.getItemIdMap();
        Map itemIdMap = new HashMap() ;
        itemIdMapList.forEach(map->{
            itemIdMap.put(map.get("key"),map.get("value"));
        });
        System.out.println(itemIdMap);
    }
    @Test
    public void getRepItemEquItemsTest(){
        List<RepItemEqu> repItemEquItems = repItemEquService.getRepItemEquItems();
        repItemEquItems.forEach(System.out::println);
    }
}
