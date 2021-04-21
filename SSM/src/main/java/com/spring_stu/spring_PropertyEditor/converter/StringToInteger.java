package com.spring_stu.spring_PropertyEditor.converter;


import org.springframework.core.convert.converter.Converter;

/**
 * converter SPI
 *  如以下接口定义所示，用于实现类型转换逻辑的 SPI 非常简单且具有强类型。
 *
 * package org.springframework.core.convert.converter;
 *
 * public interface Converter<S, T> {
 *
 *     T convert(S source);
 * }
 * 要创建自己的转换器，请实现Converter界面，并将S作为要转换的类型，并将T参数化为要转换的类型。如果需要将S的集合或数组转换为T的数组或集合，您也可以透明地应用此类转换器，前提是已注册了委派的数组或集合转换器(默认情况下DefaultConversionService这样做)。　　
 *
 *  对于每次convert(S)的调用，保证源参数不为 null。如果转换失败，您的Converter可能会引发任何未经检查的异常。具体来说，它应该抛出IllegalArgumentException以报告无效的源值。注意确保Converter实现是线程安全的。
 *
 *  为方便起见，在core.convert.support包中提供了几种转换器实现。这些包括从字符串到数字以及其他常见类型的转换器。下面的清单显示了StringToInteger类，这是一个典型的Converter实现：
 */
public class StringToInteger implements Converter<String, Integer> {

    //将string字符串转换为 Integer
    @Override
    public Integer convert(String source) {
        return Integer.valueOf(source);
    }
}