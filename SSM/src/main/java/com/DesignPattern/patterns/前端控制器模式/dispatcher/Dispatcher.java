package com.DesignPattern.patterns.前端控制器模式.dispatcher;

import com.DesignPattern.patterns.前端控制器模式.view.HomeView;
import com.DesignPattern.patterns.前端控制器模式.view.StudentView;

public class Dispatcher {
    private StudentView studentView;
    private HomeView homeView;
    public Dispatcher(){
        studentView = new StudentView();
        homeView = new HomeView();
    }

    public void dispatch(String request){
        if(request.equalsIgnoreCase("STUDENT")){
            studentView.show();
        }else{
            homeView.show();
        }
    }
}
