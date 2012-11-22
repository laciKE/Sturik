package sk.estesadohodneme.sturik.game;

/**
 * Parent of various game types.
 */
public abstract class Game {
	
	/**
	 * Colors for description of game board.
	 */
	public static final int COLOR_BLACK = 0;
	public static final int COLOR_GREEN = 1;
	public static final int COLOR_YELLOW  = 2;
	public static final int COLOR_RED  = 3;
	public static final int COLOR_BLUE = 4;
	public static final int COLOR_WHITE = 5;

	/**
	 * 
	 * @param userAction defines user action
	 * @return short[][] with description of game board.
	 */
	public abstract short[][] doStep(UserAction userAction);
	public abstract boolean isFinished();
}
