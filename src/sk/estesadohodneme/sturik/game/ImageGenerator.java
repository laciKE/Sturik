package sk.estesadohodneme.sturik.game;

import java.util.ArrayList;

/**
 * Parent of various image generator types.
 */
public abstract class ImageGenerator {

	/**
	 * Sets size of generated image.
	 * @param width
	 * @param height
	 */
	public abstract void setImageSize(int width, int height);
	
	/**
	 * @return width of generated image.
	 */
	public abstract int getImageWidth();
	
	/**
	 * @return height of generated image.
	 */
	public abstract int getImageHeight();
	
//	/**
//	 * Generates image from given array.
//	 * @param gameBoard returned by {@link Game}.doStep()
//	 * @return
//	 */
//	// TODO use suitable Image type for both Android and Swing.
//	public abstract Image generate(ArrayList<Short> gameBoard);
}
