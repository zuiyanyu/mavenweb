package com.spring_stu.springAnnotations.组件注册.Conditional_04;

public class WindowsService implements ListService {
    @Override
    public String showListCmd() {
        return "我是 window 环境";
    }
}
