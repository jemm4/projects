import java.awt.*;
/**
 * This class will keep track of the score among the two cards.
 * Each side will increase according to the game's rules.
 * @author Jeffrey Martinez
 *
 */
public class BarGraph {
	private int x = 310, y = 305;
	private final int WIDTH = 15, HEIGHT = 35;
	private int score;
	
	/**
	 * main constructor
	 * @param game
	 */
	public BarGraph(int game){
		score = game;
	}
	/**
	 * Draws the block for the graph depending if the score is positive or negative
	 * @param gGraph
	 */
	public void drawGraph(Graphics gGraph){
		gGraph.setColor(Color.WHITE);
		gGraph.drawLine(x, y - 35, x, y + 75);

		if (score > 0)
		{
			for (int i = 0; i < score; i++)
			{
				gGraph.setColor(Color.GREEN);
				gGraph.fillRect((x) + (i * WIDTH), y, WIDTH, HEIGHT);
				gGraph.setColor(Color.BLACK);
				gGraph.drawRect((x) + (i * WIDTH), y, WIDTH, HEIGHT);
			}
		}

		else if (score < 0)
		{
			for (int i = -1; i >= score; i--)
			{
				gGraph.setColor(Color.RED);
				gGraph.fillRect(x + (i * WIDTH), y, WIDTH, HEIGHT);
				gGraph.setColor(Color.BLACK);
				gGraph.drawRect(x + (i * WIDTH), y, WIDTH, HEIGHT);
			}
		}
	}
	
}
