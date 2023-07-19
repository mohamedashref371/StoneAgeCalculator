package com.PoetsCorner;
import java.util.Scanner;
import static com.PoetsCorner.Calculator.*;

public class Numerical {

    public static void main(String[] args) {

        System.out.println("Stone Age Calculator!");
        String a="0", b="0", c="0", z="0";
	    Scanner x = new Scanner(System.in);
        System.out.println("Enter 'exit' to close the program.");
	    System.out.println("Enter the function using the numbers, a, b, c, +, -, * and /");
        String y = x.nextLine();
        while(!y.equals("exit")){
            if (SubstringPlace(y,"a",0)>=0||SubstringPlace(y,"b",0)>=0||SubstringPlace(y,"c",0)>=0){fun(y, a, b, c, z);}
            else {System.out.println(calculate(y));}
            y = x.nextLine();
        }
    }
    
    public static void fun(String y, String a, String b, String c, String z) {
        Scanner x = new Scanner(System.in);
        String d;
        if (z.equals("1")) {y = x.nextLine();}
        else if (z.equals("0")) {
            if (SubstringPlace(y,"a",0)>=0) {
                System.out.println("Enter the value of 'a' using the numbers, a, b, c, +, -, * and /");
                d = x.nextLine();
                if (d.equals("")||d.equals("a")) {}
                else if (d.equals("b")) {a=b;}
                else if (d.equals("c")) {a=c;}
                else {a=calculate(d.replace("a", a).replace("b", b).replace("c", c));}
            }
            if (SubstringPlace(y,"b",0)>=0) {
                System.out.println("Enter the value of 'b' using the numbers, a, b, c, +, -, * and /");
                d = x.nextLine();
                if (d.equals("")||d.equals("b")) {}
                else if (d.equals("a")) {b=a;}
                else if (d.equals("c")) {b=c;}
                else {b=calculate(d.replace("a", a).replace("b", b).replace("c", c));}
            }
            if (SubstringPlace(y,"c",0)>=0) {
                System.out.println("Enter the value of 'c' using the numbers, a, b, c, +, -, * and /");
                d = x.nextLine();
                if (d.equals("")||d.equals("c")) {}
                else if (d.equals("a")) {c=a;}
                else if (d.equals("b")) {c=b;}
                else {c=calculate(d.replace("a", a).replace("b", b).replace("c", c));}
            }
        }
        System.out.println(calculate(y.replace("a", a).replace("b", b).replace("c", c))+'\n');
        System.out.println("Enter '1' to keep the equation, '2' to retain a,b,c values, or eny char to keep nothing.");
        z = x.nextLine();
        if (z.equals("0")||z.equals("1")){fun(y, a, b, c, z);}
    }
}
