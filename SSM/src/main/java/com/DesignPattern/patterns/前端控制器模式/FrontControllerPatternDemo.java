package com.DesignPattern.patterns.前端控制器模式;

import com.DesignPattern.patterns.前端控制器模式.controller.FrontController;

//FrontController 来演示前端控制器设计模式
public class FrontControllerPatternDemo {
    public static void main(String[] args) {
        FrontController frontController = new FrontController();
        frontController.dispatchRequest("HOME");
        frontController.dispatchRequest("STUDENT");
    }
}