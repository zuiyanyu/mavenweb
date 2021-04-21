package springbootstu.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import springbootstu.domain.intefaces.EnumInterface;

/**
 * TODO 使用 @JsonFormat(shape = JsonFormat.Shape.OBJECT)  解决@ResponseBody返回的Enum只是实例名的问题。
 * 使用前：{"status":"OFF" }  ， 使用后 ："status":{"id":0,"状态名":"停用"}
 *
 * TODO @JsonProperty("id") 给转成json的字段重命名
 *
 */
@Getter
@AllArgsConstructor
//@ToString
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusEnum implements EnumInterface {
    ON(1, "启用"),
    OFF(0, "停用");

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("状态名")
    private String name;

}
