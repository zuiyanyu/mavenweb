package springbootstu.domain;

import lombok.Data;
import org.springframework.stereotype.Component;
import springbootstu.annotations.GenderEnumFormat;
import springbootstu.domain.enums.GenderEnum;
import springbootstu.domain.enums.StatusEnum;

@Data
@Component
public class UserEntity {
    private String username;
    private String password;

    // 加上注解的含义为使用枚举的name字段进行枚举的格式化，可改为id
    //http://localhost:8082/userEntityController/index?status=1&gender=男
    //{"username":null,"password":null,"gender":"MALE","status":"ON"}
    @GenderEnumFormat("name")
    private GenderEnum gender;

    //http://localhost:8082/userEntityController/index?status=1
    private StatusEnum status;
}
