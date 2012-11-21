package sk.estesadohodneme.sturik.game;

/**
 * Parent of various game types.
 */
public abstract class Game {

	/**
	 * 
	 * @param userAction defines user action
	 * @return ArrayList<Short> with description of game board.
	 */
	public abstract short[][] doStep(UserAction userAction);
	public abstract boolean isFinished();
}
