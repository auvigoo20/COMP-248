// -------------------------------------------------------
// Assignment 2
// Written by: Auvigoo Ahmed
// For COMP 248 Section P - Fall 2019
// -------------------------------------------------------

import java.util.Scanner;
public class Assignment2Q1Switch {

/*This program will ask the user to input a number between 1-12 and it will output
the corresponding name of the month and the season according to the Persian Calendar.
This program is written using a switch statement.*/

	public static void main(String[] args) {
	Scanner keyIn= new Scanner (System.in);
	System.out.println("Welcome to Persian Calendar Program!\n");//This will display the welcoming message
	System.out.print("Please enter the Month as a number 1-12: ");
	int inputMonth= keyIn.nextInt();// The variable "inputMonth" is the number that the user inputs into the program.
		
	switch(inputMonth) {
		case 1:
			System.out.println("We are in Farvardin, Happy Spring");
			break;
		case 2:
			System.out.println("We are in Ordibehesht, Happy Spring");
			break;
		case 3:
			System.out.println("We are in Khordad, Happy Spring");
			break;
		case 4:
			System.out.println("We are in Tir, Have Fun in summer");
			break;
		case 5:
			System.out.println("We are in Mordad, Have Fun in summer");
			break;
		case 6:
			System.out.println("We are in Shahrivar, Have Fun in summer");
			break;
		case 7:
			System.out.println("We are in Mehr, Ready For Fall");
			break;
		case 8:
			System.out.println("We are in Aban, Ready For Fall");
			break;
		case 9:
			System.out.println("We are in Azar, Ready For Fall");
			break;
		case 10:
			System.out.println("We are in Dey, Keep warm Yourself in Winter");
			break;
		case 11:
			System.out.println("We are in Bahman, Keep warm Yourself in Winter");
			break;
		case 12:
			System.out.println("We are in Esfand, Keep warm Yourself in Winter");
			break;
		default://The default case occurs when the user does not input a value in the range of 1-12.
			System.out.println("This number is not a valid month");
	}
	System.out.println("\n\nThank you for using Persian Calendar Program!");//This will display the closing message
	keyIn.close();
		
	}//end of main

}//end of class
