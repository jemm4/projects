/**
 * Jeffrey Martinez
 * Second Programming project
 * This class sets up the faces of the die. It has three 
 * squares for the faces of the die and then draws dots
 * according to how many dots the user specifies to pop up.
 * The die moves position according to what the user assigns the
 * position to.
 */
import java.awt.*;
import java.util.Scanner;

public class Die extends Frame
{
	private int x; 			// will be the x coordinate
	private int y; 			// will be the y coordinate
	private int userDots; 	// the amount of dots in the die
	// to push the dot bottom(for y) or right(for x) of square
	final int TOP_RIGHT_PUSH = 65;
	// to push the dot top(for y) or left(for x) of square
	final int TOP_LEFT_PUSH = 15; 
	// to push the dot to the center(for x or y)
	final int CENTER_PUSH = 40;
	/**
	 * A handle on the window
	 */
	private UneFenetre myWindow;

	
	// the window where the die pops up
	public Die(){
		setTitle("Let's play dice!");		//	We set the characteristics of our
		setLocation(50, 50);				//  drawing window: the location,
		setSize(600, 500);					//	the size, and
		setBackground(Color.GRAY);			//	the color of the background
		setVisible(true);					//	And we make it appear
		myWindow = new UneFenetre();//to allow for window closing
		addWindowListener(myWindow);
	}

	/**
	 * draws the skeleton along with the dots
	 * @see java.awt.Window#paint(java.awt.Graphics)
	 */
	public void paint(Graphics pane){
		drawBlank(pane, x, y); 		// draws skeleton
		/*
		 * This if statement checks to see if the user inputed dots
		 * equal 1, 2, 3, etc. and if it does then it'll draw the correct
		 * amount of dots.
		 */
		if (userDots == 1){
			drawOne(pane);
		}
		else if (userDots == 2){
			drawTwo(pane);
		}
		else if (userDots == 3){
			drawThree(pane);
		}
		else if (userDots == 4){
			drawFour(pane);
		}
		else if (userDots == 5){
			drawFive(pane);
		}
		else if (userDots == 6){
			drawSix(pane);
		}
	}

	/**
	 * draws the skeleton of the die
	 * @param pane
	 * @param squareX
	 * @param squareY
	 */
	public void drawBlank(Graphics pane, int squareX, int squareY){
		
		// main face of die
		pane.setColor(Color.BLACK);
		pane.fillRect(squareX, squareY, 100, 100);
		
		pane.setColor(Color.GREEN);
		
		// sets up the top side face
		Polygon topFace = new Polygon();
		topFace.addPoint(squareX, squareY); 				// bottom left
		topFace.addPoint(squareX + 25, squareY - 15); 		// top left
		topFace.addPoint(squareX + 120, squareY - 15); 		// top right
		topFace.addPoint(squareX + 100, squareY); 			// bottom right
		
		pane.drawPolygon(topFace); 							// draws the top side face
		
		// sets up the right side face
		Polygon rightFace = new Polygon();
		rightFace.addPoint(squareX + 100, squareY); 		// top left
		rightFace.addPoint(squareX + 120, squareY - 15); 	// top right
		rightFace.addPoint(squareX + 120, squareY + 75); 	// bottom right
		rightFace.addPoint(squareX + 100, squareY + 100); 	// bottom left
		
		pane.drawPolygon(rightFace); 						// draws the right side face	
	}
	
	/**
	 * Draws dots
	 * @param pane
	 * @param circleX
	 * @param circleY
	 */
	public void drawDot(Graphics pane, int circleX, int circleY){
		pane.fillOval(circleX, circleY, 20, 20);
	}

	// draws the first dot
	private void drawOne (Graphics pane){
		drawDot(pane, x + CENTER_PUSH, y + CENTER_PUSH); 		// center dot
		}
	// draws two dots
	private void drawTwo(Graphics pane){
		drawDot(pane, x + TOP_LEFT_PUSH, y + TOP_LEFT_PUSH); 	// top left dot
		drawDot(pane, x + TOP_RIGHT_PUSH, y + TOP_RIGHT_PUSH); 	// top right dot
	}
	// draws three dots using the drawOne and drawTwo methods
	// to not have repeating code
	private void drawThree(Graphics pane){
		drawOne(pane);
		drawTwo(pane);
	}
	// draws four dots using the drawTwo method along with two other dots
	private void drawFour(Graphics pane){
		drawTwo(pane); 						// top left dot and bottom right dots
		drawDot(pane, x + TOP_RIGHT_PUSH, y + TOP_LEFT_PUSH); 	// top right dot
		drawDot(pane, x + TOP_LEFT_PUSH, y + TOP_RIGHT_PUSH); 	// bottom left dot
	}
	// draws five dots using the drawOne and drawFour method
	private void drawFive(Graphics pane){
		drawOne(pane); 			// center dot
		drawFour(pane); 		// top/bottom left/right dots
	}
	// draws six dots using the drawFour method and it draws two other dots
	private void drawSix(Graphics pane){
		drawFour(pane); // top/bottom left/right dots
		drawDot(pane, x + TOP_LEFT_PUSH , y + CENTER_PUSH); // left center dot
		drawDot(pane, x + TOP_RIGHT_PUSH , y + CENTER_PUSH); // right center dot
	}
	
	/**
	 * main method that runs the whole class
	 * @param args
	 */
	public static void main(String[] args) 
	{	
		int userX; 
		int userY;
		int userDots;
		
		System.out.println("Hi! Let's play dice!");
		Scanner keyboard = new Scanner(System.in);
		// ask for number of dots
		System.out.println("Enter the number on the face of the die: ");
		userDots = keyboard.nextInt();
		/*
		 * this while loop will keep running until the user inputs an int 1-6
		 */
		while (userDots > 6 || userDots == 0){
			System.out.println("Number not valid, try again.");
			System.out.println("Enter the number (1-6) on the face of the die: ");
			userDots = keyboard.nextInt(); // amount of dots that will be displayed 
		}
		// ask for x and y coordinates
		System.out.println("Enter the location of the die: ");
		
		userX = keyboard.nextInt(); // x coordinate for the die inputed by the user
		
		userY = keyboard.nextInt(); // y coordinate for the die inputed by the user
		
		Die die= new Die(); // draws die
		
		/*
		 * Used for the reference variable in order to make the 
		 * collected information for the x/y coordinates and 
		 * the number of dots to be able to be used somewhere else
		 * in the class.
		 */
		die.setX(userX);
		die.setY(userY);
		die.setNumDots(userDots);
	}
	// setting the variable x to someX which is set to userX in the main method
	public void setX(int someX){
		x = someX;
	}
	// returning the new x
	public int getX(){
		return x;
	}
	public void setY(int someY){
		y = someY;
	}
	public int getY(){
		return y;
	}
	public void setNumDots(int numDots){
		userDots = numDots;
	}
	public int getNumDots(){
		return userDots;
	}
}