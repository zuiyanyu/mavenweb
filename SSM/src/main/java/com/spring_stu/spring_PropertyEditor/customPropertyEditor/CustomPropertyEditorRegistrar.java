package com.spring_stu.spring_PropertyEditor.customPropertyEditor;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.stereotype.Component;

@Component
public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {
    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        //TODO PropertyEditorRegistrar 负责查找 要使用的属性编辑器。
        //TODO PropertyEditorRegistry  负责保存 要使用的属性编辑器。

        registry.registerCustomEditor(Car.class, new CustomCarEditor());

    }
}
