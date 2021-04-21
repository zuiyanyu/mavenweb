package com.spring_stu.spring_PropertyEditor.string2enum.converter;

import com.spring_stu.spring_PropertyEditor.string2enum.enums.EnumInterface;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * String to EnumInterface 的转换器工厂
 */
public class String2EnumConverterFactory implements ConverterFactory<String, EnumInterface> {

    @Override
    public <T extends EnumInterface> Converter<String, T> getConverter(Class<T> targetType) {
        return new String2Enum<>(targetType);
    }

    /**
     * 转换器
     */
    private class String2Enum<T extends EnumInterface> implements Converter<String, T> {

        private final Class<T> targetType;

        private String2Enum(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(String source) {
            for (T enumConstant : targetType.getEnumConstants()) {
                if (enumConstant.getId().toString().equals(source)) {
                    return enumConstant;
                }
            }
            return null;
        }
    }
}