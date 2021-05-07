package com.hive.functions.udtf;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 定义临时函数：
 * add jar /opt/resource/hiveJars/HiveClient.1.0-SNAPSHOT.jar;
 *
 * create temporary function base_analizer as 'com.hive.functions.udf.BaseFieldUDF';
 * create temporary function flat_analizer as 'com.hive.functions.udtf.FlatEventUDTF';
 * ===========
 * -- 1. 定义全局的UDF函数
 * export HIVE_AUX_JARS_PATH=/opt/resource/hiveJars/HiveClient.1.0-SNAPSHOT.jar
 *
 * -- 2. 自定义函数（dbevear中执行）
 * create FUNCTION gmall.base_analizer   as "com.hive.functions.udf.BaseFieldUDF" ;
 * create function gmall.flat_analizer   as "com.hive.functions.udtf.FlatEventUDTF";
 */
public class FlatEventUDTF extends GenericUDTF {
    //该方法中，我们将指定输出参数的名称和参数类型：
    @Override
    public StructObjectInspector initialize(ObjectInspector[] argOIs) throws UDFArgumentException {

        ArrayList<ObjectInspector> fieldOIs = new ArrayList<ObjectInspector>();
        ArrayList<String> fieldNames = new ArrayList<String>();

//        for (ObjectInspector oIs : argOIs) {
//            String typeName = oIs.getTypeName();
//            fieldNames.add(typeName);
//            fieldOIs.add(oIs);
//        }

        fieldNames.add("event_name");
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);

        fieldNames.add("event_json");
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);

        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames, fieldOIs);
    }

    /**
     *  一进多出 或者是多进多出. 这里 输入1条记录，输出若干条结果
     * @param objects objects[0] 代表第一条进入的参数；一次类推
     * @throws HiveException
     */
    @Override
    public void process(Object[] objects) throws HiveException {

        // 获取传入的 et
        String events  = objects[0].toString();

        // 如果传进来的数据为空，直接返回过滤掉该数据
        if (StringUtils.isBlank(events)) {
            return;
        } else {

            try {
                // 获取一共有几个事件（ad/facoriters）
                JSONArray eventArray = new JSONArray(events);

                if (eventArray == null)
                    return;

                // 循环遍历每一个事件
                JSONObject event  ;
                for (int i = 0; i < eventArray.length(); i++) {
                    // 将结果返回
                    List<Object> retList = null ;

                    try {
                        //获取当前的event详情
                        event = eventArray.getJSONObject(i);

                        //String ett = event.getString("ett");
                        // 取出每个的事件名称（ad/facoriters）
                        String eventName = event.getString("en");

                        // 取出每一个事件整体
                        //String kv = event.getString("kv");

                        //retList = Arrays.asList(ett,eventName,kv);

                        Object event_json = eventArray.get(i);
                        //event_name , event_json
                        retList = Arrays.asList(eventName,event_json);

                    } catch (JSONException e) {
                        continue;
                    }
                    forward(retList);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    //当没有记录处理的时候该方法会被调用，用来清理代码或者产生额外的输出
    @Override
    public void close() throws HiveException {

    }
}
