package com.springMVC.数据绑定_类型转换.EditorTest;

import lombok.Data;

//如格式010-12345678
@Data
public class PhoneNumberModel {
    private String areaCode;//区号
    private String phoneNumber;//电话号码
}
