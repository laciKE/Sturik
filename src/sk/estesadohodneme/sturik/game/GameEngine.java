package sk.estesadohodneme.sturik.game;

/**
 * Creates game engine, runs game logic in separate thread, generates image of
 * the game state based on given actions. Sets game properties and image
 * generator properties.
 */
public class GameEngine {
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

	
	protected UserAction mUserAction;
}
