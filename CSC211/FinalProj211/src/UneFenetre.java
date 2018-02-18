import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JFrame;

/**
 * This class is the frame of the game. It implements MouseListener and KeyListener
 * This class calls the background class because it draws the background as a MineSweeper 
 * mine field.
 * @author Jeffrey Martinez
 *
 */
public class UneFenetre extends JFrame implements MouseListener, KeyListener{
	/**
	 * Height and width of the window
	 */
	private static int width = 500;
	private static int height = 500;
	
	/**
	 * Background, the screen and the font 
	 */
	private Background background;
	private Screen screen;
	private Font font;
	
	/**
	 * The insets at the left and at the top
	 */
	private int insetLeft;
	private int insetTop;
	
	
	/**
	 * Main constructor of the class
	 * draws the frame and sets up the game 
	 */
	public UneFenetre(){
		super("MineSweeper!!"); // title of the window
		
		background = new Background();

		setResizable(false); // so it cant change sizes
		setDefaultCloseOperation(EXIT_ON_CLOSE); // so we can close the window by click x
		addMouseListener(this); 
		addKeyListener(this);
		
		screen = new Screen();
		add(screen);
		
		pack();
		insetLeft = getInsets().left;
		insetTop = getInsets().top;
		setSize(width + getInsets().left + getInsets().right, height + getInsets().bottom + getInsets().top);
		setLocationRelativeTo(null);
		setVisible(true);
		
		font = new Font("SansSerif", 0, 20); // font that will be used to announce things and the number of mines
		
	}
	
	
	public class Screen extends JPanel{
		public void paintComponent(Graphics pane) {
			pane.setFont(font); // sets the font 
			background.paint(pane); // paints the background
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		// if left click then run the background left click method
		if(e.getButton() == 1)
			background.leftClick(e.getX() - insetLeft, e.getY() - insetTop);
		// if right click then run the background right click method
		if(e.getButton() == 3)
			background.rightClick(e.getX() - insetLeft, e.getY() - insetTop);
		// repaint every time something is clicked
		screen.repaint();
	}
		@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		// if R is pressed
		if(e.getKeyCode() == KeyEvent.VK_R) {
			// completely reset the game 
			background.reset();
			// repaint
			screen.repaint();
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	// getter for the screen width
	public static int getScreenWidth() {
		return width;
	}
	
	// getter for the screen height
	public static int getScreenHeight() {
		return height;
	}

}
