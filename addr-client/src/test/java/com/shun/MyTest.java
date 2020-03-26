package com.shun;

public class MyTest {
    public int a;
    public String b;
    public void testClass(MyTest myTest){
        myTest.a = a+1;
        myTest.b = b+a;
    }
    public void testOut(){
        System.out.println(a);
        System.out.println(b);
    }

    public static void main(String[] args) {
        MyTest m = new MyTest();
        m.a = 7;
        m.b = "haha";
        m.testClass(m);
        m.testOut();
    }
}
