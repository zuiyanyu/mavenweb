package springbootstu.converters;

import org.springframework.core.convert.converter.Converter;
import springbootstu.domain.enums.StatusEnum;

/**
 * String to StatusEnum 的转换器
 */
public class String2StatusEnumConverter implements Converter<String, StatusEnum> {

    @Override
    public StatusEnum convert(String s) {
        // 注意，这里是通过id匹配
        for (StatusEnum e : StatusEnum.values()) {
            if (e.getId().equals(Integer.valueOf(s))) {
                return e;
            }
        }
        return null;
    }
}
