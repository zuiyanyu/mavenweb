package com.spring_stu.springAnnotations.annotation_import;

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
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        System.out.println("comming into selectImports(AnnotationMetadata annotationMetadata)");

        System.out.println("applicationContext = "+applicationContext); //applicationContext =  null

        //***import注解的类******** annotationMetadata= com.spring_stu.springAnnotations.annotation_import.TestDemo
        System.out.println("***import注解的类***** annotationMetadata= "+annotationMetadata.getClassName());

        //返回的数组中的全限定类 会被注入到IOC中，在容器中bean名称是该类的全类名
        return new String[]{"com.spring_stu.springAnnotations.annotation_import.TestDemo2"};
    }
}
