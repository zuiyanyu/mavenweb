package com.spring_stu.spring_PropertyEditor.string2enum.domain;

import com.spring_stu.spring_PropertyEditor.string2enum.GenderEnumFormat;
import com.spring_stu.spring_PropertyEditor.string2enum.enums.GenderEnum;
import com.spring_stu.spring_PropertyEditor.string2enum.enums.StatusEnum;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * web请求：http://localhost:8080/test/index?status=0&gender=女
 * 返回：   {"username":null,"password":null,"gender":"FEMALE","status":"OFF"}
 *
 */
@Data
//@Component
public class UserEntity {
    private String username;
    private String password;

    // 加上注解的含义为使用枚举的name字段进行枚举的格式化，可改为id
    //这里 @Value + @GenderEnumFormat ，会在 使用@Component进行依赖注入的时候生效
    @Value("男")
    @GenderEnumFormat("name")
    private GenderEnum gender;

    @Value("1")
    private GenderEnum gender2;

    //这里 @Value ，会在 使用@Component进行依赖注入的时候生效
    @Value("1")
    private StatusEnum status;

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
