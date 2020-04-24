// -------------------------------------------------------
// Assignment 4
// Written by: Auvigoo Ahmed 40128901
// For COMP 248 Section P - Fall 2019
// December 2nd 2019
// -------------------------------------------------------

//***DRIVER FILE***//

/* The purpose of this program is to act as the driver file to implement the game. It will first ask the user
 * to input the desired board size, and then ask for the names of the two players. An array of type Player will then
 * be created, which will store the values of the positions, names and energy levels of both players. The whole game is written
 * inside a while loop with the condition that neither player 1 nor player 2 has won the game. The game starts with player 1 rolling the
 * dice and moving to the appropriate position on the board. The player may lose or gain energy points throughout the game. If a player 
 * lands on an occupied space by the other player, they will have the option to challenge them or forfeit. If they decide to challenge them
 * they will generate a random number from 0 to 10 and if that number is more than 6, they win. If they decide to forfeit, the lose energy points
 * and remain at their position at the board. The game continues until a player lands on the last square at the last level of the board and they are
 * declared the winner of the game.
 * */

import java.util.Scanner;
import java.util.Random;

public class LetUsPlay {

	public static void main(String[] args) {
		Scanner keyIn = new Scanner(System.in);
		Board b = null;
		Dice die = new Dice();
		Player clone1 = null;
		Player clone2 = null;

		//Displaying the welcome banner
		System.out.println("\t*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*");
		System.out.println("\t*                                         *");
		System.out.println("\t*   WELCOME to Nancy's 3D Warrior Game!   *");
		System.out.println("\t*                                         *");
		System.out.println("\t*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*");

		System.out.println("\nThe default game board has 3 levels and each level has a 4x4 board.");
		System.out.println("You can use this default board size or change the size");
		System.out.println("\t0 to use the default board size");
		System.out.println("\t-1 to enter your own size");
		System.out.print("==> What do you want to do? ");
		int userSize = keyIn.nextInt();//asking the user for the desired game board size
		while (userSize > 0 || userSize < -1) {//If the user does not enter 0 or -1
			System.out.println("Sorry but " + userSize + " is not a legal choice.");
			userSize = keyIn.nextInt();
		}

		if (userSize == 0) {//If the user asks for the default game board size and levels
			System.out.println("Your 3D board has been set up and looks like this:\n");
			b = new Board();//creating the default board with 3 levels and size 4
			System.out.println(b.toString());//displaying the default board
		}
		if (userSize == -1) {//If the user does not ask for the default game board 
			System.out.print("\nHow many levels would you like? (minimum size 3, max 10) ");
			int userLevel = keyIn.nextInt();//asking the user for the number of levels
			while (userLevel < 3 || userLevel > 10) {//if the user does not enter a number between 3 and 10
				System.out.println("Sorry but " + userLevel + " is not a legal choice.");
				userLevel = keyIn.nextInt();
			}

			System.out.println("\nWhat size do you want the nxn boards on each level to be?");
			System.out.println("Minimum size is 3x3, max is 10x10");

			System.out.print("==> Enter the value of n: ");
			int userNXN = keyIn.nextInt();//asking the user for the game board size
			while (userNXN < 3 || userNXN > 10) {//if the user does not enter a number between 3 and 10
				System.out.println("Sorry but " + userNXN + " is not a legal choice.");
				userNXN = keyIn.nextInt();
			}

			System.out.println("\nYour 3D board has been set up and looks like this:\n");

			b = new Board(userLevel, userNXN);//creating the custom board from the user input
			System.out.println(b.toString());//displaying the custom board

		}

		Player[] playerArray = new Player[2];//Creating an array of type Player to store the 2 players

		System.out.print("\nWhat is player 0's name (one word only): ");
		String player1Name = keyIn.next();//asking for player 0's name
		playerArray[0] = new Player(player1Name);//initializing player 0's name

		System.out.print("\nWhat is player 1's name (one word only): ");
		String player2Name = keyIn.next();//asking for player 1's name
		playerArray[1] = new Player(player2Name);//initializing player 1's name

		//Determining which player goes first by generating a random number between 0 and 1
		Random rand = new Random();
		int firstPlayer = rand.nextInt(2);
		int secondPlayer;
		System.out.println("\nThe game has started " + playerArray[firstPlayer].getName() + " goes first");
		System.out.println("==========================================\n");
		if (firstPlayer == 0) {//Assigning 0 and 1 to player 1 and player 2
			secondPlayer = 1;
		} else {
			secondPlayer = 0;
		}
		
		//THE GAME BEGINS
		while (!playerArray[firstPlayer].won(b) && !playerArray[secondPlayer].won(b)) {//The condition in the while loop is that neither of the players win

			// FIRST PLAYER'S TURN
			System.out.println("\nIt is " + playerArray[firstPlayer].getName() + "'s turn");

			// If first player's energy is 0 or negative, they cannot move. They roll dice 3 times for a chance to gain energy by rolling a double
			if (playerArray[firstPlayer].getEnergy() <= 0) {
				System.out.println("\t" + playerArray[firstPlayer].getName() + " your energy is less than or equal to 0. "
								+ "\n\tYou may toss the die 3 times for a chance to increase your energy");
				for (int i = 0; i <= 2; i++) {
					die.rollDice();
					if (die.isDouble()) {
						playerArray[firstPlayer].setEnergy(playerArray[firstPlayer].getEnergy() + 2);
					}
				}
				System.out.println("\tYour energy is now " + playerArray[firstPlayer].getEnergy());
			}

			// PLAYER 1 NORMAL TURN
			else {
				int roll = die.rollDice();//Player 1 rolls dice
				System.out.println("\t" + playerArray[firstPlayer].getName() + " you rolled " + die.toString());
				if (die.isDouble()) {//If player 1 rolls a double, their energy goes up by 2
					System.out.println("\tCongratulations you rolled double " + die.getDie1()
							+ ". Your energy went up by 2 units");
					playerArray[firstPlayer].setEnergy(playerArray[firstPlayer].getEnergy() + 2);
				}

				// CALCULATING NEW POTENTIAL LOCATION
				int xToAdd = roll / b.getSize();
				int yToAdd = roll % b.getSize();
				
				//WE CANNOT MOVE PLAYER 1 DIRECTLY TO THEIR NEW POSITION AS IT COULD ALREADY BE OCCUPIED
				//SO WE MAKE A CLONE OF PLAYER 1 USING THE COPY CONSTRUCTOR AND MOVE THEM INSTEAD
				clone1 = new Player(playerArray[firstPlayer]);
			
		//THREE POSSIBLE CONDITIONS TO CONSIDER WHEN MOVING AROUND THE BOARD:
				
				// NO OUT OF BOUNDS IN POSITION & ON THE SAME LEVEL
				if (clone1.getX() + xToAdd < b.getSize() && clone1.getY() + yToAdd < b.getSize()) {
					clone1.setX(clone1.getX() + xToAdd);
					clone1.setY(clone1.getY() + yToAdd);
					clone1.setEnergy(
							clone1.getEnergy() + b.getEnergyAdj(clone1.getLevel(), clone1.getX(), clone1.getY()));

				}

				// X COORDINATE IS OUT OF BOUNDS
				else if (clone1.getX() + xToAdd >= b.getSize() && clone1.getY() + yToAdd < b.getSize()) {
					//If the new position is out of the game board (new level is greater than the number of levels), player doesn't move and they lose 2 energy
					if (clone1.getLevel() + 1 >= b.getLevel()) {
						clone1.setEnergy(clone1.getEnergy() - 2);
					} 
					else {
						clone1.setLevel(clone1.getLevel() + 1);
						int newX = (clone1.getX() + xToAdd) % b.getSize();
						clone1.setX(newX);
						clone1.setY(clone1.getY() + yToAdd);
						clone1.setEnergy(
								clone1.getEnergy() + b.getEnergyAdj(clone1.getLevel(), clone1.getX(), clone1.getY()));

					}
				}
				// Y AND X COORDINATES ARE OUT OF BOUNDS
				else if (clone1.getY() + yToAdd >= b.getSize()) {
					//If the new position is out of the game board (new level is greater than the number of levels), player doesn't move and they lose 2 energy
					if (clone1.getLevel() + 1 >= b.getLevel()) {
						clone1.setEnergy(clone1.getEnergy() - 2);
					} else {
						int newY = (clone1.getY() + yToAdd) % b.getSize();
						int wrongX = (clone1.getX() + xToAdd) + ((clone1.getY() + yToAdd) / b.getSize());
						int newX = wrongX % b.getSize();
						clone1.setLevel(clone1.getLevel() + 1);
						clone1.setX(newX);
						clone1.setY(newY);
						clone1.setEnergy(
								clone1.getEnergy() + b.getEnergyAdj(clone1.getLevel(), clone1.getX(), clone1.getY()));

					}
				}
			//THE CLONE 1 HAS NOW BEEN MOVED TO ITS POSITION. NOW WE HAVE TO SEE IF THE CLONE IS AT AN OCCUPIED POSITION OR NOT
				
				
				//CASE 1:THE CLONE 1 IS NOT AT AN OCCUPIED POSITION SO THE FIRST PLAYER CAN MOVE THERE NORMALLY
				if (!(clone1.equals(playerArray[secondPlayer]))) {
					//If the clone 1 hasn't moved, from the previous 3 cases it means that the new potential location was out of bounds and they
					//lose 2 energy. Therefore, the first player does not move
					if (clone1.equals(playerArray[firstPlayer])) {
						playerArray[firstPlayer].setEnergy(clone1.getEnergy());
						System.out.println(
								"\t!!! Sorry you need to stay where you are - that throw takes you off the grid and you lose 2 units of energy");

					}
					
					//The clone 1 has moved and the first player can move to that location normally
					else {
						playerArray[firstPlayer].moveTo(clone1);
						playerArray[firstPlayer].setEnergy(clone1.getEnergy());

						System.out.println("\tYour energy is adjusted by "
								+ b.getEnergyAdj(playerArray[firstPlayer].getLevel(), playerArray[firstPlayer].getX(),
										playerArray[firstPlayer].getY())
								+ " for landing at (" + playerArray[firstPlayer].getX() + ", "
								+ playerArray[firstPlayer].getY() + ") at level "
								+ playerArray[firstPlayer].getLevel());

					}

				}
				//CASE 2: THE CLONE 1 IS AT AN OCCUPIED POSITION SO THE FIRST PLAYER HAS TO EITHER CHALLENGE OR FORFEIT
				else {
					System.out.println("Player " + playerArray[secondPlayer].getName() + " is at your new location");
					System.out.println("What do you want to do?");//asking the player if they want to challenge or forfeit
					System.out.println("\t0 - Challenge and risk losing 50% of your energy units if you lose\n\t\t"
							+ "or move to new location and get 50% of other player's energy units");
					System.out.println(
							"\t1 - to move down one level or to move to (0,0) if at level 0 and lose 2 energy units");
					int occupied;
					occupied = keyIn.nextInt();
					while (occupied < 0 || occupied > 1) {
						System.out.println("Sorry, but " + occupied + " is not a legal choice.");
						occupied = keyIn.nextInt();
					}
					// FORFEIT
					if (occupied == 1) {
						if (clone1.getLevel() == 0) {//If the new potential location is at level 0, the player has to move to (0,0) at level 0 and they lose 2 energy since they forfeit
							playerArray[firstPlayer].setLevel(0);
							playerArray[firstPlayer].setX(0);
							playerArray[firstPlayer].setY(0);
							playerArray[firstPlayer].setEnergy(clone1.getEnergy() - 2);
							System.out.println("\tYour energy is adjusted by " + b.getEnergyAdj(0, 0, 0)
									+ " for landing at (0,0) at level 0");
						}
						else {//If the new potential location is not at level 0, the player has to move to the new potential location but 1 level down and they lose 3 energy since they forfeit
							playerArray[firstPlayer].setLevel(clone1.getLevel() - 1);
							playerArray[firstPlayer].setX(clone1.getX());
							playerArray[firstPlayer].setY(clone1.getY());
							playerArray[firstPlayer].setEnergy(clone1.getEnergy() - 2);
							System.out.println("\tYour energy is adjusted by "
									+ b.getEnergyAdj(playerArray[firstPlayer].getLevel(),
											playerArray[firstPlayer].getX(), playerArray[firstPlayer].getY())
									+ " for landing at (" + playerArray[firstPlayer].getX() + ", "
									+ playerArray[firstPlayer].getY() + ") at level "
									+ playerArray[firstPlayer].getLevel());
						}
					}

					// CHALLENGE
					else if (occupied == 0) {
						int challenge = rand.nextInt(11);//player will generate a random number between 0 and 10 and if it's less than 6, they lose
						// IF LOSE CHALLENGE, THE FIRST PLAYER DOES NOT MOVE TO THEIR NEW POTENTIAL LOCATION AND THEY LOSE HALF OF THEIR ENERGY
						if (challenge < 6) {
							System.out.println(
									"\tOh no!! :( You lost the challenge and lose half of your energy units and you stay where you are.");
							int originalEnergy = playerArray[firstPlayer].getEnergy();
							int energyLost = originalEnergy / 2;
							playerArray[firstPlayer].setEnergy(originalEnergy - energyLost);
						}
						// IF WIN CHALLENGE, THE FIRST PLAYER MOVES TO THEIR NEW POTENTIAL LOCATION AND THE OTHER PLAYER MOVES TO THE FIRST PLAYER'S ORIGINAL POSITION
						// THE OTHER PLAYER LOSES HALF OF THEIR ENERGY AND THE FIRST PLAYER GAINS IT
						else {
							System.out.println("\tBravo!! You won the challenge");
							// SECOND PLAYER MOVING TO FIRST PLAYER'S POSITION
							playerArray[secondPlayer].moveTo(playerArray[firstPlayer]);

							// SECOND PLAYER LOSING HALF ENERGY
							int secondPlayerEnergy = playerArray[secondPlayer].getEnergy();
							int energyChange = secondPlayerEnergy / 2;
							playerArray[secondPlayer].setEnergy(playerArray[secondPlayer].getEnergy() - energyChange);

							// FIRST PLAYER MOVING TO SECOND PLAYER'S POSITION
							playerArray[firstPlayer].moveTo(clone1);
							playerArray[firstPlayer].setEnergy(clone1.getEnergy());

							// FIRST PLAYER GAINING HALF ENERGY
							playerArray[firstPlayer].setEnergy(playerArray[firstPlayer].getEnergy() + energyChange);

							System.out.println("\tYour energy is adjusted by "
									+ b.getEnergyAdj(playerArray[firstPlayer].getLevel(),
											playerArray[firstPlayer].getX(), playerArray[firstPlayer].getY())
									+ " for landing at (" + playerArray[firstPlayer].getX() + ", "
									+ playerArray[firstPlayer].getY() + ") at level "
									+ playerArray[firstPlayer].getLevel());

						}

					}

				}

			}

			// SECOND PLAYER'S TURN
			System.out.println("It is " + playerArray[secondPlayer].getName() + "'s turn");

			// If second player's energy is 0 or negative, they cannot move. They roll dice 3 times for a chance to gain energy by rolling a double
			if (playerArray[secondPlayer].getEnergy() <= 0) {
				System.out.println(
						"\t" + playerArray[secondPlayer].getName() + " your energy is less than or equal to 0. "
								+ "\n\tYou may toss the die 3 times for a chance to increase your energy");
				for (int i = 0; i <= 2; i++) {
					die.rollDice();
					if (die.isDouble()) {
						playerArray[secondPlayer].setEnergy(playerArray[secondPlayer].getEnergy() + 2);
					}
				}
				System.out.println("\tYour energy is now " + playerArray[secondPlayer].getEnergy());
			}

			// PLAYER 2 NORMAL TURN

			else {
				int roll = die.rollDice();
				System.out.println("\t" + playerArray[secondPlayer].getName() + " you rolled " + die.toString());
				if (die.isDouble()) {//if player 2 rolls a double, their energy goes up by 2
					System.out.println("\tCongratulations you rolled double " + die.getDie1()
							+ ". Your energy went up by 2 units");
					playerArray[secondPlayer].setEnergy(playerArray[secondPlayer].getEnergy() + 2);
				}

				// CALCULATING NEW POTENTIAL LOCATION
				int xToAdd = roll / b.getSize();
				int yToAdd = roll % b.getSize();

				//WE CANNOT MOVE PLAYER 2 DIRECTLY TO THEIR NEW POSITION AT IT COULD ALREADY BE OCCUPIED
				//SO WE MAKE A CLONE OF PLAYER 2 USING THE COPY CONSTRUCTOR AND MOVE THEM INSTEAD
				clone2 = new Player(playerArray[secondPlayer]);

		//THREE POSSIBLE CONDITIONS TO CONSIDER WHEN MOVING AROUND THE BOARD:
				
				// NO OUT OF BOUNDS IN POSITION & ON THE SAME BOARD
				if (clone2.getX() + xToAdd < b.getSize() && clone2.getY() + yToAdd < b.getSize()) {
					clone2.setX(clone2.getX() + xToAdd);
					clone2.setY(clone2.getY() + yToAdd);
					clone2.setEnergy(
							clone2.getEnergy() + b.getEnergyAdj(clone2.getLevel(), clone2.getX(), clone2.getY()));

				}

				// X COORDINATE IS OUT OF BOUNDS
				else if (clone2.getX() + xToAdd >= b.getSize() && clone2.getY() + yToAdd < b.getSize()) {
					if (clone2.getLevel() + 1 >= b.getLevel()) {
						clone2.setEnergy(clone2.getEnergy() - 2);
					} else {
						clone2.setLevel(clone2.getLevel() + 1);
						int newX = (clone2.getX() + xToAdd) % b.getSize();
						clone2.setX(newX);
						clone2.setY(clone2.getY() + yToAdd);
						clone2.setEnergy(
								clone2.getEnergy() + b.getEnergyAdj(clone2.getLevel(), clone2.getX(), clone2.getY()));

					}
				}
				// Y AND X COORDINATES ARE OUT OF BOUNDS
				else if (clone2.getY() + yToAdd >= b.getSize()) {
					//If the new position is out of the game board (new level is greater than the number of levels), player doesn't move and they lose 2 energy
					if (clone2.getLevel() + 1 >= b.getLevel()) {
						clone2.setEnergy(clone2.getEnergy() - 2);
					} 
					else {
						int newY = (clone2.getY() + yToAdd) % b.getSize();
						int wrongX = (clone2.getX() + xToAdd) + ((clone2.getY() + yToAdd) / b.getSize());
						int newX = wrongX % b.getSize();
						clone2.setLevel(clone2.getLevel() + 1);
						clone2.setX(newX);
						clone2.setY(newY);
						clone2.setEnergy(
								clone2.getEnergy() + b.getEnergyAdj(clone2.getLevel(), clone2.getX(), clone2.getY()));

					}
				}
				
		//THE CLONE 2 HAS NOW BEEN MOVED TO ITS POSITION. NOW WE HAVE TO SEE IF THE CLONE IS AT AN OCCUPIED POSIITON OR NOT

			//CASE 1: THE CLONE 2 IS NOT AT AN OCCUPIED POSITION SO THE SECOND PLAYER CAN MOVE THERE NORMALLY
				if (!(clone2.equals(playerArray[firstPlayer]))) {
					//If the clone 2 hasn't moved, from the previous 3 cases it means that the new potential location was out of bounds and they
					//lose 2 energy. Therefore, the second player does not move
					if (clone2.equals(playerArray[secondPlayer])) { 
						playerArray[secondPlayer].setEnergy(clone2.getEnergy());
						System.out.println(
								"\t!!! Sorry you need to stay where you are - that throw takes you off the grid and you lose 2 units of energy");

					}

					//The clone 2 has moved and the second player can move to that location normally
					else {
						playerArray[secondPlayer].moveTo(clone2);
						playerArray[secondPlayer].setEnergy(clone2.getEnergy());

						System.out.println("\tYour energy is adjusted by "
								+ b.getEnergyAdj(playerArray[secondPlayer].getLevel(), playerArray[secondPlayer].getX(),
										playerArray[secondPlayer].getY())
								+ " for landing at (" + playerArray[secondPlayer].getX() + ", "
								+ playerArray[secondPlayer].getY() + ") at level "
								+ playerArray[secondPlayer].getLevel());

					}

				}
				
			//CASE 2: THE CLONE 2 IS AT AN OCCUPIED POSITION SO THE SECOND PLAYER HAS TO EITHER CHALLENGE OR FORFEIT
				else {
					System.out.println("Player " + playerArray[firstPlayer].getName() + " is at your new location");
					System.out.println("What do you want to do?");//asking the palyer if they want to challenge or forfeit
					System.out.println("\t0 - Challenge and risk losing 50% of your energy units if you lose\n\t\t"
							+ "or move to new location and get 50% of other player's energy units");
					System.out.println(
							"\t1 - to move down one level or to move to (0,0) if at level 0 and lose 2 energy units");
					int occupied;
					occupied = keyIn.nextInt();
					while (occupied < 0 || occupied > 1) {
						System.out.println("Sorry, but " + occupied + " is not a legal choice.");
						occupied = keyIn.nextInt();
					}
					// FORFEIT
					if (occupied == 1) {
						if (clone2.getLevel() == 0) {
							//If the new potential location is at level 0, the player has to move to (0,0) at level 0 and they lose 2 energy since they forfeit
							playerArray[secondPlayer].setLevel(0);
							playerArray[secondPlayer].setX(0);
							playerArray[secondPlayer].setY(0);
							playerArray[secondPlayer].setEnergy(clone2.getEnergy() - 2);
							System.out.println("\tYour energy is adjusted by " + b.getEnergyAdj(0, 0, 0)
									+ " for landing at (0,0) at level 0");
						} else {
							//If the new potential location is not at level 0, the player has to move to the new potential location but 1 level down and they lose 3 energy since they forfeit
							playerArray[secondPlayer].setLevel(clone2.getLevel() - 1);
							playerArray[secondPlayer].setX(clone2.getX());
							playerArray[secondPlayer].setY(clone2.getY());
							playerArray[secondPlayer].setEnergy(clone2.getEnergy() - 2);
							System.out.println("\tYour energy is adjusted by "
									+ b.getEnergyAdj(playerArray[secondPlayer].getLevel(),
											playerArray[secondPlayer].getX(), playerArray[secondPlayer].getY())
									+ " for landing at (" + playerArray[secondPlayer].getX() + ", "
									+ playerArray[secondPlayer].getY() + ") at level "
									+ playerArray[secondPlayer].getLevel());
						}
					}

					// CHALLENGE
					else if (occupied == 0) {
						int challenge = rand.nextInt(11);//player will generate a random number between 0 and 10 and if it's less than 6, they lose

						// IF LOSE CHALLENGE, THE SECOND PLAYER DOES NOT MOVE TO THEIR NEW POTENTIAL LOCATION AND THEY LOSE HALF OF THEIR ENERGY
						if (challenge < 6) {
							System.out.println(
									"\tOh no!! You lost the challenge and lose half of your energy units and you stay where you are.");
							int originalEnergy = playerArray[secondPlayer].getEnergy();
							int energyLost = originalEnergy / 2;
							playerArray[secondPlayer].setEnergy(originalEnergy - energyLost);
						}
						// IF WIN CHALLENGE, THE SECOND PLAYER MOVES TO THEIR NEW POTENTIAL LOCATION AND THE OTHER PLAYER MOVES TO THE SECOND PLAYER'S ORIGINAL POSITION
						//THE OTHER PLAYER LOSES HALF OF THEIR ENERGY AND THE SECOND PLAYER GAINS IT
						else {
							System.out.println("\tBravo!! You won the challenge");
							// FIRST PLAYER MOVING TO SECOND PLAYER'S POSITION
							playerArray[firstPlayer].moveTo(playerArray[secondPlayer]);

							// FIRST PLAYER LOSING HALF ENERGY
							int firstPlayerEnergy = playerArray[firstPlayer].getEnergy();
							int energyChange = firstPlayerEnergy / 2;
							playerArray[firstPlayer].setEnergy(playerArray[firstPlayer].getEnergy() - energyChange);

							// SECOND PLAYER MOVING TO FIRST PLAYER'S POSITION
							playerArray[secondPlayer].moveTo(clone2);
							playerArray[secondPlayer].setEnergy(clone2.getEnergy());

							// SECOND PLAYER GAINING HALF ENERGY
							playerArray[secondPlayer].setEnergy(playerArray[secondPlayer].getEnergy() + energyChange);

							System.out.println("\tYour energy is adjusted by "
									+ b.getEnergyAdj(playerArray[secondPlayer].getLevel(),
											playerArray[secondPlayer].getX(), playerArray[secondPlayer].getY())
									+ " for landing at (" + playerArray[secondPlayer].getX() + ", "
									+ playerArray[secondPlayer].getY() + ") at level "
									+ playerArray[secondPlayer].getLevel());

						}

					}

				}

			}
			
			//Displaying a summary of positions and energy of player 1 and 2
			System.out.println("\nAt the end of this round:");
			System.out.println("\t" + playerArray[firstPlayer].getName() + " is on level "
					+ playerArray[firstPlayer].getLevel() + " at location (" + playerArray[firstPlayer].getX() + ", "
					+ playerArray[firstPlayer].getY() + ") and has " + playerArray[firstPlayer].getEnergy()
					+ " units of energy.");
			System.out.println("\t" + playerArray[secondPlayer].getName() + " is on level "
					+ playerArray[secondPlayer].getLevel() + " at location (" + playerArray[secondPlayer].getX() + ", "
					+ playerArray[secondPlayer].getY() + ") and has " + playerArray[secondPlayer].getEnergy()
					+ " units of energy.");

			//Checking if any player has won
			if (playerArray[firstPlayer].won(b) || playerArray[secondPlayer].won(b)) {
				break;//If a player has won, we exit the loop
			}

			//Asking the user to enter any key to continue to the next round
			System.out.print("Any key to continue to next round ...");
			String nextRound = "";
			nextRound = keyIn.next();
			if (nextRound != "") {
				continue;
			}
		}//END OF THE WHILE LOOP

		
		
		//Displaying the name of the winner
		if (playerArray[firstPlayer].won(b)) {
			System.out.println("\n\nThe winner is player " + playerArray[firstPlayer].getName() + ". Well done!!!");
		}
		if (playerArray[secondPlayer].won(b)) {
			System.out.println("\n\nThe winner is player " + playerArray[secondPlayer].getName() + ". Well done!!!");
		}
		
		keyIn.close();

	}// end of main

}// end of class LetUsPlay
