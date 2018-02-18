/**
 * Jeffrey Martinez 
 * The purpose of this class is to implement the rules of the game.
 * If the sum of the dice is even, you win if your die's value is
 * higher than the computer's die. The computer wins if its value 
 * is higher than your die, and it's a tie if you have the same 
 * value. If the sum of the dice is odd, you will win if your die 
 * has a value lower than the computer's. The computer wins if its 
 * die has a value lower than your die.
 */
import java.awt.*;
import java.util.Scanner;
import java.util.Random;

public class Game extends Frame{

	private Die myDie, theComputerDie;  // will be the user-generated die and comp die

	/**
	 * A handle on the window
	 */
	private UneFenetre myWindow;


	// the window where the die pops up
	public Game(){
		setTitle("Let's play dice!");		//	We set the characteristics of our
		setLocation(50, 50);				//  drawing window: the location,
		setSize(600, 500);					//	the size, and
		setBackground(Color.GRAY);			//	the color of the background
		setVisible(true);					//	And we make it appear
		myWindow = new UneFenetre();		// to allow for window closing
		addWindowListener(myWindow);
		myDie = new Die(); 					// draws a new die for myDie
		theComputerDie = new Die(); 		// draws a new die for theComputerDie
		display(); 							// calls the display method
		repaint(); 							// repaints the dice
	}
	/**
	 * paints the user die and then paints the computer die after 2 seconds
	 */
	public void paint(Graphics pane){
		myDie.paint(pane); 					// paints myDie
		/*
		 * makes it so there's a two second delay between the painting of myDie and
		 * the computer die 
		 */
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		theComputerDie.paint(pane);			// paints theComputerDie
	}

	/**
	 * This method contains the rules of the game and what the console displays.
	 * It asks the user to input the number of dots on a die and the coordinates 
	 * of that die. 
	 */
	public void display(){
		/*
		 * The variables userX and userY are both to be used for the x and y coordinates.
		 * The userDots and randNumDots are used for the amount of dots that will be painted
		 * on the user and computer die. The overlapGap will be the gap left between each die.
		 * The totalDots will be used for the rules of the game. The randomNumX and randomNum Y 
		 * will be used for the randomly generated coordinates for the computer die.
		 * HumanW will also be used for the rules of the game.
		 */
		int userX, userY, userDots, randomNumX, randomNumY, overlapGap, totalDots,
		randNumDots, humanW;

		/* 
		 * sets the randomNumX and randomNumY variables to 0 in order to be able to use
		 * the two variables in the while loop 
		 */
		randomNumX = 0;
		randomNumY = 0;
		Random rand = new Random();
		System.out.println("Hi! Let's play dice!");
		Scanner keyboard = new Scanner(System.in);
		// ask for number of dots
		System.out.println("Enter the number on the face of the die: ");
		userDots = keyboard.nextInt();
		/*
		 * this while loop will keep running until the user inputs an int 1-6
		 */
		while (userDots > 6 || userDots == 0 || userDots < 0 ){
			System.out.println("Number not valid, try again.");
			System.out.println("Enter the number (1-6) on the face of the die: ");
			userDots = keyboard.nextInt(); // amount of dots that will be displayed 
		}
		// ask for x and y coordinates
		System.out.println("Enter the location of the die: ");

		userX = keyboard.nextInt(); // x coordinate for the die inputed by the user

		userY = keyboard.nextInt(); // y coordinate for the die inputed by the user


		/*
		 * Used for the reference variable in order to make the 
		 * collected information for the x/y coordinates and 
		 * the number of dots to be able to be used somewhere else
		 * in the Die class.
		 */		
		myDie.setX(userX);
		myDie.setY(userY);
		myDie.setNumDots(userDots);
		/*
		 * overlapGap makes sure that the computer die does not overlap 
		 * with the user-generated die
		 */
		overlapGap = userX + 150;
		/*
		 * while the random number generated for the x and y coordinates is smaller
		 * than the overlapGap, then it will keep assigning a random number to the
		 * variables randomNumX and randomNumY
		 */
		while (randomNumX < overlapGap){
			randomNumX = rand.nextInt(userX + 200);
		}
		while (randomNumY < overlapGap){
			randomNumY = rand.nextInt(userY + 200);
		}
		/*
		 * sets the x and y coordinate for the computer die to the randomNumX
		 * and randomNumY
		 */
		theComputerDie.setX(randomNumX);
		theComputerDie.setY(randomNumY);
		/*
		 * sets the number of dots for the computer die to a random number between
		 * 1 and 6
		 */
		randNumDots = rand.nextInt(6) + 1;
		theComputerDie.setNumDots(randNumDots);

		/*
		 * Checks to see if the human-generated dots are greater than
		 * the computer's dots. If it is, then it sets humanW equal to one.
		 * If the userDots are less than randNumDots then it sets humanW
		 * equal to two. If they are equal then it sets humanW equal to zero. 
		 */
		if (userDots > randNumDots){
			humanW = 1;
		}
		else if (userDots < randNumDots){
			humanW = 2;
		}
		else{
			humanW = 0;
		}

		/*
		 * Adds the userDots and randNumDots in order to check to
		 * see if they are odd or even later on in the program. 
		 */
		totalDots = userDots + randNumDots;

		/*
		 * If the total amount of dots are even and the user-generated dots are
		 * greater than the computer's dots then the user wins
		 */
		if (((totalDots % 2) == 0) && (humanW == 1)){
			System.out.println("You win!");
		}
		/*
		 * Otherwise if the totalDots are even and the computer's dots are higher then
		 * the user loses
		 */
		else if (((totalDots % 2) == 0) && humanW == 2){
			System.out.println("You lose!");
		}
		/*
		 * Or if it is odd and the computer's dots are higher than the userDots then
		 * the user wins
		 */
		else if (((totalDots % 2) != 0) && humanW ==2){
			System.out.println("You win!");
		}
		/*
		 * if the amount of total dots are odd and the user's dots are higher than the 
		 * computer's dots then the user loses
		 */
		else if (((totalDots % 2) != 0) && humanW == 1){
			System.out.println("You lose!");
		}
		/*
		 * If the amount of computer dots and userDots are the same, then it's a tie
		 */
		else if(humanW == 0){
			System.out.println("It's a tie!");
		}
	}

	/**
	 * main method that runs the whole class
	 * @param args
	 */
	public static void main(String[] args) 
	{	
		Game myGameTable = new Game(); // instantiates and runs the whole Game class under myGameTable
	}	
}
