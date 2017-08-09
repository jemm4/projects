import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * This class is the background of the frame. The empty tiles and any of the other squares
 * that will exist. 
 * @author Jeffrey Martinez
 *
 */
public class Background {
	/**
	 * Default width and height of the tiles
	 */
	private static int width = 20;
	private static int height = 20;

	/**
	 * The amount of bombs allotted for the minefield 
	 */
	private final int AMOUNT_OF_BOMBS = 20;

	/**
	 * Booleans that will determine if the user has won or lost
	 */
	private boolean win;
	private boolean lost;

	private Random random;

	/**
	 * Empty array of squares
	 */
	private Square[][] squares;

	/**
	 * The bomb, flag, normal, and pressed tiles for the tiles
	 */
	private BufferedImage mine = ImageLoad.scale(ImageLoad.loadImage("pix/mine.png"), Square.getWidth(), Square.getHeight());
	private BufferedImage flag = ImageLoad.scale(ImageLoad.loadImage("pix/flag.png"), Square.getWidth(), Square.getHeight());
	private BufferedImage pressed = ImageLoad.scale(ImageLoad.loadImage("pix/pressed.png"), Square.getWidth(), Square.getHeight());
	private BufferedImage normal = ImageLoad.scale(ImageLoad.loadImage("pix/normal.png"), Square.getWidth(), Square.getHeight());


	/**
	 * Main constructor of the class that will draw the squares with the appropriate pictures
	 */
	public Background() {
		/**
		 * Makes new squares in a grid
		 */
		squares = new Square[width][height];

		random = new Random();

		/**
		 * Sets the positions of the squares by using nester for loops to go through
		 * all of the x coordinates and all of the y coordinates
		 */
		for(int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				squares[i][j] = new Square(i, j, normal, mine, pressed, flag);
			}
		}
		reset(); // makes sure everything is reset so the game is brand new
	}

	/**
	 * places a bomb in a square for the amount of bombs
	 */
	private void placeBombs() {
		for(int i = 0; i < AMOUNT_OF_BOMBS; i++) {
			placeBomb();
		}
	}

	/**
	 * if the square is an empty space and a bomb is not in it then there will be a bomb set
	 */
	private void placeBomb() {
		int x = random.nextInt(width);
		int y = random.nextInt(height);

		if(!squares[x][y].isBomb()) {
			squares[x][y].setBomb(true);
		}
		else placeBomb();		
	}

	/**
	 * Sets and assigns the numbers for the squares that have been 
	 * clicked on
	 */
	private void setNumbers()
	{
		for(int x = 0;x < width;x++)
		{
			for(int y = 0;y < height;y++)
			{
				int mx = x - 1; // left of the square being analyzed
				int gx = x + 1; // right of the square being analyzed
				int my = y - 1; // above the square being analyzed
				int gy = y + 1; // below the square being analyzed

				int amountOfBombs = 0; // starts off the amount of bombs counter at 0
				
				/* If there is a bomb anywhere around the square being analyzed, then 
				* increase the counter for the amount of bombs near the square by one
				* for each bomb around it
				*/
				if(mx >= 0 && my >= 0 && squares[mx][my].isBomb())
					amountOfBombs++;
				if(mx >= 0 && squares[mx][y].isBomb())
					amountOfBombs++;
				if(mx >= 0 && gy < height && squares[mx][gy].isBomb())
					amountOfBombs++;
				if(my >= 0 && squares[x][my].isBomb())
					amountOfBombs++;
				if(gy < height && squares[x][gy].isBomb()) 
					amountOfBombs++;

				if(gx < width && my >= 0 && squares[gx][my].isBomb())
					amountOfBombs++;
				if(gx < width && squares[gx][y].isBomb()) 
					amountOfBombs++;
				if(gx < width && gy < height && squares[gx][gy].isBomb()) 
					amountOfBombs++;

				/*
				 * Sets the amount of bombs near the square equal to the amount of bombs
				 * that are actually near the square
				 */
				squares[x] [y].setAmountOfBombsNear(amountOfBombs);
			}
		}
	}

	/**
	 * Method that decides what happens when the user left clicks on the squares
	 * @param x
	 * @param y
	 */
	public void leftClick(int x, int y) {
		// if the user has not lost nor won yet
		if(!lost && !win) {
			/*
			 * then set the x and y coordinate of the square equal to the mouse x/y divided by the
			 * width and height of the square, respectively
			 */
			int xSquare = x / Square.getWidth();
			int ySquare = y / Square.getHeight();
			
			// if the square is not a flag and it is left clicked then
			if(!squares[xSquare][ySquare].isFlag()) {
				// set the square to clickedOn
				squares[xSquare][ySquare].setClickedOn(true);
				// if it is a bomb
				if(squares[xSquare][ySquare].isBomb())
					// then lose
					lost = true;
				else {
					// otherwise if the amount of bombs near the square is 0
					if(squares[xSquare][ySquare].getAmountOfBombsNear() == 0) {
						// draw an open square 
						clickedOn(xSquare, ySquare);
					}
				}
				// check if the game is done everytime the user left clicks
				checkDone();
			}


		}
	}
	
	/**
	 * Method that decides what to do if the user right clicks a square
	 * @param x
	 * @param y
	 */
	public void rightClick(int x, int y) {
		// if the user has not lost nor won then
		if(!lost && !win) {
			/*
			 * assign the x+y coordinate to the mouse x+y
			 * divided by the width and height respectively
			 */
			int xSquare = x / Square.getWidth();
			int ySquare = y / Square.getHeight();
			
			squares[xSquare][ySquare].placeFlag(); // place a flag image
			checkDone(); // check if the game is done
		}
	}

	/**
	 * Method that decides what to do if a square is clicked on
	 * @param x
	 * @param y
	 */
	private void clickedOn(int x, int y)
	{
		// If a square is clicked on then set it equal to true
		squares[x][y].setClickedOn(true);
		
		// if the amount of bombs near the square is 0 then
		if(squares[x][y].getAmountOfBombsNear() == 0)
		{
			int mx = x - 1; // left square being analyzed
			int gx = x + 1; // right square being analyzed
			int my = y - 1; // top square being analyzed
			int gy = y + 1; // bottom square being analyzed

			/* The user can click on the square and then set it to clickedOn
			 * and set the squares around it that have no mines near it
			 * to clicked on as well
			 */
			if(mx >= 0 && my >= 0 && squares[mx][my].canClickOn())
				clickedOn(mx, my);
			if(mx >= 0 && squares[mx][y].canClickOn())
				clickedOn(mx, y);
			if(mx >= 0 && gy < height && squares[mx][gy].canClickOn()) 
				clickedOn(mx, gy);

			if(my >= 0 && squares[x] [my].canClickOn()) 
				clickedOn(x, my);
			if(gy < height && squares[x] [gy].canClickOn()) 
				clickedOn(x, gy);

			if(gx < width && my >= 0 && squares[gx][my].canClickOn())
				clickedOn(gx, my);
			if(gx < width && squares[gx][y].canClickOn())
				clickedOn(gx, y);
			if(gx < width && gy < height && squares[gx][gy].canClickOn()) 
				clickedOn(gx, gy);
		}
	}
	
	/**
	 * Method that checks to see if the game is done
	 */
	private void checkDone() {
		win = true;

		outer : for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				/*
				 * if the squares have been clicked on or is a bomb or is a flag then the user lost and loop
				 * out of the loop that continually checks
				 */
				if(!(squares[x][y].isClickedOn() || squares[x][y].isBomb() || squares[x][y].isFlag())){
					win = false;
					break outer;
				}
			}
		}
	}

	/**
	 * Method that resets everything when called with a new set of bombs and numbers
	 */
	public void reset() {
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				squares[x][y].reset();
			}
		}
		lost = false;
		win = false;
		
		placeBombs();
		setNumbers();
	}
	
	/**
	 * Paints the squares and any other things that are important for the game
	 * @param pane
	 */
	public void paint(Graphics pane) {
		for(int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				squares[i][j].paint(pane);
			}
		}
		// if the user lost
		if(lost){
			//set the color to magenta and print that the user lost
			pane.setColor(Color.MAGENTA);
			pane.drawString("You lose... press R to try again", 150, 200);
		}
		// otherwise if the user won 
		else if(win) {
			// set the color to cyan and print that the user won with enthusiasm
			pane.setColor(Color.CYAN);
			pane.drawString("You win!!1!!1!", 150, 200);
		}
	}

	/**
	 * getter for the width
	 * @return
	 */
	public static int getWidth() {
		return width;
	}
	/**
	 * getter for the height
	 * @return
	 */
	public static int getHeight() {
		return height;
	}
}