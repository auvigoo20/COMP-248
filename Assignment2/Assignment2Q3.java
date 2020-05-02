// -------------------------------------------------------
// Assignment 2
// Written by: Auvigoo Ahmed
// For COMP 248 Section P - Fall 2019
// -------------------------------------------------------
import java.util.Scanner;
public class Assignment2Q3 {

	/*This program will ask the user to input a mathematical expression involving two operands (which
	 * are integers) and one operator (x, +, / , -). It will then calculate and display the result of the
	 * operation and ask the user to either input another expression or to quit the program.      */

	public static void main(String[] args) {

		System.out.println("Hello to mini calculator program.");//This will display the welcoming message
		System.out.println("Please enter the numbers along operation (press q to exit):\n"); 

		Scanner keyIn= new Scanner(System.in);
		String inputExpression;
		inputExpression= keyIn.next();//The string "inputExpression" is the mathematical expression that the user will input into the program.
		boolean quitLoop = true;//This boolean will allow the following do-while loop to either continue or exit the loop.

		do {	

			int expressionLength = inputExpression.length();//This will calculate the length of the string that the user inputs
			int operationMultiplication = inputExpression.indexOf('x');//This will find the index of "x" from the string, if applicable
			int operationAddition = inputExpression.indexOf('+');//This will find the index of "+" from the string, if applicable
			int operationDivision = inputExpression.indexOf('/');//This will find the index of "/" from the string, if applicable
			int operationSubtraction = inputExpression.indexOf('-');//This will find the index of "-" from the string, if applicable

			
			/*The following if statements represent each possibility of finding the appropriate operand in the input. When an operand
			 * is not found in the expression, the index of that operand is -1 */
			
			if (operationMultiplication != -1) {//This if statement will calculate a multiplication operation
				String operand1Mul = inputExpression.substring(0, operationMultiplication);//This will isolate the first operand of the expression
				String operand2Mul = inputExpression.substring(operationMultiplication+1, expressionLength);//This will isolate the second operand of the expression
				int numOperand1Mul= Integer.parseInt(operand1Mul);//This will convert the first string operand into an integer
				int numOperand2Mul= Integer.parseInt(operand2Mul);//This will convert the second string operand into an integer
				double resultMultiplication= numOperand1Mul*numOperand2Mul;//This will calculate the result of the multiplication
				System.out.println("\nThe answer is: " + resultMultiplication);
				System.out.println("Please enter the numbers along operation (press q to exit):\n");
				inputExpression= keyIn.next();
			}


			else if (operationAddition != -1) {//This if statement will calculate an addition operation
				String operand1Add = inputExpression.substring(0, operationAddition);//This will isolate the first operand of the expression
				String operand2Add = inputExpression.substring(operationAddition+1, expressionLength);//This will isolate the second operand of the expression
				int numOperand1Add = Integer.parseInt(operand1Add);//This will convert the first string operand into an integer
				int numOperand2Add = Integer.parseInt(operand2Add);//This will convert the second string operand into an integer
				double resultAddition= numOperand1Add + numOperand2Add;//This will calculate the result of the addition
				System.out.println("\nThe answer is: " + resultAddition);
				System.out.println("Please enter the numbers along operation (press q to exit):\n");
				inputExpression= keyIn.next();
			}

			else if (operationDivision != -1) {//This if statement will calculate a division operation
				String operand1Div = inputExpression.substring(0, operationDivision);//This will isolate the first operand of the expression
				String operand2Div = inputExpression.substring(operationDivision+1, expressionLength);//This will isolate the second operand of the expression
				int numOperand1Div = Integer.parseInt(operand1Div);//This will convert the first string operand into an integer
				int numOperand2Div = Integer.parseInt(operand2Div);//This will convert the second string operand into an integer
				double resultDivision = numOperand1Div/numOperand2Div;//This will calculate the result of the division
				System.out.println("\nThe answer is: " + resultDivision);
				System.out.println("Please enter the numbers along operation (press q to exit):\n");
				inputExpression= keyIn.next();
			}

			else if (operationSubtraction != -1) {//This if statement will calculate a subtraction operation
				String operand1Sub = inputExpression.substring(0, operationSubtraction);//This will isolate the first operand of the expression
				String operand2Sub = inputExpression.substring(operationSubtraction+1, expressionLength);//This will isolate the second operand of the expression
				int numOperand1Sub = Integer.parseInt(operand1Sub);//This will convert the first string operand into an integer
				int numOperand2Sub = Integer.parseInt(operand2Sub);//This will convert the second string operand into an integer
				double resultSubtraction = numOperand1Sub - numOperand2Sub;//This will calculate the result of the subtraction
				System.out.println("\nThe answer is: " + resultSubtraction);
				System.out.println("Please enter the numbers along operation (press q to exit):\n");
				inputExpression= keyIn.next();
			}
			else if (inputExpression.equals("q")){//This will quit the do-while loop if the user inputs "q"
				quitLoop=false;//When the user inputs "q", "quitLoop" becomes false and therefore leaves the loop

			}
		}
		while(quitLoop==true);

		System.out.println("\nThanks for using mini calculator program.");//This will display the closing message


		keyIn.close();
	}//end of main

}//end of class
