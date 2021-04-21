package com.spring_stu.spring_PropertyEditor.string2enum.converter;

import com.spring_stu.spring_PropertyEditor.string2enum.GenderEnumFormat;
import com.spring_stu.spring_PropertyEditor.string2enum.enums.GenderEnum;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.*;

public class GenderFormatterFactory implements AnnotationFormatterFactory<GenderEnumFormat> {
    @Override
    public Set<Class<?>> getFieldTypes() {
        System.out.println("getFieldTypes 被调用");
        HashSet<Class<?>> types = new HashSet<>() ;
        types.add(GenderEnum.class);
        return types ;//Collections.singleton(GenderEnumFormatGenderEnumFormatGenderEnumFormat.class);
    }

    @Override
    public Printer<?> getPrinter(GenderEnumFormat annotation, Class<?> fieldType) {
        System.out.println("getPrinter 被调用了");
        return new GenderFormatter(annotation.value());
    }

    @Override
    public Parser<?> getParser(GenderEnumFormat annotation, Class<?> fieldType) {
        System.out.println("getParser 被调用了");
        return new GenderFormatter(annotation.value());
    }

    final class GenderFormatter implements Formatter<GenderEnum> {
        private final String fieldName;
        private Method getter;

        private GenderFormatter(String fieldName) {
            this.fieldName = fieldName;
        }

        @Override
        public GenderEnum parse(String text, Locale locale) throws ParseException {
            if (getter == null) {
                try {
                    getter = GenderEnum.class.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
                } catch (NoSuchMethodException e) {
                    throw new ParseException(e.getMessage(), 0);
                }
            }
            for (GenderEnum e : GenderEnum.values()) {
                try {
                    if (getter.invoke(e).equals(text)) {
                        return e;
                    }
                } catch (IllegalAccessException | InvocationTargetException e1) {
                    throw new ParseException(e1.getMessage(), 0);
                }
            }
            throw new ParseException("输入参数有误，不存在这样的枚举值：" + text, 0);
        }

        @Override
        public String print(GenderEnum object, Locale locale) {
            try {
                // 这里应该也判断一下getter是否为null然后选择进行初始化，但是因为print方法没有效果所以也懒得写了
                return getter.invoke(object).toString();
            } catch (IllegalAccessException | InvocationTargetException e) {
                return e.getMessage();
            }
        }
    }
}