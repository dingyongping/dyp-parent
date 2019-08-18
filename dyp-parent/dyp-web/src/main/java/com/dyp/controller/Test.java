package com.dyp.controller;

public class Test {
    public static void main(String[] args) {
        StringBuilder sbr=new StringBuilder();
        StringBuilder sbr1=new StringBuilder();
        StringBuilder sbr2=new StringBuilder();
        String tableName="test_name";
        sbr.append("INSERT INTO ").append(tableName).append(" (");

         sbr1.append("Field02").append(",");
         String str01=sbr1.toString().substring(0,sbr1.toString().length()-1);
        sbr.append(str01.toString());
        sbr.append(") VALUES ( '");

        sbr2.append("Field02").append("',");
        String str02=sbr2.toString().substring(0,sbr2.toString().length()-1);
        sbr.append(str02.toString());
        sbr.append(" );");

        System.out.println(sbr.toString());
    }
}
