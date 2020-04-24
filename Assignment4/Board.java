// -------------------------------------------------------
// Assignment 4
// Written by: Auvigoo Ahmed 40128901
// For COMP 248 Section P - Fall 2019
// December 2nd 2019
// -------------------------------------------------------

//***BOARD CLASS***//

/* The purpose of this program is to define a class which will generate the board for the game.
 * The board consists of a 3-D array, with the first dimension representing the level, the second
 * dimension representing the rows and the third dimension representing the columns of the board. 
 * At each position, there is a specific energy value that will be added or subtracted to the total 
 * energy value of the player that lands on that position. 
 * */
public class Board {

	private static final int MIN_LEVEL = 3;
	private static final int MIN_SIZE = 3;
	private int level;
	private int size;
	private int [][][] board = null;
	
	Board() {//Default constructor that sets the number of levels to 3 and the size of the board to 4
		level = 3;
		size = 4;
		createBoard(level, size);
	}

	Board(int l, int x) {//Construtor that generates a board depending on the user input in the driver file
		level = l;
		size = x;
		createBoard(l, x);
	}

	private void createBoard(int level, int size) {//This method will create the board by filling each position with a specific energy value
		int l = level;
		int x = size;
		int y = size;
		board = new int[level][size][size];
		
		for (int i = 0; i < l; i++) { // LEVEL

			for (int j = 0; j < x; j++) {// ROW

				for (int k = 0; k < y; k++) {// COLUMN
					if (i + j + k == 0) {//First position of the board
						board[i][j][k] = 0;
					} else if ((i + j + k) % 3 == 0) {//if the position is a multiple of 3
						board[i][j][k] = -3;
					} else if ((i + j + k) % 5 == 0) {//if the position is a multiple of 5
						board[i][j][k] = -2;
					} else if ((i + j + k) % 7 == 0) {//if the position is a multiple of 7
						board[i][j][k] = 2;
					} else {
						board[i][j][k] = 0;
					}
				}
				}
		}
	}

	public int getLevel() {//Accessor method for level
		return level;
	}

	public int getSize() {//Accessor method for size
		return size;
	}

	public int getEnergyAdj(int l, int x, int y) {//Returns the energy adjustment value that is stored in a specific location in the board
		return board[l][x][y];
	}
	
	public String toString() {//Returns a string that displays the energy adjustment values for the board at each position
		String stringBoard = "";
		int l = level;
		int x = size;
		int y = size;
		for (int i = 0; i < l; i++) {
			stringBoard += "Level " + i + "\n--------\n";
			for (int j=0; j < x; j++) {
				for(int k=0; k < y; k++) {
					stringBoard += "\t"+board[i][j][k];
				}
				stringBoard += "\n";
			}
			
		}
		
		return stringBoard;
	}
	
}
