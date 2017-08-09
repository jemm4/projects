import java.awt.*;
import java.awt.event.*;

/**
 * This class will start the game of mini-war.
 * It implements all of the other classes in the project.
 * @author Jeffrey Martinez
 *
 */
public class Game extends Frame implements MouseListener{
	
	/**	
	 * This will give the user a handle on the window
	 **/
	private UneFenetre myWindow;
	
	/**
	 * Makes the buttons Play, Clear and Quit
	 */
	private Button Play, Clear, Quit;
	/**
	 * The width and the height of the button
	 */
	private final int BUTTON_WIDTH = 80, 
			BUTTON_HEIGHT = 35;
	
	/**
	 * Instantiates the first deck and the second deck of the face down cards
	 */
	private Card firstDeck, secondDeck;
	/**
	 * Instantiates the graph that will pop up to keep score
	 */
	private BarGraph graph;
	/**
	 * Stores the values of the mouse-x and mouse-y
	 */
	private int x, y;

	/**
	 * If something is clicked then it will tell the program to shwo
	 * the cards depending on whether Play or Clear is clicked
	 */
	private boolean click = false;
	
	private boolean initializationComplete = false;
	
	/**
	 * Instantiate the two face up cards
	 */
	private Card faceUpOne, faceUpTwo;
	
	/**
	 * will hold the ranks of each of the cards; the score will hold the score
	 */
	private int rankOne, rankTwo, score = 0;
	
	/**
	 * 
	 */
	private int counter = 0;
	
	/**
	 * Main constructor for the Game class
	 */

	public Game()
	{
		setTitle("Playing Mini-War!");
		setLocation(100, 100);
		setSize(600, 450);
		setBackground(Color.gray);

		myWindow = new UneFenetre();			// Allows the user to close the window
		addWindowListener(myWindow);			//		using the "X" button
		
		addMouseListener(this);					// ""

		Play = new Button("Play!", Color.GREEN, 210, 380, BUTTON_WIDTH, BUTTON_HEIGHT);
		Clear = new Button("Clear.", Color.cyan, 340, 380, BUTTON_WIDTH, BUTTON_HEIGHT);
		Quit = new Button("Quit!", Color.red, 470, 380, BUTTON_WIDTH, BUTTON_HEIGHT);
		
		/*
		 * draws the first and second face down card
		 */
		firstDeck = new Card(10, 50, false);
		secondDeck = new Card(460, 50, false);

		setVisible(true);
		initializationComplete = true;
	
	}
	/**
	 * Paint method; paints the cards and the buttons
	 */
	public void paint(Graphics pane)
	{
		if (initializationComplete == true)
		{
			Play.paint(pane);
			Clear.paint(pane);
			Quit.paint(pane);
			firstDeck.paint(pane);

			if (click == true)
			{
				faceUpOne.paint(pane);
				faceUpTwo.paint(pane);
				graph.drawGraph(pane);
			}

			secondDeck.paint(pane);
		}
	}
	
	/**
	 * If the mouse is clicked inside of the the buttons then it will
	 * do according to what the button should do.
	 */
	public void mouseClicked(MouseEvent event)
	{
		if (Play.isInside(x, y))
		{
			faceUpOne = new Card(160, 50, true);
			faceUpTwo = new Card(310, 50, true);

			rankOne = faceUpOne.getCardNum();
			rankTwo = faceUpTwo.getCardNum();

			if (rankOne > rankTwo)
			{
				if (counter > 0)
				{
					score -= 2 * counter;
					counter = 0;
				}
				else
					score--;
			}
			else if (rankOne < rankTwo)
			{
				if (counter > 0)
				{
					score += 2 * counter;
					counter = 0;
				}
				else
					score++;
			}
			else
			{
				counter++;
			}

			graph = new BarGraph(score);

			click = true;
		}

		else if(Clear.isInside(x, y))
		{
			click = false;
			score = 0;
		}
		else if(Quit.isInside(x, y))
			System.exit(0);
	}

	public void mouseEntered(MouseEvent arg0) {}

	public void mouseExited(MouseEvent arg0) {}

	/**
	 * When the mouse is pressed, if the mouse is inside the play button then
	 * flip it, if it's inside clear then set click to false and then flip that,
	 * if it is inside quit then flip quit
	 */
	public void mousePressed(MouseEvent event)
	{
		x = event.getX();
		y = event.getY();

		if(Play.isInside(x, y))
		{
			Play.flip();
			repaint();
		}
		else if(Clear.isInside(x, y))
		{
			Clear.flip();
			click = false;
			repaint();
		}
		else if(Quit.isInside(x, y))
		{
			Quit.flip();
			repaint();
			System.exit(0);
		}
	}
	
	public void mouseReleased(MouseEvent event)
	{
		if(Play.isInside(x, y))
		{
			Play.flip();
		}
		else if(Clear.isInside(x, y))
		{
			Clear.flip();
		}
		else if(Quit.isInside(x, y))
		{
			Quit.flip();
		}
	}
	

	
	/**
	 * Main method
	 * @param args
	 */
	public static void main (String[] args)
	{
		Game playGame = new Game();
	}
}
