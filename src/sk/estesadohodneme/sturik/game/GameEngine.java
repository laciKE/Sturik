package sk.estesadohodneme.sturik.game;

import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * Creates game engine, runs game logic in separate thread, generates image of
 * the game state based on given actions. Sets game properties and image
 * generator properties.
 */
public class GameEngine implements Runnable, Serializable {
	/**
	 * Implements {@link Serializable} due to possibility of putting in Bundle.
	 */
	private static final long serialVersionUID = -8474304921301340469L;

	/**
	 * User's actions constants.
	 */
	public static final int ACTION_UNDEFINED = -1;
	public static final int ACTION_LEFT = 1;
	public static final int ACTION_RIGHT = 2;
	public static final int ACTION_DOWN = 3;
	public static final int ACTION_UP = 4;

	/**
	 * Type of game constants.
	 */
	public static final int GAME_SNAKE = 1;
	public static final int GAME_TETRIS = 2;

	/**
	 * Type of image generator constants.
	 */
	public static final int IMAGE_GENERATOR_SIMPLE = 1;
	public static final int IMAGE_GENERATOR_MLYNY = 2;

	/**
	 * Fields for game state and properties.
	 */
	private volatile UserAction mUserAction = null;
	private volatile Game mGame = null;
	private volatile ImageGenerator mImageGenerator = null;
	private volatile Bitmap mGameBoard = null;
	private int mImageWidth = 0, mImageHeight = 0;
	private volatile Boolean mRunning = false;
	private volatile Integer mDelayInMiliseconds = 1000;

	/**
	 * Locks for synchronized access to shared fields.
	 */
	private final Lock mGameLock = new ReentrantLock();
	private final Lock mGameBoardLock = new ReentrantLock();
	private final Lock mUserActionLock = new ReentrantLock();
	private final Lock mImageGeneratorLock = new ReentrantLock();

	/**
	 * Creates game engine, initializes fields.
	 */
	public GameEngine() {
		mUserAction = new UserAction();
	}

	/**
	 * Set type of game: creates new instance of concrete game and holds
	 * reference to it.
	 * 
	 * @param gameType
	 *            should be one of GAME_SNAKE or GAME_TETRIS
	 * @throws IllegalStateException
	 *             when call this function while game is running
	 */
	// TODO uncomments available types of Game
	public void setGame(int gameType) throws IllegalStateException {
		synchronized (mRunning) {
			if (mRunning) {
				throw new IllegalStateException(
						"Game can not be changed while running.");
			}

			switch (gameType) {
			case GAME_SNAKE:
				mGame = new GameSnake();
				break;
			case GAME_TETRIS:
				// mGame = new GameTetris();
				break;
			default:
				mGame = null;
				break;
			}
		}
	}

	/**
	 * Set type of image generator: creates new instance of concrete image
	 * generator and holds reference to it.
	 * 
	 * @param imageGeneratorType
	 *            should be one of IMAGE_GENERATOR_SIMPLE or
	 *            IMAGE_GENERATOR_MLYNY
	 */
	// TODO uncomments available types of ImageGenerator
	public void setImageGenerator(int imageGeneratorType) {
		mImageGeneratorLock.lock();
		try {

			switch (imageGeneratorType) {
			case IMAGE_GENERATOR_SIMPLE:
				// mImageGenerator = new ImageGeneratorSimple();
				break;
			case IMAGE_GENERATOR_MLYNY:
				// mImageGenerator = new ImageGeneratorMlyny();
				break;
			default:
				mImageGenerator = null;
				break;
			}
		} finally {
			mImageGeneratorLock.unlock();
		}
	}

	/**
	 * Sets size of image generated by {@link ImageGenerator}.
	 * 
	 * @param width
	 *            width of image
	 * @param height
	 *            height of image
	 * @throws NullPointerException
	 *             if image generator is not set, throws
	 *             {@link NullPointerException}
	 */
	public void setImageSize(int width, int height) throws NullPointerException {
		mImageWidth = width;
		mImageHeight = height;
		mImageGeneratorLock.lock();
		try {
			mImageGenerator.setImageSize(mImageWidth, mImageHeight);
		} finally {
			mImageGeneratorLock.unlock();
		}
	}

	/**
	 * Sets delay between game steps.
	 * 
	 * @param miliseconds
	 *            delay between game steps
	 */
	public void setDelay(int miliseconds) {
		synchronized (mDelayInMiliseconds) {
			mDelayInMiliseconds = miliseconds;
		}
	}

	/**
	 * Sets user action.
	 * 
	 * @param userAction
	 *            should be one of ACTION_LEFT, ACTION_RIGHT, ACTION_UP,
	 *            ACTION_DOWN
	 */
	public void setUserAction(int userAction) {
		mUserActionLock.lock();
		try {
			switch (userAction) {
			case ACTION_LEFT:
				mUserAction.setAction(UserAction.ACTION_LEFT);
				break;
			case ACTION_RIGHT:
				mUserAction.setAction(UserAction.ACTION_RIGHT);
				break;
			case ACTION_DOWN:
				mUserAction.setAction(UserAction.ACTION_DOWN);
				break;
			case ACTION_UP:
				mUserAction.setAction(UserAction.ACTION_UP);
				break;
			default:
				mUserAction.setAction(UserAction.ACTION_UNDEFINED);
				break;
			}
		} finally {
			mUserActionLock.unlock();
		}
	}

	/**
	 * Returns delay between game steps.
	 * 
	 * @return delay between game steps in miliseconds
	 */
	public int getDelay() {
		synchronized (mDelayInMiliseconds) {
			return mDelayInMiliseconds;
		}
	}

	/**
	 * Returns game board's bitmap.
	 * 
	 * @return copy of internal game board
	 */
	public Bitmap getGameBoard() {
		mGameBoardLock.lock();
		Bitmap bitmap = null;
		try {
			bitmap = mGameBoard.copy(mGameBoard.getConfig(), false);
		} catch (NullPointerException e) {
			// mGameBoardLock is not set
		} finally {
			mGameBoardLock.unlock();
		}

		return bitmap;
	}

	/**
	 * Starts the game in new {@link Thread}.
	 * 
	 * @return if game starts, return true, false otherwise.
	 */
	// TODO uncomment null pointer checks
	public boolean start() {
		synchronized (mRunning) {
			mGameLock.lock();
			mImageGeneratorLock.lock();
			try {
				if ((mRunning) || (mGame == null) /*
												 * || (mImageGenerator == null)
												 */) {
					return false;
				}
			} finally {
				mImageGeneratorLock.unlock();
				mGameLock.unlock();
			}

			mRunning = true;
		}

		new Thread(this).start();
		Log.d("STURIK", "GameEngine start");

		return true;
	}

	/**
	 * Stops the game.
	 */
	public void stop() {
		Log.d("STURIK", "GameEngine will stop");
		synchronized (mRunning) {
			mRunning = false;
		}
		Log.d("STURIK", "GameEngine stop");
	}

	/**
	 * Runnable for run game.
	 */
	// TODO uncomment parts with mGame and mImageGenerator
	@Override
	public void run() {
		boolean running;
		synchronized (mRunning) {
			running = mRunning;
		}
		int counter = 0;
		short[][] rawGameBoard;
		while (running) {
			Log.d("STURIK", "running " + (counter++));
			mUserActionLock.lock();
			mGameLock.lock();
			try {
				if (mUserAction.isActionLeft())
					Log.d("STURIK", "left");
				if (mUserAction.isActionRight())
					Log.d("STURIK", "right");
				if (mUserAction.isActionUp())
					Log.d("STURIK", "up");
				if (mUserAction.isActionDown())
					Log.d("STURIK", "down");
				rawGameBoard = mGame.doStep(mUserAction);
				mUserAction.clear();
			} finally {
				mGameLock.unlock();
				mUserActionLock.unlock();
			}
			mImageGeneratorLock.lock();
			mGameBoardLock.lock();
			try {
				// mGameBoard = mImageGenerator.generate(rawGameBoard);
			} finally {
				mGameBoardLock.unlock();
				mImageGeneratorLock.unlock();
			}
			int delayInMiliseconds;
			synchronized (mDelayInMiliseconds) {
				delayInMiliseconds = mDelayInMiliseconds;
			}
			try {
				Thread.sleep(delayInMiliseconds);
			} catch (InterruptedException e) {
				synchronized (mRunning) {
					mRunning = false;
				} // finish thread
			}
			synchronized (mRunning) {
				running = mRunning;
			}
		}
	}

}