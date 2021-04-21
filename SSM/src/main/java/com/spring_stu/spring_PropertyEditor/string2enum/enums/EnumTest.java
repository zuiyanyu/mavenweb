package com.spring_stu.spring_PropertyEditor.string2enum.enums;

import com.alibaba.fastjson.JSON;
import com.beust.jcommander.internal.Maps;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.Map;
import java.util.stream.Stream;

public class EnumTest {
    @Test
   public void getEnumConstants_test(){
       Class<StatusEnum> targetType = StatusEnum.class ;
       StatusEnum[] enumConstants = targetType.getEnumConstants();
        /**
         * id = 1
         * id = 0
         */
       for (StatusEnum enumConstant : enumConstants) {
           System.out.println("id = "+enumConstant.getId());
       }
   }
   //TODO enum 转为 json
    @Test
    public void enumTojson_test(){
        Map<String, Object> params = Maps.newHashMap();
        params.put("action", StatusEnum.OFF);
        String s = JSON.toJSONString(params);

        //{"action":"OFF"}
        System.out.println(s);

        System.out.println(JSON.toJSONString(GenderEnum.MALE));

    }
    @Test
    public void ObjectMapper_Test() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        //[{"id":0,"性别":"男"},{"id":1,"性别":"女"}
        System.out.println(mapper.writeValueAsString(GenderEnum.values()));

        //{"id":1,"性别":"女"}
        System.out.println(mapper.writeValueAsString(GenderEnum.valueOf("FEMALE")));
    }
    @Test
    //json转为 enum
    public void jsonTOenum_test(){
        String jsonStr = "{\"action\":\"OFF\"}";
       // StatusEnum.fromString(json.getString("action"));

        //方式1
        StatusEnum off = StatusEnum.valueOf("OFF");
        System.out.println(off);


        //方式2
        String enumName = "OFF" ;
        StatusEnum statusEnum = Stream.of(StatusEnum.values())
                .filter(action -> enumName.equalsIgnoreCase(action.name()))
                .findFirst()
                .orElse(null);
        System.out.println(statusEnum);
    }
}
