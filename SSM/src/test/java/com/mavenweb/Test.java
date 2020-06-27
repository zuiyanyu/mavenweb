package com.mavenweb;

public class Test {
    public static void main(String[] args) {
        int num =9928 ;
        String s = switchColIndexToName(num);
        System.out.println(s);
        String s2 = switchColIndexToName2(num);
        System.out.println(s2);
    }

    public static String switchColIndexToName(int columnIndex){
        String[] letterArray = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        StringBuilder colHeader = new StringBuilder();
        int a ;
        int b ;
        while(columnIndex > 0){
            a = (columnIndex-1) / 26 ;
            b = (columnIndex-1) % 26 ;
            columnIndex = a ;
            colHeader.append(letterArray[b]);
        }
//    while(columnIndex > 0){
//        a = columnIndex / 26 ;
//        b = columnIndex % 26 ;
//        columnIndex = a ;
//        colHeader.append(letterArray[b-1]);
//    }
//
        return  colHeader.reverse().toString() ;
    }
    public static String switchColIndexToName2(int columnIndex){
        String[] letterArray = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        StringBuilder colHeader = new StringBuilder();
        int a ;
        int b ;

        while(columnIndex > 0){
            a = columnIndex / 26 ;
            b = columnIndex % 26 ;
            columnIndex = a ;
            colHeader.append(letterArray[b-1]);
        }
        return  colHeader.reverse().toString() ;
    }
}
