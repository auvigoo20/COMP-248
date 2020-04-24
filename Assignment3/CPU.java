// -------------------------------------------------------
// Assignment 3
// Written by: Auvigoo Ahmed 40128901
// For COMP 248 Section P - Fall 2019
// -------------------------------------------------------


//*** QUESTION 2 ***
/* The following program defines a class for properties of an Intel CPU. It has 6 accessor methods to
 * return the values of the generation, series, price, speed, launch date and SGX support.
 * It has one mutator method to set the suggested price. It has a method to return the price of the CPU corresponding
 * to a certain date, since the price decreases with time. It has a method to return all the values of the properties of the CPU.
 * It has a method to compare the generation series and SGX support of two CPUs. Finally, it has a method to compare two CPUs based
 * on their generations.*/


public class CPU {

	//Declaring the private instance variables that describe the CPU
	private int generation;
	private String series;
	private double price;
	private double speed;
	private String date;
	private String support;
	
	//The first constructor with no argument
	public void initialize() 
	{
		this.generation = 1;
		this.series= "i3";
		this.price= 117.00;
		this.speed= 2.93;
		this.date= "Q1'10";
		this.support= "SGX is not supported";
	}
	
	//The second constructor method with arguments
	public void initialize(int generation, String series, double price, double speed, String date, String support) {
		this.generation = generation;
		this.series=series;
		this.price=price;
		this.speed=speed;
		this.date=date;
		this.support=support;
	}
	
	//The 6 following methods are accessor methods that return the value of each property of the CPU
	public int getGeneration() {
		return generation;
	}
	public String getSeries() {
		return series;
	}
	public double getPrice() {
		return price;
	}
	public double getSpeed() {
		return speed;
	}
	public String getDate() {
		return date;
	}
	public String getSupport() {
		return support;
	}
	
	//The mutator method that sets the suggested price of the CPU
	public double setPrice(double price) {
		this.price = price;
		return price;
	}
	
	//This method returns the price of the CPU at a certain date
	public double priceNow(String sQuarterYear) {
		
		//Transforming the launch date into an integer
		String[] launchDate=date.split("'");//Splitting the quarter and the year of launch date
		String dateQuarter= launchDate[0];
		String dateQuarterNum=dateQuarter.substring(1,2);//Isolating the quarter number (Always 1-4)
		int dateQuarterNumber= Integer.parseInt(dateQuarterNum);//Converting the quarter number into an integer
		String dateYear=launchDate[1];
		int dateYearNumber= Integer.parseInt(dateYear);//Converting the year into an integer
		int dateYearQuarterNumber= dateYearNumber*4;//Converting the year into quarters(multiply by 4 since there are 4 quarters per year)
		int totalDateQuarter= dateQuarterNumber+dateYearQuarterNumber;//Getting the launch date in quarters
		
		
		//Transforming the sQuarterYear into an integer
		String[] quarterYear= sQuarterYear.split("'");//Splitting the quarter and the year of the supplied date
		String quarter= quarterYear[0];
		String quarterNum= quarter.substring(1, 2);//Isolating the quarter number (Always 1-4)
		int quarterNumber= Integer.parseInt(quarterNum);//Converting the quarter number into an integer
		String year= quarterYear[1];
		int yearNumber= Integer.parseInt(year);//Converting the year into an integer
		int yearQuarterNumber= yearNumber*4;//Converting the year into quarters(multiply by 4 since there are 4 quarters per year)
		int totalQuarter= quarterNumber+yearQuarterNumber;//Getting the supplied date in quarters
		
		
		if(totalQuarter<=totalDateQuarter) {//If the supplied date is before the launch date, the price remains unchanged
			return price; 
		}
		else {
			int differenceQuarter= totalQuarter-totalDateQuarter;//Finding the number of quarters between the launch date and the supplied date
			double reduce= price*0.02;//Calculating the amount to subtract after each quarter
			for(int i=1; i<=differenceQuarter; i++) {
				
				price = price - reduce;//Reducing the price after each quarter
			}
			return price;
		}	
	}
	
	//This method returns all the values of the CPU
	public String toString() {
		return ("This CPU is "+ this.generation+"th generation "+this.series+" ("+this.speed+"GHz), launched: "+ this.date+" with price: "+
				this.price+" USD. "+this.support+".");
	}
	
	//This method tests the equality of two CPUs according to their generation, series and SGX support
	public void equals(int generation, String series, String support) {
		if(this.generation==generation && this.series.equalsIgnoreCase(series) && this.support.equalsIgnoreCase(support)) {
			System.out.println("YES");
		}
		else {
			System.out.println("NO");
		}
	} 
	
	//This method compares two CPUs based on their generations
	public void isHigherGeneration(int generation) {
		if(this.generation>generation) {
			System.out.println("YES");
		}
		else {
			System.out.println("NO");
		}
	}
	
	
//*****************************************************************************************************************************************//
	
	
//This following is the main method of the CPU class. Two CPU objects are created and each method is used to test the validity of
//the CPU class.
	
public static void main(String[] args) {
		
		//Welcoming message
		System.out.println("Welcome to the simple class example!\n");
		
		
		
		//First CPU object declaration
		CPU cpu1 = new CPU();
		cpu1.initialize();//the constructor with no argument
		System.out.println("CPU 1: "+cpu1.toString());
		
		//Second CPU object declaration
		CPU cpu2= new CPU();
		cpu2.initialize(10, "i9", 449.00, 3.1, "Q2'19", "SGX is supported");//the constructor with arguments
		System.out.println("CPU 2: "+cpu2.toString());
		
		System.out.println("CPU 1 Series: "+cpu1.getSeries());
		System.out.println("CPU 1 Suggested price: "+cpu1.getPrice()+" USD ");
		System.out.println("CPU 1 Suggested price (after mutator call): "+cpu1.setPrice(110.00)+" USD");//Testing the mutator method
		
		//Testing the equality of CPU 1 and CPU 2 with the equals() method
		System.out.print("Are CPU 1 and CPU 2 equal? ");
		cpu1.equals(cpu2.getGeneration(), cpu2.getSeries(), cpu2.getSupport());
			
		
		//Comparing the generation of CPU 1 and CPU 2 with the isHigherGeneration() method
		System.out.print("Is CPU 1 of higher generation than CPU 2? ");
		cpu1.isHigherGeneration(cpu2.getGeneration()); 
			
		
		//Calculating the price of CPU 1 according to a supplied date using the priceNow() method
		System.out.print("CPU 1 Price at Q3'19 :");
		double cpu1PriceAt = cpu1.priceNow("Q3'19");
		String cpu1PriceAtFormatted=String.format("%.02f", cpu1PriceAt);//Formatting the price in order to display 2 decimals
		System.out.println(cpu1PriceAtFormatted+" USD");
		

		//Calculating the price of CPU 2 according to a supplied date using the priceNow() method
		System.out.print("CPU 2 Price at Q3'19 :");
		double cpu2PriceAt= cpu2.priceNow("Q3'19");
		String cpu2PriceAtFormatted= String.format("%.02f", cpu2PriceAt);//Formatting the price in order to display 2 decimals
		System.out.println(cpu2PriceAtFormatted+ " USD");
		
		
		//Closing Message
		System.out.println("\nThank you for testing the simple class example!");
		
			
		
		
		
		
	}//end of main
	
	
	
	

}//end of class CPU

