package com.springMVC.数据绑定_类型转换.EditorTest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 四、注册PropertyEditor
 * 1、使用WebDataBinder进行控制器级别注册PropertyEditor（控制器独享）
 * 使用WebDataBinder注册控制器级别的PropertyEditor，这种方式注册的
 * PropertyEditor只对当前控制器独享，即其他的控制器不会自动注册这个PropertyEditor，如果需要还需要再注册一
 * 下。
 * protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder){
 *         //super.initBinder(request, binder);
 *
 *         //注册自定义的属性编辑器
 *         //1、日期
 *         DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 *         CustomDateEditor dateEditor = new CustomDateEditor(df, true);
 *         //表示如果命令对象有Date类型的属性，将使用该属性编辑器进行类型转换
 *             binder.registerCustomEditor(Date .class,dateEditor);
 *         //自定义的电话号码编辑器
 *             binder.registerCustomEditor(PhoneNumberModel .class,new
 *
 *         PhoneNumberEditor());
 *     }
 *
 * 2、使用WebBindingInitializer批量注册PropertyEditor
 * 如果想在多个控制器同时注册多个相同的PropertyEditor时，可以考虑使用WebBindingInitializer。
 *
 * <!-- 注册WebBindingInitializer实现 -->
 * <bean id="myWebBindingInitializer" class="cn.javass.chapter4.web.controller.support.initializer.MyWebBindingInitializer"/>
 * <bean name="/dataBind" class="cn.javass.chapter4.web.controller.DataBinderTestController">
 * <!-- 注入WebBindingInitializer实现 -->
 * <property name="webBindingInitializer" ref="myWebBindingInitializer"/>
 * </bean>
 *
 * 使用WebBindingInitializer的好处是当你需要在多个控制器中需要同时使用多个相同的PropertyEditor可以在
 * WebBindingInitializer实现中注册，这样只需要在控制器中注入WebBindingInitializer即可注入多个
 * PropertyEditor
 */
public class MyWebBindingInitializer implements WebBindingInitializer {
    @Override
    public void initBinder(WebDataBinder binder) {
        //注册自定义的属性编辑器
        //1、日期
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor dateEditor = new CustomDateEditor(df, true);
        //表示如果命令对象有Date类型的属性，将使用该属性编辑器进行类型转换
        binder.registerCustomEditor(Date.class, dateEditor);
        //自定义的电话号码编辑器
        binder.registerCustomEditor(PhoneNumberModel.class, new PhoneNumberEditor());
    }


}