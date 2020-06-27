package controller;

import com.mavenweb.domain.DqReportItemLocal;
import com.mavenweb.domain.RepItemEqu;
import com.mavenweb.service.DqReportItemLocalService;
import com.mavenweb.service.DqReportItemService;
import com.mavenweb.service.RepItemEquService;
import com.mavenweb.utils.SpringJUnitUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DqReportController extends SpringJUnitUtils {
private final Logger LOGGER = LoggerFactory.getLogger(DqReportController.class) ;


    @Resource
    DqReportItemService dqReportItemService ;
    @Resource
    DqReportItemLocalService  dqReportItemLocalService ;
    @Resource
    RepItemEquService repItemEquService ;

    @Test
    public void getRepItemEqu(){
        // 获取item 与 id 的映射关系
        List<Map<String,String>>  itemIdMapList = repItemEquService.getItemIdMap();
        Map<String,String> itemIdMap = new HashMap() ;
        itemIdMapList.forEach(map->{
            itemIdMap.put(map.get("key"),map.get("value"));
        });


        //先从 rep_item_equ 中获取item的原始规则、
        List<RepItemEqu> repItemEquItemList = repItemEquService.getRepItemEquItems();

        List<RepItemEqu> errorList = new ArrayList<>() ;
        repItemEquItemList.forEach(repItemEquItem->{
            String repSname = repItemEquItem.getRepSname();
            // 为每条数据项设置id
            String repItemAddr = repItemEquItem.getRepItemAddr();

            String key = repSname+":"+repItemAddr ;
            String value = itemIdMap.get(key) ;
            if(null==value){
                repItemEquItem.setExt("主键获取失败：{"+key+"->"+value+"}");
                errorList.add(repItemEquItem);
            }
//            if(null==repItemEquItem.getRepItemId()  || "".equals(repItemEquItem.getRepItemId()) ){
//                repItemEquItem.setRepItemId(value);
//            }
            repItemEquItem.setRepItemId(value);
            //为每条数据项的内置规则用 数据项id表示
            String repItemEquOrigin = repItemEquItem.getRepItemEquOrigin();
            String[] split = repItemEquOrigin.split("\\+");
            StringBuilder buf = new StringBuilder();
            for (String item : split) {
                key = repSname+":"+item ;
                value=itemIdMap.get(key);
                if(null==value){
                    repItemEquItem.setExt("规则主键获取失败：{"+key+"->"+value+"}");
                    errorList.add(repItemEquItem);
                }
                buf.append(value).append("+");
            }
            String itemEquOrigin = buf.toString();
            itemEquOrigin=itemEquOrigin.substring(0,itemEquOrigin.lastIndexOf("+")) ;
            repItemEquItem.setRepItemEqu(itemEquOrigin);
        });
        //repItemEquItemList.forEach(System.out::println);
        if(errorList.size()>0){
            System.out.println("======查询id失败的items=======");
            errorList.forEach(System.out::println);
        }else{
            System.out.println("====要更改的item:====");
            //repItemEquItemList.forEach(System.out::println);
            System.out.println(repItemEquItemList.get(0));

            repItemEquItemList.forEach(repItemEquService::updateOneRepItemEqu);
          //  repItemEquService.updateRepItemEqu(repItemEquItemList);
          //  repItemEquService.updateOneRepItemEqu(repItemEquItemList.get(0));
        }

    }

    /**
     * 为没有id的记录 生成id并更新
     */
    //@Test
    public void generateForDqReportItemLocal(){
         //获取最大的ID
        String maxID_dqReportItem = dqReportItemService.getMaxID();
        LOGGER.info("maxID_dqReportItem={}",maxID_dqReportItem);

        String maxID_dqReportItemLocal = dqReportItemLocalService.getMaxID();
        LOGGER.info("maxID_dqReportItemLocal={}",maxID_dqReportItemLocal);

        Integer maxId = this.getMaxId(maxID_dqReportItem, maxID_dqReportItemLocal);
        LOGGER.info("maxid={}",maxId);

        //获取要更新ID的数据
        List<DqReportItemLocal> willBeUpdatedItems = dqReportItemLocalService.getWillBeUpdatedItems();
       // willBeUpdatedItems.forEach(System.out::println);

        String newId ;
        for (DqReportItemLocal dqReportItemLocal : willBeUpdatedItems) {
            maxId ++ ;
            newId =  "R00"+ maxId ;
            dqReportItemLocal.setRepItemId(newId);
            System.out.println(dqReportItemLocal);
            dqReportItemLocalService.updateItemId(dqReportItemLocal);
        }

    }
    private Integer getMaxId(String id1,String id2){
        String maxId = id1 ;
        if(id1 ==null ){
            maxId = id2 ;
        }
        if(id2==null){
            maxId = "0" ;
        }
        LOGGER.info("maxid={}",maxId);
        maxId = maxId.substring(maxId.indexOf("R")+1);
        LOGGER.info("maxid={}",maxId);
        return Integer.parseInt(maxId) ;
    }


}
