package com.baizhi;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;

public class Test {
    @org.junit.jupiter.api.Test
    public void test(){
        for (int i = 1; i <=9 ; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j*i+"="+j+"*"+i+"\t");

            }
            System.out.println("");
            System.out.println("111111111");
            System.out.println("111111111");
            System.out.println("111111111");
            System.out.println("111111111");
            System.out.println("111111111"); System.out.println("111111111");



        }
    }
    @org.junit.jupiter.api.Test
    public void  test2(){
        Map map=new HashMap();
        String a="asdaskjdgajikASUKEDRG";
        char[] chars = a.toCharArray();
        for (int i = 0; i <a.length(); i++) {

        }
    }
}
