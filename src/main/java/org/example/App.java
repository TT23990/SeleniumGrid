package org.example;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {

//        System.out.println(helloAbs.sayHelloAbs());
        System.out.println( "Hello World!" );

        Bank hdfc=new HDFC();
        hdfc.deposit();
        hdfc.withdraw();
        hdfc.interest();

        Bank icici=new ICICI();
        icici.deposit();
        icici.withdraw();
        icici.interest();
    }
}

abstract class Bank {
    public void deposit(){
        System.out.println("Bank deposit method");
    }
    public void withdraw(){
        System.out.println("Bank withdraw method");
    }
    abstract public void interest();
}

class HDFC extends Bank {
    @Override
    public void interest(){
        System.out.println("HDFC interest method");
    }
}

class ICICI extends Bank {
    @Override
    public void interest(){
        System.out.println("ICICI interest method");
    }
}
