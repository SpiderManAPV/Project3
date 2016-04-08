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
		try {
			Scanner polyFile = new Scanner(new File(file));
			while(polyFile.hasNext()) {
				String line = polyFile.nextLine();
				for(int i = 0; i < line.length(); i++) {

				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("The file is not found.");
			System.out.println("Please enter a valid file name and try again.");
		}
	}
}
