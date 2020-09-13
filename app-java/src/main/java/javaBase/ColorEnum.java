package javaBase;

import org.apache.commons.lang.StringUtils;

public enum ColorEnum {
    RED(1,"红色"),
    BlUE(2,"绿色"),
    BLACK;

     ColorEnum() {};
     ColorEnum(int code,String name) {
        this.code = code ;
        this.name = name ;
    };
    String name ;
    int code ;

    @Override
    public String toString() {
        return "["+StringUtils.leftPad(String.valueOf(code),8,"0") +":"+name+"]";
    }
}
