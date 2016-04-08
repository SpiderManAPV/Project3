// Created by
// COS 150
// Project 3
// Polynomial Calculator

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PolynomialCalculator {
	public static void main(String[] args) {
		String file = args[0];
		System.out.println(file);
		try {
			Scanner polyFile = new Scanner(new File(args[0]));
		} catch (FileNotFoundException e) {
			System.out.println("The file is not found.");
			System.out.println("Please enter a valid file name and try again.");
		}

	}
}
