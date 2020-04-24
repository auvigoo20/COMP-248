// -------------------------------------------------------
// Assignment 2
// Written by: Auvigoo Ahmed 40128901
// For COMP 248 Section P - Fall 2019
// -------------------------------------------------------
import java.util.Scanner;

public class Assignment2Q2 {

	/*
	 * This program will ask the user to input an integer which corresponds to the
	 * length of the side of a square. It will then calculate the square's
	 * circumference. Afterwards, the program will draw the corresponding square
	 * using the character "X". The character "\" will be displayed along the main
	 * diagonal of the square
	 */

	public static void main(String[] args) {
		// This will display the welcoming message
		System.out.println("-------------------------------------------");
		System.out.println("\t Circumference Calculator");
		System.out.println("-------------------------------------------\n");

		Scanner keyIn = new Scanner(System.in);

		System.out.println("Please enter the length of the side!");

		int lengthSquare = keyIn.nextInt();// The variable "lengthSquare" is the value that the user inputs into the program
		int circumference = 4 * lengthSquare;// This will calculate the circumference of the square

		System.out.println("The Circumference of the Square is " + circumference);

		for (int i = 1; i <= lengthSquare; i++) { // This outer for loop will make each row of the square
			System.out.print("\t\t\t\t\t");
			for (int j = 1; j <= lengthSquare; j++) { // This inner for loop will input the characters in each row of the square

				if (i == j) { // This if statement will allow the program to input "\" along the main diagonal of the square
					System.out.print("\\ ");
				} else { // If the next input is not along the main diagonal, the program will simply input "X"		
					System.out.print("X ");
				}
			}
			System.out.println(""); // This prints out the next line for each row of the square
		}
		
		System.out.println("\nThank you for using the Circumference Calculator!");//This will display the closing message
		keyIn.close();

	}// end of main

}// end of class
