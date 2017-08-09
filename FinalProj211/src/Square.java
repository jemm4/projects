import java.awt.Graphics;
import java.awt.image.BufferedImage;


/**
 * This class is for the tiles of the game. It gets pictures that have been loaded into the program 
 * and assigns them to squares wherever they need to be assigned.
 * @author Jeffrey Martinez
 *
 */
public class Square {

	/* 
	 * these buffered images will hold the normal image,
	 * the pressed image, the flag image and the bomb image
	 */
	private BufferedImage normal;
	private BufferedImage pressedImage;
	private BufferedImage flagImage;
	private BufferedImage bombImage;

	/*
	 * the x and y coordinates
	 */
	private int x;
	private int y;
	
	/*
	 * booleans to determine if a square is a bomb,
	 * has been clicked on or is a flag
	 */
	private boolean bomb;
	private boolean clickedOn;
	private boolean flag;
	
	/*
	 * int of amount of bombs near a square
	 */
	private int amountOfBombsNear;

	/*
	 * sets the width and the height proportional to the window width and height
	 */
	private static int width = UneFenetre.getScreenWidth()/Background.getWidth();
	private static int height = UneFenetre.getScreenHeight()/Background.getHeight();

	/**
	 * Constructor for the x+y coordinates, and the images for the squares
	 * @param x
	 * @param y
	 */
	public Square(int x, int y, BufferedImage normal, BufferedImage bomb, 
			BufferedImage pressedImage, BufferedImage flag) {
		this.x = x;
		this.y = y;
		this.normal = normal;
		this.bombImage = bomb;
		this.pressedImage = pressedImage;
		this.flagImage = flag;
	}

	/**
	 * Constructor for the buffered pressed-on square
	 * @return 
	 */
	public void setClickedOn(boolean clickedOn) {
		this.clickedOn = clickedOn;
	}

	/**
	 * Pressed image constructor
	 * @param pressedImage
	 */
	public void setPressedImage(BufferedImage pressedImage) {
		this.pressedImage = pressedImage;
	}

	/**
	 * determines if a square has been clicked on or not
	 * @return 
	 */
	public boolean isClickedOn() {
		return clickedOn;
	}


	/**
	 * Constructor for the thing that determines if a square is a bomb or not
	 * @param bomb
	 */
	public void setBomb(boolean bomb) {
		this.bomb = bomb;
	}

	/**
	 * Constructor for the bomb
	 * @return
	 */
	public boolean isBomb() {
		return bomb;
	}

	/**
	 * Constructor for the amount of bombs near the bomb
	 * @param amountOfBombsNear
	 */
	public void setAmountOfBombsNear(int amountOfBombsNear) {
		this.amountOfBombsNear = amountOfBombsNear;
	}
	
	/**
	 * gets the amount of bombs near a square
	 * @return
	 */
	public int getAmountOfBombsNear() {
		return amountOfBombsNear;
	}

	/**
	 * determines if the user can click on it or not
	 * @return
	 */
	public boolean canClickOn() {
		return !clickedOn && !bomb && amountOfBombsNear >= 0; 
	}

	/**
	 * determines if there can be a flag placed or not
	 */
	public void placeFlag() {
		if(flag)
			flag = false;
		else {
			if(!clickedOn)
				flag = true;
		}
	}
	
	/**
	 * if the square is a flag or not
	 * @return
	 */
	public boolean isFlag() {
		return flag;
	}
	
	/**
	 * constructor for a reset
	 */
	public void reset() {
		flag = false;
		bomb = false;
		clickedOn = false;
	}

	/**
	 * Paint method that draws everything according to the code.
	 * Determines what to paint where and also draws a string that says the
	 * amount of mines near a square
	 * @param pane
	 */
	public void paint(Graphics pane) {
		// if the mine is not clicked yet then it will paint the normal image
		if(!clickedOn){
			if(!flag)
				pane.drawImage(normal, x * width, y * height, null);
			else
				pane.drawImage(flagImage, x * width, y * height, null);
		}
		// otherwise
		else {
			// if the square is a bomb then it will draw a bomb
			if(bomb) {
				pane.drawImage(bombImage, x * width, y* height, null);
			}
			// otherwise draw an the clicked image
			else {
				pane.drawImage(pressedImage, x * width, y * height, null);
				// if there are bombs around the square then draw the number of bombs around it
				if(amountOfBombsNear > 0) {
					pane.drawString("" + amountOfBombsNear, x * width + 8, y * height + 20);
				}
			}
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