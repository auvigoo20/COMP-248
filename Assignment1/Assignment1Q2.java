// -------------------------------------------------------
// Assignment 1
// Written by: Auvigoo Ahmed 40128901
// For COMP 248 Section P - Fall 2019
// -------------------------------------------------------


import java.util.Scanner;
public class Assignment1Q2 {
	
//This program reads a 5 digit number that represents a time in seconds and converts it into its corresponding hours, minutes and seconds.
//If the converted time is in the range of 0 to 24 hours, the program will display it in a time format as hh:mm:ss.
//If not, the program will swap the first and last digit of the initial 5 digit number.
//The program will then convert the swapped number into its corresponding hours, minutes and seconds.

	public static void main(String[] args) {

		//This will display the welcoming message
		System.out.println("*****************************************");
		System.out.println("   Welcome to Time Converter Program!");
		System.out.println("*****************************************\n");


		//This will ask the user to input the number of seconds into the program
		Scanner keyIn = new Scanner(System.in);
		int inputSeconds;
		//The variable "inputSeconds" is the number of seconds that the user inputs into the program
		System.out.print("\nPlease enter the seconds which will be converted: ");
		inputSeconds = keyIn.nextInt();

		//This will convert the number of seconds that the user inputs into its corresponding hours, minutes and seconds
		final int MINS_IN_HOUR = 60;     //There are 60 minutes in one hour OR 60 seconds in one minute
		final int SECONDS_IN_HOUR = 3600;   //There are 3600 seconds in one hour
		int numOfHours, numOfMins, numOfSec;
		numOfHours = (inputSeconds / SECONDS_IN_HOUR);
		numOfMins = ((inputSeconds - (numOfHours * SECONDS_IN_HOUR))/ MINS_IN_HOUR);
		numOfSec = (inputSeconds - (numOfHours * SECONDS_IN_HOUR) - (numOfMins * MINS_IN_HOUR));
		System.out.println("\nThe correspond hours, minutes, seconds is "+ numOfHours + " hrs, " + numOfMins + " mins, " + numOfSec + " secs. \n");

		
		//This will convert the correspond hours, minutes, seconds into its valid time if it does not exceed 24 hours
		if (inputSeconds <= 86400) { //The value "86400" is the number of seconds in 24 hours
			
			/*The following nested if statements represent each possibility of having "00" in the valid time
			The possible combinations in the order that is written are:
			00:mm:ss, 00:00:ss, 00:mm:00, hh:00:00, hh:mm:00, hh:00:ss, 00:00:00, hh:mm:ss.*/

			if (numOfHours==0 && numOfMins != 0 && numOfSec != 0) {
				System.out.println("The valid time is: " + "00" + ":" + numOfMins + ":" + numOfSec + ".\n");
			}
			else if (numOfHours==0 && numOfMins==0 && numOfSec != 0) {
				System.out.println("The valid time is: " + "00" + ":" + "00" + ":" + numOfSec + ".\n");
			}
			else if (numOfHours==0 && numOfMins != 0 && numOfSec==0) {
				System.out.println("The valid time is: " + "00" + ":" + numOfMins + ":" + "00" + ".\n");
			}
			else if (numOfHours != 0 && numOfMins==0 && numOfSec==0){
				System.out.println("The valid time is: " + numOfHours + ":" + "00" + ":" + "00" + ".\n");
			}
			else if (numOfHours != 0  && numOfMins != 0 && numOfSec==0) {
				System.out.println("The valid time is: " + numOfHours + ":" + numOfMins + ":" + "00" + ".\n");
			}
			else if (numOfHours != 0 && numOfMins==0 && numOfSec != 0) {
				System.out.println("The valid time is: " + numOfHours + ":" +"00"+":" + numOfSec + ".\n");
			}
			else if (numOfHours==0 && numOfMins==0 && numOfSec==0) {
				System.out.println("The valid time is: 00:00:00\n");
			}
			else
				System.out.println("The valid time is: " + numOfHours + ":" + numOfMins + ":" + numOfSec + ".\n");}

		

		//This will swap the first and last digits when there is no corresponding valid time (when the numOfSeconds exceeds 86400)
		else {
			System.out.println("There is no valid time of your input.\n");

			/*This will store the first digit of "inputSeconds" into a new variable by dividing its value by 10000 
			since the input should always be a 5-digit number*/
			int firstDig = inputSeconds/10000; //first digit

			//This will store the last digit of "inputSeconds" into a new variable by computing its modulus 10
			int lastDig = inputSeconds % 10; //last digit

			//This will store the three middle digits into a new variable so that they are left unchanged
			int middleDig = (inputSeconds - (10000 * firstDig)- lastDig)/10; //three middle digits

			//This will combine the stored digits into a value in such a way that the first and last digits of "inputSeconds" are swapped
			int newValueOfSec = (lastDig * 10000)+ (middleDig * 10) + firstDig; //new value of "inputSeconds" with first and last digits swapped
			System.out.println("The swapped sequence of the input time is:" + newValueOfSec +"\n");

			//This will convert the swapped number of seconds into its corresponding hours, minutes and seconds
			int swappedNumOfHours, swappedNumOfMins, swappedNumOfSec;
			swappedNumOfHours = (newValueOfSec / SECONDS_IN_HOUR);
			swappedNumOfMins = ((newValueOfSec - (swappedNumOfHours * SECONDS_IN_HOUR))/ MINS_IN_HOUR);
			swappedNumOfSec = (newValueOfSec - (swappedNumOfHours * SECONDS_IN_HOUR) - (swappedNumOfMins * MINS_IN_HOUR));
			System.out.println("The correspond hours, minutes, seconds is "+ swappedNumOfHours + " hrs, " + swappedNumOfMins + " mins, " + swappedNumOfSec + " secs. \n");
			}
		 
		System.out.println("Thank you for using the Time Converter Program!");//This prints out the closing message
		keyIn.close();

	}//end of main

}//end of class
