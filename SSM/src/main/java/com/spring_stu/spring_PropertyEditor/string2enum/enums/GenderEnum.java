package com.spring_stu.spring_PropertyEditor.string2enum.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
//@ToString
public enum GenderEnum implements EnumInterface {

    MALE(0, "男"),
    FEMALE(1, "女") ;

    @JsonProperty("id") // 设置输出后的名称
    private Integer id;
    @JsonProperty("性别")
    private String name;

}
