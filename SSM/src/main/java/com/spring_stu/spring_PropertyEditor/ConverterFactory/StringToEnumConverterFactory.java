package com.spring_stu.spring_PropertyEditor.ConverterFactory;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * 当需要集中整个类层次结构的转换逻辑时(例如，从 String 转换为 java.lang.Enum 对象时)，可以实现ConverterFactory，
 * 如以下示例所示：　　
 *
 * package org.springframework.core.convert.converter;
 * public interface ConverterFactory<S, R> {
 *
 *     <T extends R> Converter<S, T> getConverter(Class<T> targetType);
 * }
 * 参数化 S 为您要转换的类型，参数化 R 为定义可以转换为的类的“范围”的基本类型。然后实现 getConverter(Class)，其中 T 是 R 的子类。　　
 */
public class StringToEnumConverterFactory implements ConverterFactory<String, Enum> {
    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
        return  new StringToEnumConverter(targetType);
    }
    private final class StringToEnumConverter<T extends Enum> implements Converter<String, T> {
        private Class<T> enumType;

        public StringToEnumConverter(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public T convert(String source) {
            return (T) Enum.valueOf(this.enumType, source.trim());
        }
    }
}
