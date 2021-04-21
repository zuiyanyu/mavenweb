package springbootstu.domain.enums;

public class EnumTest {
    public static void main(String[] args) {
        Class<StatusEnum> targetType = StatusEnum.class ;
        StatusEnum[] enumConstants = targetType.getEnumConstants();
        for (StatusEnum enumConstant : enumConstants) {
            System.out.println(enumConstant.getId());
        }
    }
}
