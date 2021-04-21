package com.springMVC.数据绑定_类型转换.EditorTest;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//(2、PhoneNumber属性编辑器
//PropertyEditorSupport：一个PropertyEditor的支持类；
public class PhoneNumberEditor extends PropertyEditorSupport {
        Pattern pattern = Pattern.compile("^(\\d{3,4})-(\\d{7,8})$");

    /**
     * setAsText：表示将String——>PhoneNumberModel，根据正则表达式进行转换，如果转换失败抛出异常，则接下
     * 来的验证器会进行验证处理；
     * @param text
     * @throws IllegalArgumentException
     */
    @Override
        public void setAsText(String text) throws IllegalArgumentException {
            if(text == null || !StringUtils.hasLength(text)) {
                setValue(null); //如果没值，设值为null
            }
            Matcher matcher = pattern.matcher(text);
            if(matcher.matches()) {
                PhoneNumberModel phoneNumber = new PhoneNumberModel();
                phoneNumber.setAreaCode(matcher.group(1));
                phoneNumber.setPhoneNumber(matcher.group(2));
                setValue(phoneNumber);
            } else {
                throw new IllegalArgumentException(String.format("类型转换失败，需要格式[010-12345678]，但格式是[%s]", text));
            }
        }

    /**
     * getAsText：表示将PhoneNumberModel——>String
     * @return
     */
    @Override
        public String getAsText() {
            PhoneNumberModel phoneNumber = ((PhoneNumberModel)getValue());
            return phoneNumber == null ? "" : phoneNumber.getAreaCode() + "-" + phoneNumber.getPhoneNumber();
        }

}
