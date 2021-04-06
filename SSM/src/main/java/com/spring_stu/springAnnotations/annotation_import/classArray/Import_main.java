package com.spring_stu.springAnnotations.annotation_import.classArray;


import com.spring_stu.domain.Address;
import com.spring_stu.springAnnotations.组件注册.ImportSelector_05.Bean01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
/**
 * 1. 先进行 Import中的类进行注入到IOC中
 * 2. 然后进行 @Component 注解的类的注入。
 * 3. @Component 注解的类，当构造方法只有一个有参构造时候，参数会从IOC容器中自动获取，进行注入。
 */
@Import(value = {Address.class,Bean01.class})
//@Component
public class Import_main {
    private Address address ;

    @Autowired
    private Address address2 ;

    /**
     * TODO 当只有一个有参构造方法的时候，参数会从容器中获取。
     * @param address
     */
    public Import_main(Address address){
        this.address = address ;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress2() {
        return address2;
    }

    public void setAddress2(Address address2) {
        this.address2 = address2;
    }

    @Override
    public String toString() {
        return "Import_main{" +
                "address=" + address +
                ", address2=" + address2 +
                '}';
    }
}
