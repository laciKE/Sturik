package sk.estesadohodneme.sturik.game;

import sk.estesadohodneme.sturik.ApplicationResources;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Generates image of game board. Uses sprites of rooms with lights turned on
 * and off.
 */
public class ImageGeneratorMlyny extends ImageGenerator {

	private boolean isReady = false;
	private Bitmap[][] spritesOn = null;
	private Bitmap[][] spritesOff = null;
	private Config mConfig = null;
	private float mCellWidth, mCellHeight;

	/**
	 * Generates image from given array.
	 * 
	 * @param gameBoard
	 *            returned by {@link Game}.doStep()
	 * @return
	 */
	@Override
	public Bitmap generate(short[][] gameBoard) {
		int gameBoardHeight = gameBoard.length;
		int gameBoardWidth = gameBoard[0].length;
		if (!isReady) {
			init(gameBoardHeight, gameBoardWidth);
		}
		Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, mConfig);
		Canvas canvas = new Canvas(bitmap);
		for (int r = 0; r < gameBoardHeight; r++) {
			for (int c = 0; c < gameBoardWidth; c++) {
				if (gameBoard[r][c] == Game.COLOR_BLACK) {
					canvas.drawBitmap(spritesOff[r][c], c * mCellWidth, r
							* mCellHeight, null);
				} else {
					canvas.drawBitmap(spritesOn[r][c], c * mCellWidth, r
							* mCellHeight, null);
				}
			}
		}

		return bitmap;
	}

	/**
	 * Initializes internal state of {@link ImageGeneratorMlyny}: load images
	 * and divide them into sprites.
	 * 
	 * @param gameBoardHeight
	 * @param gameBoardWidth
	 */
	protected void init(int gameBoardHeight, int gameBoardWidth) {
		mCellWidth = ((float) mWidth) / gameBoardWidth;
		mCellHeight = ((float) mHeight) / gameBoardHeight;

		Resources resources = ApplicationResources.getResources();
		int resID;
		Bitmap sturakTmp, sturak;

		resID = resources.getIdentifier("sturak_on", "drawable",
				"sk.estesadohodneme.sturik");
		sturakTmp = BitmapFactory.decodeResource(resources, resID);
		sturak = Bitmap.createScaledBitmap(sturakTmp, mWidth, mHeight, false);
		mConfig = sturak.getConfig();

		spritesOn = new Bitmap[gameBoardHeight][gameBoardWidth];

		for (int r = 0; r < gameBoardHeight; r++) {
			for (int c = 0; c < gameBoardWidth; c++) {
				spritesOn[r][c] = Bitmap.createBitmap(sturak, (int) (c
						* mCellWidth + 1), (int) (r * mCellHeight + 1),
						(int) mCellWidth, (int) mCellHeight);
			}
		}

		resID = resources.getIdentifier("sturak_off", "drawable",
				"sk.estesadohodneme.sturik");
		sturakTmp = BitmapFactory.decodeResource(resources, resID);
		sturak = Bitmap.createScaledBitmap(sturakTmp, mWidth, mHeight, false);
		mConfig = sturak.getConfig();

		spritesOff = new Bitmap[gameBoardHeight][gameBoardWidth];

		for (int r = 0; r < gameBoardHeight; r++) {
			for (int c = 0; c < gameBoardWidth; c++) {
				spritesOff[r][c] = Bitmap.createBitmap(sturak, (int) (c
						* mCellWidth + 1), (int) (r * mCellHeight + 1),
						(int) mCellWidth, (int) mCellHeight);
			}
		}

		isReady = true;
	}
}
