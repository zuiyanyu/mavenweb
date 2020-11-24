package com.spring_stu.springAnnotations.Conditional;

public class LinuxService implements ListService {
    @Override
    public String showListCmd() {
        return "我是 linux 环境";
    }
}
