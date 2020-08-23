package javaVMJDK7.虚拟机执行子系统.动态类型;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;
class Test{
    class GrandFather{
        void thinking( ) {
            System.out.println( "i am grandfather") ;
        }}
    class Father extends GrandFather{
        void thinking( ) {
            System.out.println( "i am father") ;
        }}
    class Son extends Father{
        void thinking( ) {
            try{
                MethodType mt=MethodType.methodType( void.class) ;
                MethodHandle mh=lookup( ) .findSpecial( GrandFather.class, "thinking", mt,getClass( ) ) ;
                mh.invoke( this) ;
            }catch( Throwable e) {
            }}}
    public static void main( String[]args) {
( new Test( ) .new Son( ) ) .thinking( ) ;
    }}
