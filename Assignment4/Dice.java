// -------------------------------------------------------
// Assignment 4
// Written by: Auvigoo Ahmed
// For COMP 248 Section P - Fall 2019
// December 2nd 2019
// -------------------------------------------------------

//***DICE CLASS***//


/* The purpose of this program is to define a class in which two dice will be rolled and their sum
 * will determine the position in which the player will land in the driver file. Additionally, it will
 * also determine if the player rolls a double or not, which will be useful in the driver file.
 * */
import java.util.Random;
public class Dice {

	private int die1;// roll of the 1st die
	private int die2;// roll of the 2nd die  

	public Dice() {// default constructor

	}

	public int getDie1() {//accessor method for die 1
		return this.die1;
	}

	public int getDie2() {//accessor method for die 2
		return this.die2;
	}

	public int rollDice() {//method to roll 2 dice and return their sum
		Random rand = new Random();
		int die1 = rand.nextInt(6) + 1;
		this.die1 = die1;
		int die2 = rand.nextInt(6) + 1;
		this.die2 = die2;
		int sum = die1 + die2;
		return sum;
	}

	public boolean isDouble() {//method to determine if die 1 is equal to die 2
		return (die1 == die2);
	}

	public String toString() {//returns a string describing the roll of each die
		return "Die 1:" + die1 + " Die 2:" + die2;
	}


}
