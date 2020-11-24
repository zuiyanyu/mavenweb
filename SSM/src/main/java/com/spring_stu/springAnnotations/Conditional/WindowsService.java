package com.spring_stu.springAnnotations.Conditional;

public class WindowsService implements ListService {
    @Override
    public String showListCmd() {
        return "我是 window 环境";
    }
}
