// -------------------------------------------------------
// Assignment 2
// Written by: Auvigoo Ahmed 
// For COMP 248 Section P - Fall 2019
// -------------------------------------------------------

import java.util.Scanner;
public class Assignment2Q1If {
	
/*This program will ask the user to input a number between 1-12 and it will output
 the corresponding name of the month and the season according to the Persian Calendar.
 This program is written using if-else statements.*/

	public static void main(String[] args) {
	Scanner keyIn= new Scanner (System.in);
	System.out.println("Welcome to Persian Calendar Program!\n");//This will display the welcoming message
	System.out.print("Please enter the Month as a number 1-12: ");
	int inputMonth = keyIn.nextInt();// The variable "inputMonth" is the number that the user inputs into the program.
	
	//The following nested if statements contain the months in Spring.
	if (inputMonth == 1 || inputMonth == 2 || inputMonth ==3) {
		if (inputMonth==1) {
			System.out.print("We are in Farvardin, ");
		}
		else if (inputMonth == 2) {
			System.out.print("We are in Ordibehesht, ");
		}
		else if (inputMonth == 3) {
			System.out.print("We are in Khordad, ");
		}
	System.out.println("Happy Spring");
	}
	//The following nested if statements contain the months in Summer.
	else if (inputMonth==4 ||inputMonth==5 || inputMonth==6) {
		if (inputMonth==4) {
			System.out.print("We are in Tir, ");
		}
		else if (inputMonth==5) {
			System.out.print("We are in Mordad, ");
		}
		else if (inputMonth==6) {
			System.out.print("We are in Shahrivar, ");
		}
	System.out.print("Have Fun in summer");
	}
	//The following nested if statements contain the months in Fall.
	else if (inputMonth==7 || inputMonth==8 || inputMonth==9) {
		if (inputMonth==7) {
			System.out.print("We are in Mehr, ");
		}
		else if (inputMonth==8) {
			System.out.print("We are in Aban, ");
		}
		else if (inputMonth==9) {
			System.out.print("We are in Azar, ");
		}
	System.out.print("Ready for Fall");	
	}
	//The following nested if statements contain the months in Winter.
	else if (inputMonth==10 || inputMonth==11 || inputMonth==12) {
		if (inputMonth==10) {
			System.out.print("We are in Dey, ");
		}
		else if(inputMonth==11) {
			System.out.print("We are in Bahman, ");
		}
		else if(inputMonth==12) {
			System.out.print("We are in Esfand, ");
		}
	System.out.print("Keep warm Yourself in Winter");
	}
	//This if statement will tell the user that their input is invalid if they input a number that is not in the range of 1-12.
	else if (inputMonth>12 || inputMonth<1) {
		System.out.print("This number is not a valid month");
	}
	
	System.out.println("\n\nThank you for using Persian Calendar Program!");//This will display the closing message
	keyIn.close();
	
	}//end of main

}//end of class
