package sk.estesadohodneme.sturik.game;

/**
 * Creates objects for store last user action.
 */
public class UserAction {
	/**
	 * User's actions constants.
	 */
	public static final byte ACTION_UNDEFINED = 0;
	public static final byte ACTION_LEFT = 1;
	public static final byte ACTION_RIGHT = 2;
	public static final byte ACTION_DOWN = 4;
	public static final byte ACTION_UP = 8;

	protected byte mAction = ACTION_UNDEFINED;

	/**
	 * Clears user action; becomes ready for new turn.
	 */
	public void clear() {
		mAction = ACTION_UNDEFINED;
	}

	/**
	 * Sets user action.
	 * 
	 * @param action
	 *            should be one of ACTION_LEFT, ACTION_RIGHT, ACTION_DOWN or
	 *            ACTION_UP
	 */
	public void setAction(byte action) {
		mAction = action;
	}

	/**
	 * @return true if ACTION_LEFT has been set, false otherwise.
	 */
	public boolean isActionLeft() {
		return ((mAction & ACTION_LEFT) > 0) ? true : false;
	}

	/**
	 * @return true if ACTION_RIGHT has been set, false otherwise.
	 */
	public boolean isActionRight() {
		return ((mAction & ACTION_RIGHT) > 0) ? true : false;
	}

	/**
	 * @return true if ACTION_UP has been set, false otherwise.
	 */
	public boolean isActionUp() {
		return ((mAction & ACTION_UP) > 0) ? true : false;
	}

	/**
	 * @return true if ACTION_DOWN has been set, false otherwise.
	 */
	public boolean isActionDown() {
		return ((mAction & ACTION_DOWN) > 0) ? true : false;
	}

}
