package com.springMVC.数据绑定_类型转换.EditorTest;

import java.util.Date;
import java.util.List;
import java.util.Map;
//接下来我们写自定义的属性编辑器进行数据绑定：
//（1、模型对象：
public class DataBinderTestModel {
    private String username;
    private boolean bool;//Boolean值测试
    private SchoolInfoModel schooInfo;
    private List hobbyList;//集合测试，此处可以改为数组/Set进行测试
    private Map map;//Map测试
    private PhoneNumberModel phoneNumber;//String->自定义对象的转换测试
    private Date date;//日期类型测试
    private UserState state;//String——>Enum类型转换测试
//省略getter/setter
}
