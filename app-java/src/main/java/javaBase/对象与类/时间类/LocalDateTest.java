package javaBase.对象与类.时间类;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

/**
 * static LocalDatenow( )
 * 构造一个表示当前日期的对象。
 * • static LocalDate of ( int year , int month , int day )
 * 构造一个表示给定日期的对象。
 * • int getYear( )
 * • int getMonthValue( )
 * • int getDayOfMonth( )
 * 得到当前日期的年、 月和曰。
 * • DayOfWeek getDayOfWeek
 * 得到当前日期是星期几， 作为 DayOfWeek 类的一个实例返回。 调用 getValue 来得到
 * 1 ~ 7 之间的一个数， 表示这是星期几， 1 表示星期一， 7 表示星期日。
 * • LocalDate piusDays( int n )第 4 章 ? 象 与 ? 1 0 3
 * • LocalDate minusDays(int n)
 * 生成当前日期之后或之前 n 天的日期。
 */
public class LocalDateTest {
    public static void main(String[] args) {
        LocalDate date = LocalDate. now();
        LocalDate of = LocalDate.of(2020, 8, 17);
        System.out.println(of); // 2020-08-17
        System.out.println(date);  //2020-08-18

        int year = date.getYear();
        System.out.println(year);//2020

        Month month = date.getMonth();
        int monthValue = date.getMonthValue();
        System.out.println(month); //AUGUST
        System.out.println(monthValue); //8

        int today = date.getDayOfMonth() ;
        System.out.println(today);//18

        //获取周几
        DayOfWeek weekday = date.getDayOfWeek();
        int value = weekday.getValue() ; // 1 = Monday, .. . 7 = Sunday
        System.out.println(value); // 6

        // 将 date 设置为这个月的第一天
        date = date.minusDays(today-1) ; // Set to start of month  当前日期减去多少天  18 - 17 = 1
        today = date.getDayOfMonth() ;
        System.out.println(today);//1

        // 将 date 日期加1天
        date = date.plusDays(1);
        today = date.getDayOfMonth() ;
        System.out.println(today);//2


    }
}
