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
		//System.out.println(file);
		poly1 = new LinkedList();
		poly2 = new LinkedList();
		readFile(file);
		//System.out.println(poly1);
		//System.out.println(poly2);
		System.out.println("Polynomial 1: "+printPoly(poly1));
		System.out.println("Polynomial 2: "+printPoly(poly2));
		System.out.println("Sum: "+printPoly(addition(poly1, poly2)));
		System.out.println("Difference: "+printPoly(subtraction(poly1, poly2)));
		System.out.println("Product: "+printPoly(multiply(poly1,poly2)));
	}
	private static void readFile(String fileName) {
		try {
			Scanner polyFile = new Scanner(new File(fileName));
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
						if(isNeg) {
							num= num*-1;
						}
						poly1.add(count, num);
						num = 0;
						isNeg = false;
					}
					//System.out.println(poly1);
				}
				if(poly1.size()%2 != 0) {
					poly1.add(poly1.size()+1, 0);
				}
				for(int i = line2.length()-1; i>=0; i--) {
					if(Character.isDigit(line2.charAt(i))) {
						num*=10;
						num+=Character.getNumericValue(line2.charAt(i));
					} else if(line2.charAt(i) == '-') {
						isNeg = true;
					} else if(line2.charAt(i) == ' ') {
						if(isNeg) {
							num= num*-1;
						}
						poly2.add(count, num);
						num = 0;
						isNeg = false;
					}
					//System.out.println(poly2);
				}
				if(poly2.size()%2 != 0) {
					poly2.add(poly2.size()+1, 1);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("The file is not found.");
			System.out.println("Please enter a valid file name and try again.");
		}
	}
	private static String printPoly(LinkedList poly) {
		StringBuilder printer = new StringBuilder();
		for(int i = 0; i < poly.size(); i++) {
			//System.out.print(poly.get(i)+"x^");
			printer.append(poly.get(i)+"x^");
			i++;
			if(i < poly.size()) {
				printer.append(poly.get(i));
			}
			if(i+1 < poly.size()) {
				if(Integer.parseInt(poly.get(i+1).toString()) > 0) {
					printer.append("+");
				} else {
					printer.append("");
				}
			}
		}
		return printer.toString();
	}
	private static LinkedList addition(LinkedList list1, LinkedList list2) {
		LinkedList sum = new LinkedList();
		int[] exp1 = new int[list1.size()/2];
		int[] coef1 = new int[list1.size()/2];
		int[] exp2 = new int[list2.size()/2];
		int[] coef2 = new int[list2.size()/2];
		//<editor-fold desc="For loops to fill arrays">
		for(int i = 0; i < coef1.length; i++) {
			coef1[i] = Integer.parseInt(list1.get(i*2).toString());
		}
		for(int i = 0; i < exp1.length; i++) {
			exp1[i] = Integer.parseInt(list1.get(i*2+1).toString());
		}
		for(int i = 0; i < coef2.length; i++) {
			coef2[i] = Integer.parseInt(list2.get(i*2).toString());
		}
		for(int i = 0; i < exp2.length; i++) {
			exp2[i] = Integer.parseInt(list2.get(i*2+1).toString());
		}
		//</editor-fold>
		int index = 0;
		int count = 0;
		for(int i = 0; i < exp1.length; i++) {
			//System.out.println(i);
			//System.out.println("i: "+index);
			//System.out.println("l: "+exp2.length);
			//System.out.println(printPoly(sum));
			if(exp1[i] == exp2[index]) {
				sum.add((coef1[i]+coef2[index]));
				sum.add((exp1[i]));
				index++;
				if(index == exp2.length) {
					//System.out.println("y");
					i++;
					count = i;
					i = exp1.length;
				}
			} else if(exp1[i] > exp2[index]) {
				sum.add(coef1[i]);
				sum.add(exp1[i]);
			} else if(exp1[i] < exp2[index]) {
				sum.add(coef2[index]);
				sum.add(exp2[index]);
				i--;
				index++;
				//System.out.println("check");
				if(index == exp2.length) {
					//System.out.println("y");
					i++;
					count = i;
					i = exp1.length;
				}
			}
		}
		while(index < exp2.length) {
			sum.add(coef2[index]);
			sum.add(exp2[index]);
			index++;
		}
		while(count < exp1.length) {
			//System.out.println(count);
			sum.add(coef1[count]);
			sum.add(exp1[count]);
			count++;
		}
		//System.out.println("x: "+printPoly(sum));
		return sum;
	}
	private static LinkedList subtraction (LinkedList list1, LinkedList list2) {
		LinkedList dif = new LinkedList();
		int[] exp1 = new int[list1.size()/2];
		int[] coef1 = new int[list1.size()/2];
		int[] exp2 = new int[list2.size()/2];
		int[] coef2 = new int[list2.size()/2];
		//<editor-fold desc="For loops to fill arrays">
		for(int i = 0; i < coef1.length; i++) {
			coef1[i] = Integer.parseInt(list1.get(i*2).toString());
		}
		for(int i = 0; i < exp1.length; i++) {
			exp1[i] = Integer.parseInt(list1.get(i*2+1).toString());
		}
		for(int i = 0; i < coef2.length; i++) {
			coef2[i] = Integer.parseInt(list2.get(i*2).toString());
		}
		for(int i = 0; i < exp2.length; i++) {
			exp2[i] = Integer.parseInt(list2.get(i*2+1).toString());
		}
		//</editor-fold>
		int index = 0;
		for(int i = 0; i < exp1.length; i++) {
			if(exp1[i] == exp2[index]) {
				dif.add((coef1[i]-coef2[index]));
				dif.add((exp1[i]));
				index++;
			} else if(exp1[i] > exp2[index]) {
				dif.add(coef1[i]);
				dif.add(exp1[i]);
			}
			else if(exp1[i] < exp2[index]) {
				dif.add(coef2[index]*-1);
				dif.add(exp2[index]);
				i--;
				index++;
			}
		}
		while(index < 3) {
			dif.add(coef2[index]*-1);
			dif.add(exp2[index]);
			index++;
		}
		return dif;
	}
	private static LinkedList multiply(LinkedList list1, LinkedList list2) {
		LinkedList product = new LinkedList();
		int[] exp1 = new int[list1.size()/2];
		int[] coef1 = new int[list1.size()/2];
		int[] exp2 = new int[list2.size()/2];
		int[] coef2 = new int[list2.size()/2];
		//<editor-fold desc="For loops to fill arrays">
		for(int i = 0; i < coef1.length; i++) {
			coef1[i] = Integer.parseInt(list1.get(i*2).toString());
		}
		for(int i = 0; i < exp1.length; i++) {
			exp1[i] = Integer.parseInt(list1.get(i*2+1).toString());
		}
		for(int i = 0; i < coef2.length; i++) {
			coef2[i] = Integer.parseInt(list2.get(i*2).toString());
		}
		for(int i = 0; i < exp2.length; i++) {
			exp2[i] = Integer.parseInt(list2.get(i*2+1).toString());
		}
		//</editor-fold>
		for(int i = 0; i < exp1.length; i++) {
			LinkedList temp = new LinkedList();
			LinkedList temp2 = new LinkedList();
			for(int j = 0; j < exp2.length; j++) {
				temp.add(coef1[i]*coef2[j]);
				temp.add(exp1[i]+exp2[j]);
				//product.add(coef1[i]*coef2[j]);
				//product.add(exp1[i]+exp2[j]);
			}

			if(i == 0) {
				for(int j = 0; j < temp.size(); j++) {
					product.add(temp.get(j));
				}
				//System.out.println("P: "+printPoly(product));
			} else if(!product.equals(temp)) {
				//System.out.println("Temp: "+printPoly(temp));
				//System.out.println("Prod: "+printPoly(product));
				product = (addition(temp, product));
				//product = temp2;
				//temp2.clear();
			}
			//System.out.println("P: "+printPoly(temp2));
		}
		return product;
	}
}