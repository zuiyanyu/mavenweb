package com.spring_stu.spring_PropertyEditor.customPropertyEditor;

import java.beans.PropertyEditorSupport;

public class CarEditor extends PropertyEditorSupport {
    /**
     * 将字面值转换为属性类型对象 。
     * 字面值采用逗号分隔的格式同时为brand、maxSpeed和price属性值提供设置，setAsText()方法解析这个字面值并生成对应的Car对象。
     * 由于我们并不需要将Boss内部的car属性反显到属性编辑器中，因此不需要覆盖getAsText()方法。
     *
     * @param text
     * @throws IllegalArgumentException
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(null ==text || text.indexOf(",")==-1){
            throw new IllegalArgumentException("设置的字符串格式不正确");
        }
        String[] infos = text.split(",");
        Car car = new Car();
        car.setBrand(infos[0]);
        car.setMaxSpeed(Integer.parseInt(infos[1]));
        car.setPrice(Double.parseDouble(infos[1]));

        //调用父类的setValue()方法设置转换后的属性对象
        super.setValue(car);
    }
}