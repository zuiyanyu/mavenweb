package com.spring_stu.springAnnotations.annotation_import;


import com.spring_stu.domain.Address;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Import(value = {Address.class,TestDemo05.class})
@Component
public class Import_Type1 {
    private Address address ;

    /**
     * 当只有一个有参构造方法的时候，参数会从容器中获取。
     * @param address
     */
    public Import_Type1(Address address){
        this.address = address ;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ImportAnnotation_Type1{" +
                "address=" + address +
                '}';
    }
}
