package com.zk;

public class TestImplement implements TestInterface {

    public static void main(String[] args) {

        String str = "a.b.c.d..k,l,";
        String[] strArray = str.split("\\.|,");
        System.out.println(strArray.length);
        for (String s : strArray) {
            System.out.println(s);
        }

        String[] strArray1 = str.split("\\.|," , -1);
        System.out.println(strArray1.length);
        for (String s : strArray1) {
            System.out.println(s);
        }

    }
}
