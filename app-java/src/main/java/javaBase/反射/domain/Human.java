package javaBase.反射.domain;

import javaBase.反射.domain.annotations.NameMeta;

@NameMeta
public class Human {
    private String human_name ;
    private Integer human_age ;

    String human_name_default ;
    public String human_name_public ;
    protected String human_name_protected ;

    public Human(){
        System.out.println("Human无参构造！！！");
    }
    public Human(String human_name){
        System.out.println("Human有参构造！！！");
        this.human_name = human_name ;
    }


    public void method_public_Human(){
        System.out.println("getHumanName");
    }

    public void setHuman_name(String human_name){
        this.human_name = human_name ;
    }


    public void play_public(){
        System.out.println("public无参：play_public");
    }
    public void play_public_2(String info){
        System.out.println("public有参：play_public2："+info);
    }
    protected void play_protected(){
        System.out.println("protected无参：play_protected");
    }
    protected void play_protected_2(String info){
        System.out.println("protected有参：play_protected_2："+info);
    }
    void play_default(){
        System.out.println("默认修饰符无参：play_default");
    }
    void play_default_2(String info){//默认修饰符   带参数
        System.out.println("默认修饰符有参：play_default_2:"+info);
    }
    private void play_private(){
        System.out.println("private无参：play_private");
    }
    private void play_private_2(String info){
        System.out.println("private有参：play_private_2："+info);
    }

}
