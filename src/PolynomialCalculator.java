// Created by
// COS 150
// Project 3
// Polynomial Calculator

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class PolynomialCalculator {
	private static LinkedList poly1;
	private static LinkedList poly2;
	public static void main(String[] args) {
		String file = args[0];
		System.out.println(file);
		poly1 = new LinkedList();
		poly2 = new LinkedList();
		try {
			Scanner polyFile = new Scanner(new File(file));
			while(polyFile.hasNext()) {
				String line1;
				String line2;
				line1 = " "+polyFile.nextLine();
				line2 = " "+polyFile.nextLine();
				int count = 0;
				int num = 0;
				boolean isNeg = false;
				for(int i = line1.length()-1; i>=0; i--) {
					if(Character.isDigit(line1.charAt(i))) {
						num*=10;
						num+=Character.getNumericValue(line1.charAt(i));
					} else if(line1.charAt(i) == '-') {
						isNeg = true;
					} else if(line1.charAt(i) == ' ') {
						if(isNeg == true) {
							num= num*-1;
						}
						poly1.add(count, num);
						num = 0;
						isNeg = false;
					}
					System.out.println(poly1);
				}
				for(int i = line2.length()-1; i>=0; i--) {
					if(Character.isDigit(line2.charAt(i))) {
						num*=10;
						num+=Character.getNumericValue(line2.charAt(i));
					} else if(line2.charAt(i) == '-') {
						isNeg = true;
					} else if(line2.charAt(i) == ' ') {
						if(isNeg == true) {
							num= num*-1;
						}
						poly2.add(count, num);
						num = 0;
						isNeg = false;
					}
					System.out.println(poly2);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("The file is not found.");
			System.out.println("Please enter a valid file name and try again.");
		}
	}
}
