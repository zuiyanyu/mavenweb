package com.spring_stu.springAnnotations.组件注册.ImportSelector_05.ImportSelector_03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;


class ImportSelectorImpl implements ImportSelector {
    //这里 ApplicationContext 还没准备好， 不能进行注入；applicationContext 为null；
    @Autowired
    ApplicationContext applicationContext ;

    /**必须会
     *  @import的第二种用法
     * 示例： @Import({ImportSelectorImpl.class})
     * @param annotationMetadata 表示当前被@Import注解给标注的所有注解信息
     * @return 返回值为类的全限定名，是要被注册到IOC容器中的 ；
     *         如果返回null会报异常，应该为用空数组替代null；
     *
     */
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        System.out.println("comming into selectImports(AnnotationMetadata annotationMetadata)");
        System.out.println("applicationContext = "+applicationContext); //applicationContext =  null

        //TODO 返回的数组中的全限定类 会被注入到IOC中，在容器中bean名称是该类的全类名
        return new String[]{"com.spring_stu.springAnnotations.组件注册.ImportSelector_05.Bean02"};
    }
}
