import java.awt.*;
/**
 * This class will create a blank card and then choose a random suit
 * along with a random number and paint it on the card.
 * @author Jeffrey Martinez
 *
 */
public class Card {
	private final int WIDTH = 100, HEIGHT = 200; // Width and height of the card
	private final int ARC_LENGTH = 10; // The length of the arc of the rounded rectangle
	private int x, y; // Will pass by a constructor and will tell the cards where to draw
	private final int OFF_SET = 5, SECOND_OFF_SET = 10; // offset between the cards
	private final int CIRCLE = 37, CLUBS = 36; // the radius of the circles for the suits and the club
	
	/**
	 * This boolean will keep track of whether or not the card is face up or not. 
	 */
	private boolean faceUp;
	/**
	 * The number/letter on the card
	 */
	private String[] numCard = {"A", "1","2","3","4","5","6",
			"7","8","9","10","J","Q","K"};
	/*
	 * The value used in the if statement used here will be used for the
	 * array established above. 
	 */
	private int cardNum = (int)(Math.random() * 12) + 1; 
	
	
	/**
	 * main constructor
	 * @param someX
	 * @param someY
	 * @param bool
	 */
	public Card(int someX, int someY, boolean bool){
		x = someX;
		y = someY;
		faceUp = bool;
	}
	/**
	 * Draws clubs
	 * @param graphic
	 */
	public void drawClubs(Graphics gClub){
			int[] xArray = new int[3];
			int[] yArray = new int[3];

			xArray[0] = (x + WIDTH/2);
			xArray[1] = (x + WIDTH/2) - (ARC_LENGTH);
			xArray[2] = (x + WIDTH/2) + (ARC_LENGTH);

			yArray[0] = (y + HEIGHT/2) - (ARC_LENGTH * 3);
			yArray[1] = (y + HEIGHT/2) + HEIGHT/6;
			yArray[2] = (y + HEIGHT/2) + HEIGHT/6;

			gClub.setColor(Color.WHITE);
			gClub.fillPolygon(xArray, yArray, 3);
			gClub.fillOval((x + WIDTH/2) - 31, (y + HEIGHT/2) - 11, CLUBS, CLUBS);
			gClub.fillOval((x + WIDTH/2) - 6, (y + HEIGHT/2) - 11, CLUBS, CLUBS);
			gClub.fillOval((x + WIDTH/2) - 19, (y + HEIGHT/2)- 28, CLUBS, CLUBS);
		}
	/**
	 * Draws the diamonds
	 * @param gDiamond
	 */
	public void drawDiamonds(Graphics gDiamond)
	{	
		int[] xArray = new int[4];
		int[] yArray = new int[4];

		xArray[0] = x + WIDTH/2;
		xArray[1] = (x + WIDTH/2) - (OFF_SET * 4);
		xArray[2] = x + WIDTH/2;
		xArray[3] = (x + WIDTH/2) + (OFF_SET * 4);

		yArray[0] = (y + HEIGHT/2) + (ARC_LENGTH * 3);
		yArray[1] = y + HEIGHT/2;
		yArray[2] = (y + HEIGHT/2) - (ARC_LENGTH * 3);
		yArray[3] = y + HEIGHT/2;

		gDiamond.setColor(Color.RED);
		gDiamond.fillPolygon(xArray, yArray, 4);
	}

	
	/**
	 * Draws the hearts
	 * @param gHeart
	 */
	public void drawHearts(Graphics gHeart)
	{
		int[] xArray = new int[3];
		int[] yArray = new int[3];

		xArray[0] = (x + WIDTH/2) - (ARC_LENGTH * 3);
		xArray[1] = x + WIDTH/2;
		xArray[2] = (x + WIDTH/2) + (ARC_LENGTH * 3);

		yArray[0] = (y + HEIGHT/2);
		yArray[1] = (y + HEIGHT/2) + (ARC_LENGTH * 3);
		yArray[2] = (y + HEIGHT/2);

		gHeart.setColor(Color.RED);
		gHeart.fillPolygon(xArray, yArray, 3);
		gHeart.fillOval((x + WIDTH/2) - 31,
				(y + HEIGHT/2) - HEIGHT/8, CIRCLE, CIRCLE);
		gHeart.fillOval((x + WIDTH/2) - 6, (y + HEIGHT/2)
				- HEIGHT/8,	CIRCLE, CIRCLE);
	}

	/**
	 * Draws the spades
	 * @param gSpade
	 */
	public void drawSpades(Graphics gSpade)
	{
		int[] xArray = new int[3];
		int[] yArray = new int[3];
		int[] xLines = new int[3];
		int[] yLines = new int[3];

		xArray[0] = (x + WIDTH/2);
		xArray[1] = (x + WIDTH/2) - (ARC_LENGTH * 3);
		xArray[2] = (x + WIDTH/2) + (ARC_LENGTH * 3);

		yArray[0] = (y + HEIGHT/2) - (ARC_LENGTH * 3);
		yArray[1] = (y + HEIGHT/2);
		yArray[2] = (y + HEIGHT/2);

		xLines[0] = (x + WIDTH/2);
		xLines[1] = (x + WIDTH/2) - (ARC_LENGTH);
		xLines[2] = (x + WIDTH/2) + (ARC_LENGTH);

		yLines[0] = (y + HEIGHT/2) - (ARC_LENGTH * 3);
		yLines[1] = (y + HEIGHT/2) + HEIGHT/6;
		yLines[2] = (y + HEIGHT/2) + HEIGHT/6;

		gSpade.setColor(Color.WHITE);
		gSpade.fillPolygon(xArray, yArray, 3);
		gSpade.fillOval((x + WIDTH/2) - 31, (y + HEIGHT/2) - 11, CIRCLE, CIRCLE);
		gSpade.fillOval((x + WIDTH/2) - 6, (y + HEIGHT/2) - 11, CIRCLE, CIRCLE);
		gSpade.setColor(Color.WHITE);
		gSpade.fillPolygon(xLines, yLines, 3);
	}
	
	/**
	 * Method draws the card 
	 */
	private void drawCard(Graphics pane){
		int suit; // what the suit of the card will be
				
		// The foreground color of the card
		pane.setColor(Color.BLACK);
		pane.fillRoundRect(x, y, WIDTH, HEIGHT, ARC_LENGTH, ARC_LENGTH);
		/**
		 * if the card is face down then it will draw it face down 
		 */
		if (faceUp == false){
			pane.setColor(Color.BLACK);
			pane.fillRoundRect(x + OFF_SET, y + OFF_SET, WIDTH
					- SECOND_OFF_SET, HEIGHT - SECOND_OFF_SET, 
					ARC_LENGTH, ARC_LENGTH);
			pane.setColor(Color.WHITE);
			pane.fillRect(x + SECOND_OFF_SET, y + OFF_SET, ARC_LENGTH,
					HEIGHT - SECOND_OFF_SET);
			pane.fillRect(x + (WIDTH - SECOND_OFF_SET) - SECOND_OFF_SET, 
					y + OFF_SET, ARC_LENGTH, HEIGHT - SECOND_OFF_SET);
			/*
			 * The design on the card (circles and such)
			 */
			pane.setColor(Color.WHITE);
			pane.fillOval(((x + WIDTH/2) - 20), ((y + HEIGHT/2) - 20), CIRCLE, CIRCLE);
			pane.setColor(Color.WHITE);
			pane.fillOval(((x + WIDTH/2) - 20), ((y + HEIGHT/2) - 40), CIRCLE, CIRCLE);
			pane.setColor(Color.GREEN);
			pane.fillOval(((x + WIDTH/2) - 20), ((y + HEIGHT/2) - 30), CIRCLE, CIRCLE);
			pane.setColor(Color.WHITE);
			pane.drawOval(((x + WIDTH/2) - 20), ((y + HEIGHT/2) - 20), CIRCLE, CIRCLE);
			pane.setColor(Color.WHITE);
			pane.drawOval(((x + WIDTH/2) - 20), ((y + HEIGHT/2) - 40), CIRCLE, CIRCLE);
		}
		/**
		 * If faceUp == true then there will be a random suit drawn on the card
		 */
		else{
			
			suit = (int)(Math.random() * 4) + 1;

			switch (suit)
			{
			case 1:
				drawClubs(pane);
				pane.setColor(Color.WHITE);
				break;
			case 2:
				drawSpades(pane);
				pane.setColor(Color.WHITE);
				break;
			case 3:
				drawHearts(pane);
				pane.setColor(Color.RED);
				break;
			case 4:
				drawDiamonds(pane);
				pane.setColor(Color.RED);
				break;
			}
			/*
			 * Decides what the number on the card will be randomly
			 */
			Font write = new Font("Serif", Font.BOLD, 20);
			pane.setFont(write);
			pane.drawString(numCard[cardNum], x + 5, y + 25);
		}
	}

	/**
	 * the paint method for the card
	 * @param pane
	 */
	public void paint(Graphics pane)
	{
		drawCard(pane);
	}
	/**
	 * 
	 * @return the number that appears on the card
	 */
	public int getCardNum(){
		return cardNum;
	}
}
