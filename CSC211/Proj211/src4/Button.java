import java.awt.*;
/**
 * This class will create a button to be assigned whatever it is we need
 * in the game class such as a start, a restart and a clear.
 * @author Jeffrey Martinez
 *
 */
public class Button {
	/**
	 * coordinates of the box (top-left corner)
	 */
	private int x, y;
	
	/**
	 * width and the height of the button
	 */
	private int width, height;
	
	/**
	 * whether the button is being pushed or not
	 */
	private boolean faceUp;
	
	/**
	 * label of the button
	 */
	private String label;
	
	private Color lowColor, highColor;
	
	private Shape shape;
	/**
	 * Default constructor of the button in case the button
	 * has no paramaters set
	 */
	public Button(){
		this("?????",							//		with a questionable label,
				 Color.black,					//		in plain black
				 43, 47,						//		at an arbitrary location,
				 143, 147);						//		and with an arbitrary size,
	}	
	
	/**
	 * Main constructor that creates a button with a given label,
	 * color, x-coordinate, y-coordinate, width and height
	 * @param someLabel
	 * @param someColor
	 * @param someX
	 * @param someY
	 * @param someWidth
	 * @param someHeight
	 */
	public Button(String someLabel,
			   Color someColor,
			   int someX, int someY,
			   int someWidth, int someHeight)
	{									
		setup(someLabel,					
		  someColor,							
		  someX, someY,
		  someWidth, someHeight);
		setShape();
	}
	/**
	 * Main constructor that sets up the label, color, x, y, width, and height
	 * @param someLabel what it says on the button
	 * @param someColor the color of the button
	 * @param someX the x-coordinate of the top-left corner of the button
	 * @param someY the y-coordinate of the top-left corner of the button
	 * @param someWidth the width of the button
	 * @param someHeight the height of the button
	 */
	public void setup(String someLabel,
			  Color someColor,
			  int someX, int someY,
			  int someWidth, int someHeight)
	{
		label = someLabel;
		highColor = someColor;
		x = someX;
		y = someY;
		width = someWidth;
		height = someHeight;
		faceUp = true;							//	Initially, the button is up
		lowColor = highColor.darker();			//the color when the button is pressed
	}
	/**
	 * Sets the shape of the button to a rectangle
	 */
	public void setShape(){
		shape = new Rectangle(x, y, width, height);
	}
	/**
	 * 
	 * @param pointX
	 * @param pointY
	 * @return true if the point is inside of the shape
	 */
	public boolean isInside(int pointX, int pointY){
		return shape.contains(pointX,pointY);
	}
	/**
	 * flips the button
	 */
	public void flip(){
		faceUp = !faceUp;
	}
	/**
	 * paint the button
	 * @param pane
	 */
	public void paint(Graphics pane){
		Graphics2D pane2 = (Graphics2D)pane;
		pane2.setColor(faceUp? highColor: lowColor); 
		pane2.fill(shape);
		pane2.setColor(Color.white);
		pane2.draw(shape); //button border
		pane2.setColor(Color.black);
		int labelWidth = pane2.getFontMetrics().stringWidth(label);
		int labelHeight = pane2.getFontMetrics().getAscent();
		pane2.drawString(label,
						x + (width - labelWidth)/2,
						y + (height + labelHeight)/2);
	}


}
