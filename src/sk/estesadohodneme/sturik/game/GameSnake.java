package sk.estesadohodneme.sturik.game;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class GameSnake extends Game {

	public static final int BOARD_WIDTH = 15;
	public static final int BOARD_HEIGHT= 13;
	public static final short BOARD_EMPTY = Game.COLOR_BLACK;
	public static final short BOARD_SNAKE = Game.COLOR_GREEN;
	public static final short BOARD_HEAD  = Game.COLOR_YELLOW;
	public static final short BOARD_FOOD  = Game.COLOR_RED;
	public static final short BOARD_SCORE = Game.COLOR_BLUE;
	public static final short BOARD_ROUND = Game.COLOR_RED;
	public static final int SNAKE_LIFES = 10;
	public static final int SCORE_DURATION = 5;
	
	protected Queue<Integer> mSnake;
	protected short[][] mBoard = new short[BOARD_HEIGHT][BOARD_WIDTH];
	protected Random mRandom = new Random();
	
	protected int mShowScore;
	protected int mShowRound;
	protected int mSnakeHead;
	protected int mSnakeStock;
	protected int mSnakeScore;
	protected int mSnakeVX,mSnakeVY;
	protected int mKilledSnakes;
	protected boolean mIsFinished;
	
	public boolean isFinished() {
		return mIsFinished;
	}
	
	public int getDefaultDelay() {
		return 200;
	}
	
	protected boolean isEmpty(int i) {
		if (mBoard[i/BOARD_WIDTH][i%BOARD_WIDTH] == BOARD_EMPTY) return true;
		return false;
	}
	
	protected int findEmptyPosition() {
		int rnd = mRandom.nextInt(BOARD_HEIGHT*BOARD_WIDTH);
		if (!isEmpty(rnd)) rnd = findEmptyPosition();
		return(rnd);
	}
	
	protected void boardAddFood() {
		int pos = findEmptyPosition();
		mBoard[pos/BOARD_WIDTH][pos%BOARD_WIDTH] = BOARD_FOOD;
	}
	
	protected void killSnake() {
		/*
		while(!mSnake.isEmpty()) {
			int p = mSnake.remove();
			mBoard[p/BOARD_WIDTH][p%BOARD_WIDTH] = BOARD_EMPTY;
		}*/
		clearBoard();
		mSnake.clear();
		
		mShowScore = SCORE_DURATION;
		
		mKilledSnakes++;
		if (mKilledSnakes == SNAKE_LIFES) mIsFinished = true;
		//newSnake();
	}
	
	protected void newSnake() {
		mSnakeHead = findEmptyPosition();
		mSnake.add(mSnakeHead);
		mBoard[mSnakeHead/BOARD_WIDTH][mSnakeHead%BOARD_WIDTH] = BOARD_HEAD;
		mSnakeVX = 0;
		mSnakeVY = 0;
		
		mSnakeScore = 0;
		mSnakeStock = 2;
	}

	protected void clearBoard() {
		for(int i=0;i<BOARD_HEIGHT;i++) 
		for(int j=0;j<BOARD_WIDTH;j++)
			mBoard[i][j] = BOARD_EMPTY;
	}
	
	public GameSnake() {
		mSnake = new LinkedBlockingQueue<Integer>();
		
		mIsFinished = false;
		mKilledSnakes = 0;
		mShowScore = 0;
		clearBoard();
		boardAddFood();
		newSnake();
	}
	
	protected void updateDirection(UserAction userAction) {
		if (userAction.isActionLeft()&&(mSnakeVX==0)) {
				mSnakeVX=-1;
				mSnakeVY= 0;
			}
		if (userAction.isActionRight()&&(mSnakeVX==0)) {
			mSnakeVX= 1;
			mSnakeVY= 0;
		}
		if (userAction.isActionUp()&&(mSnakeVY==0)) {
			mSnakeVX= 0;
			mSnakeVY=-1;
		}
		if (userAction.isActionDown()&&(mSnakeVY==0)) {
			mSnakeVX= 0;
			mSnakeVY= 1;
		}
	}
	
	protected void showScore() {
		mShowScore--;
		if (mShowScore == 0) {
			clearBoard();
			mShowRound = SCORE_DURATION;
		}
		else
			mBoard = DigitDrawer.showNumberAtBoard(mSnakeScore,BOARD_SCORE,mBoard);
	}
	
	protected void showRound() {
		mShowRound--;
		if (mShowRound == 0) {
			clearBoard();
			newSnake();
			boardAddFood();
		}
		else
			mBoard = DigitDrawer.showNumberAtBoard(mKilledSnakes,BOARD_ROUND,mBoard);
	}
	
	@Override
	public short[][] doStep(UserAction userAction) {
		if (mShowScore > 0) { 
			showScore();
			return mBoard;
		}
		if (mShowRound > 0) {
			showRound();
			return mBoard;
		}
		
		updateDirection(userAction);		
		if ((mSnakeVX == 0)&&(mSnakeVY == 0))
			return mBoard;
		
		int newX = mSnakeHead%BOARD_WIDTH;
		int newY = mSnakeHead/BOARD_WIDTH;
		mBoard[newY][newX] = BOARD_SNAKE;
		newX += mSnakeVX;
		newY += mSnakeVY;
		
		if ((newX<0)||(newX>=BOARD_WIDTH)||(newY<0)||(newY>=BOARD_HEIGHT)) {
			killSnake();
			return mBoard;
		}
		
		boolean noFoodAtBoard = false;
		if (mBoard[newY][newX] == BOARD_FOOD) {
			mSnakeStock+=3;
			noFoodAtBoard = true;
			mSnakeScore++;
		}
		else if (mBoard[newY][newX] != BOARD_EMPTY) {
			killSnake();
			return mBoard;
		}
		
		
		if (mSnakeStock>0) 
			mSnakeStock--;
		else {
			int old = mSnake.remove();
			mBoard[old/BOARD_WIDTH][old%BOARD_WIDTH] = BOARD_EMPTY;
		}
		
		mSnakeHead = newY*BOARD_WIDTH + newX;
		mSnake.add(mSnakeHead);
		mBoard[newY][newX] = BOARD_HEAD;
		if (noFoodAtBoard) boardAddFood();
		
		return mBoard;
	}

}
