package sk.estesadohodneme.sturik.game;

import java.util.ArrayList;

/**
 * Parent of various game types.
 */
public abstract class Game {

	/**
	 * 
	 * @param userAction defines user action
	 * @return ArrayList<Short> with description of game board.
	 */
	public abstract ArrayList<Short> doStep(UserAction userAction);
}
