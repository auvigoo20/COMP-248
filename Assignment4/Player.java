// -------------------------------------------------------
// Assignment 4
// Written by: Auvigoo Ahmed 40128901
// For COMP 248 Section P - Fall 2019
// December 2nd 2019
// -------------------------------------------------------

//***PLAYER CLASS***//

/* The purpose of this program is to define a class which will store the values of the position, names and energy levels
 * of the players of the game. It also contains a method that will determine if a player has won, which could be used
 * to end the game in the driver file.
 * */
public class Player {
	
	private String name;
	private int level, x, y, energy;

	public Player() {//default constructor
		name = "";
		energy = 10;
		level = 0;
		x = 0;
		y = 0;
	}

	public Player(String name) {//Constructor that takes a string parameter representing the name of the player
		this.name = name;
		this.level = 0;
		this.x = 0;
		this.y =0;
		this.energy = 10;
	}

	public Player(int level, int x, int y) {//Constructor that takes three integer parameters representing the position of the player
		this.level = level;
		this.x = x;
		this.y = y;
		this.energy = 10;
		this.name="";
	}
	
	public Player(Player p) {// Copy constructor
		this.name = p.name;
		this.level = p.level;
		this.x = p.x;
		this.y = p.y;
		this.energy = p.energy;
	}
	
	
	public String getName() {//Accessor method for the player's name
		return name;
	}

	public void setName(String name) {//Mutator method for the player's name
		this.name = name;
	}

	public int getLevel() {//Accessor method for the level
		return level;
	}

	public void setLevel(int level) {//Mutator method for the level
		this.level = level;
	}

	public int getX() {//Accessor method for the x position
		return x;
	}

	public void setX(int x) {//Mutator method for the x position
		this.x = x;
	}

	public int getY() {//Accessor method for the y position
		return y;
	}

	public void setY(int y) {//Mutator method for the y position
		this.y = y;
	}

	public int getEnergy() {//Accessor method for the energy
		return energy;
	}

	public void setEnergy(int energy) {//Mutator method for the energy
		this.energy = energy;
	}
	
	public void moveTo(Player p) {//Method to move a player to another player's location
		this.level = p.level;
		this.x = p.x;
		this.y = p.y;
	}
	
	public boolean won(Board b) {//Method to determine if a player has won (if the player is at the last position of the board)
		
		if(level == b.getLevel()-1 && x == b.getSize()-1 && y == b.getSize()-1 ) {
			return true;
		}
		else
			return false;
		
	}
	
	public boolean equals(Player p) {//Method to determine if a position is occupied by another player
		
		if(p.level == this.level && p.x == this.x && p.y == this.y) {
			return true;	
		}
		else
			return false;
	}
	
	public String toString() {//Returns a string that displays the position and energy adjustment value at that position
		return "Player "+this.name+" has "+this.energy+" energy and is at level "+this.level+" at "
				+ "position ("+this.x+", "+this.y+").";
	}

	
	
}
