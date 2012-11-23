package sk.estesadohodneme.sturik.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Generates simple image of game board. Uses only simple color rectangles. 
 */
public class ImageGeneratorSimple extends ImageGenerator {

	Paint mPaint = new Paint();
	
	/**
	 * Generates image from given array.
	 * @param gameBoard returned by {@link Game}.doStep()
	 * @return
	 */
	@Override
	public Bitmap generate(short[][] gameBoard) {
		Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight,
				Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		
		mPaint.setColor(Color.WHITE);
		mPaint.setStrokeWidth(0);

		canvas.drawRect(0, 0, mWidth, mHeight, mPaint);
		
		int gameBoardHeight = gameBoard.length;
		int gameBoardWidth = gameBoard[0].length;
		float cellWidth = ((float) mWidth) / gameBoardWidth;
		float cellHeight = ((float) mHeight) / gameBoardHeight;

		for (int r = 0; r < gameBoardHeight; r++) {
			for (int c = 0; c < gameBoardWidth; c++) {
				switch (gameBoard[r][c]) {
				case Game.COLOR_BLACK:
					mPaint.setColor(Color.BLACK);
					break;
				case Game.COLOR_RED:
					mPaint.setColor(Color.RED);
					break;
				case Game.COLOR_GREEN:
					mPaint.setColor(Color.GREEN);
					break;
				case Game.COLOR_YELLOW:
					mPaint.setColor(Color.YELLOW);
					break;
				case Game.COLOR_BLUE:
					mPaint.setColor(Color.BLUE);
					break;
				default:
					mPaint.setColor(Color.BLACK);
					break;
				}
				canvas.drawRect(c * cellWidth + 1, r * cellHeight + 1, (c + 1)
						* cellWidth - 1, (r + 1) * cellHeight - 1, mPaint);
			}
		}

		return bitmap;
	}
}
