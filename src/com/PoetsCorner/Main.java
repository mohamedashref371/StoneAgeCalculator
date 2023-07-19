package com.PoetsCorner;

import java.util.Scanner;
public class Main
{   
	public static void main(String[] args)
	{
		System.out.println("Stone Age Calculator!");
		Scanner x = new Scanner(System.in);
		System.out.println("Enter 'exit' to close the program.");
		System.out.println("Enter the calculation using the numbers, ., +, -, *, /");
		String y=x.nextLine();
		while(!y.equals("exit")){
			System.out.println("= "+ Calculator.calculate(y) + "\n------------------\n");
			y=x.nextLine();
		}
	}
} // I tried making a rudimentary calculator.
