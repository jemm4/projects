import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class is to load the background image into the program to be used by the
 * program later on. 
 * frame.
 * @author Jeffrey Martinez
 *
 */
public class ImageLoad {
	/**
	 * Loads the images 
	 * @param path
	 * @return
	 */
	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(ImageLoad.class.getClassLoader().getResourceAsStream(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Scales the images to fit into the window
	 * @param source
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage scale(BufferedImage source, int width, int height) {
		BufferedImage scaled = new BufferedImage(width, height, source.getType());
		Graphics pane = scaled.getGraphics();
		pane.drawImage(source, 0, 0, width, height, null);
		pane.dispose();
		return scaled;
	}
}
