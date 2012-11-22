package sk.estesadohodneme.sturik.game;

import android.graphics.Bitmap;

/**
 * Parent of various image generator types.
 */
public abstract class ImageGenerator {
	
	/**
	 * Image size
	 */
	protected int mWidth = 0, mHeight = 0;
	
	/**
	 * Sets size of generated image.
	 * @param width
	 * @param height
	 */
	public void setImageSize(int width, int height){
		mWidth = width;
		mHeight = height;
	}
	
	/**
	 * @return width of generated image.
	 */
	public  int getImageWidth(){
		return mWidth;
	}
	
	/**
	 * @return height of generated image.
	 */
	public int getImageHeight(){
		return mHeight;
	}
	
	/**
	 * Generates image from given array.
	 * @param gameBoard returned by {@link Game}.doStep()
	 * @return
	 */
	public abstract Bitmap generate(short[][] gameBoard);
}
