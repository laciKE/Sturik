package sk.estesadohodneme.sturik.game;

import java.util.Random;

import android.util.Log;

public class GameTetris extends Game {

	public static final int BOARD_WIDTH = 15;
	public static final int BOARD_HEIGHT= 13;
	public static final short BOARD_EMPTY = Game.COLOR_BLACK;
	public static final short BOARD_PIECE = Game.COLOR_YELLOW;
	public static final short BOARD_PILE  = Game.COLOR_GREEN;
	
	public static final int DESCENT_TIME = 3;
	
	protected short[][] mBoard = new short[BOARD_HEIGHT][BOARD_WIDTH];
	protected Random mRandom = new Random();
	
	protected boolean mIsFinished;
	protected int mPieceType,mPieceRotation,mPieceX,mPieceY;
	protected int mDescentTime;
	
	public boolean isFinished() {
		return mIsFinished;
	}
	public int getDefaultDelay() {
		return 150;
	}
	
	public static final short[][][][] mPieces =  //kind X rotation X 2D bitmap 
	{
	// Square
	  {
	   {
	    {0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0},
	    {0, 0, 2, 1, 0},
	    {0, 0, 1, 1, 0},
	    {0, 0, 0, 0, 0}
	    },
	   {
	    {0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0},
	    {0, 0, 2, 1, 0},
	    {0, 0, 1, 1, 0},
	    {0, 0, 0, 0, 0}
	    },
	   {
	    {0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0},
	    {0, 0, 2, 1, 0},
	    {0, 0, 1, 1, 0},
	    {0, 0, 0, 0, 0}
	    },
	   {
	    {0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0},
	    {0, 0, 2, 1, 0},
	    {0, 0, 1, 1, 0},
	    {0, 0, 0, 0, 0}
	    }
	   },	 
	// I
	  {
	   {
	    {0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0},
	    {0, 1, 2, 1, 1},
	    {0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0}
	    },
	   {
	    {0, 0, 0, 0, 0},
	    {0, 0, 1, 0, 0}, 
	    {0, 0, 2, 0, 0},
	    {0, 0, 1, 0, 0},
	    {0, 0, 1, 0, 0}
	    },
	   {
	    {0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0},
	    {1, 1, 2, 1, 0},
	    {0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0}
	    },
	   {
	    {0, 0, 1, 0, 0},
	    {0, 0, 1, 0, 0},
	    {0, 0, 2, 0, 0},
	    {0, 0, 1, 0, 0},
	    {0, 0, 0, 0, 0}
	    }
	   }
	  ,
	// L
	  {
	   {
	    {0, 0, 0, 0, 0},
	    {0, 0, 1, 0, 0},
	    {0, 0, 2, 0, 0},
	    {0, 0, 1, 1, 0},
	    {0, 0, 0, 0, 0}
	    },
	   {
	    {0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0},
	    {0, 1, 2, 1, 0},
	    {0, 1, 0, 0, 0},
	    {0, 0, 0, 0, 0}
	    },
	   {
	    {0, 0, 0, 0, 0},
	    {0, 1, 1, 0, 0},
	    {0, 0, 2, 0, 0},
	    {0, 0, 1, 0, 0},
	    {0, 0, 0, 0, 0}
	    },
	   {
	    {0, 0, 0, 0, 0},
	    {0, 0, 0, 1, 0},
	    {0, 1, 2, 1, 0},
	    {0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0}
	    }
	   },
	// L mirrored
	  {
	   {
	    {0, 0, 0, 0, 0},
	    {0, 0, 1, 0, 0},
	    {0, 0, 2, 0, 0},
	    {0, 1, 1, 0, 0},
	    {0, 0, 0, 0, 0}
	    },
	   {
	    {0, 0, 0, 0, 0},
	    {0, 1, 0, 0, 0},
	    {0, 1, 2, 1, 0},
	    {0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0}
	    },
	   {
	    {0, 0, 0, 0, 0},
	    {0, 0, 1, 1, 0},
	    {0, 0, 2, 0, 0},
	    {0, 0, 1, 0, 0},
	    {0, 0, 0, 0, 0}
	    },
	   {
	    {0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0},
	    {0, 1, 2, 1, 0},
	    {0, 0, 0, 1, 0},
	    {0, 0, 0, 0, 0}
	    }
	   },
	// N
	  {
	   {
	    {0, 0, 0, 0, 0},
	    {0, 0, 0, 1, 0},
	    {0, 0, 2, 1, 0},
	    {0, 0, 1, 0, 0},
	    {0, 0, 0, 0, 0}
	    },
	   {
	    {0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0},
	    {0, 1, 2, 0, 0},
	    {0, 0, 1, 1, 0},
	    {0, 0, 0, 0, 0}
	    },
	   {
	    {0, 0, 0, 0, 0},
	    {0, 0, 1, 0, 0},
	    {0, 1, 2, 0, 0},
	    {0, 1, 0, 0, 0},
	    {0, 0, 0, 0, 0}
	    },
	   {
	    {0, 0, 0, 0, 0},
	    {0, 1, 1, 0, 0},
	    {0, 0, 2, 1, 0},
	    {0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0}
	    }
	   },
	// N mirrored
	  {
	   {
	    {0, 0, 0, 0, 0},
	    {0, 0, 1, 0, 0},
	    {0, 0, 2, 1, 0},
	    {0, 0, 0, 1, 0},
	    {0, 0, 0, 0, 0}
	    },
	   {
	    {0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0},
	    {0, 0, 2, 1, 0},
	    {0, 1, 1, 0, 0},
	    {0, 0, 0, 0, 0}
	    },
	   {
	    {0, 0, 0, 0, 0},
	    {0, 1, 0, 0, 0},
	    {0, 1, 2, 0, 0},
	    {0, 0, 1, 0, 0},
	    {0, 0, 0, 0, 0}
	    },
	   {
	    {0, 0, 0, 0, 0},
	    {0, 0, 1, 1, 0},
	    {0, 1, 2, 0, 0},
	    {0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0}
	    }
	   },
	// T
	  {
	   {
	    {0, 0, 0, 0, 0},
	    {0, 0, 1, 0, 0},
	    {0, 0, 2, 1, 0},
	    {0, 0, 1, 0, 0},
	    {0, 0, 0, 0, 0}
	    },
	   {
	    {0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0},
	    {0, 1, 2, 1, 0},
	    {0, 0, 1, 0, 0},
	    {0, 0, 0, 0, 0}
	    },
	   {
	    {0, 0, 0, 0, 0},
	    {0, 0, 1, 0, 0},
	    {0, 1, 2, 0, 0},
	    {0, 0, 1, 0, 0},
	    {0, 0, 0, 0, 0}
	    },
	   {
	    {0, 0, 0, 0, 0},
	    {0, 0, 1, 0, 0},
	    {0, 1, 2, 1, 0},
	    {0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0}
	    }
	   }
	};
	public static final short[][]   mPiecesInitialPosition =
		{
		/* Square */
		  { -2,-2,-2,-2 },
		/* I */
		  { -2,-1,-2,-0 },
		/* L */
		  { -1,-2,-1,-1 },
		/* L mirrored */
		  { -1,-1,-1,-2 },
		/* N */
		  { -1,-2,-1,-1 },
		/* N mirrored */
		  { -1,-2,-1,-1 },
		/* T */
		  { -1,-2,-1,-1 },
		};
	
	protected void deleteLine(int y) {
	    for (int i=y;i>0;i--)
	    for (int j=0;j<BOARD_WIDTH;j++)
	    	mBoard[i][j] = mBoard[i-1][j];
	}
	protected void findAndDeleteFullLines() {
	    for(int i=0;i<BOARD_HEIGHT;i++) {
	    	int j=0;
	    	for(;j<BOARD_WIDTH;j++)
	    		if (mBoard[i][j] != BOARD_PILE)
	    			break;
	    	if (j == BOARD_WIDTH)
	    		deleteLine(i);
	    }
	}
	
	protected boolean isMoveAllowed(int type,int rot,int x,int y) {
		for(int i=0;i<5;i++)
		for(int j=0;j<5;j++) {
			
			if (mPieces[type][rot][i][j]>0) {
				//is it still on the board?
				if ((i+y<0)||(i+y>=BOARD_HEIGHT)||(j+x<0)||(j+x>=BOARD_WIDTH))
					return false;
				//is the position free?
				if (mBoard[i+y][j+x] == BOARD_PILE)
					return false;
			}
		}
	    return true;
	}
	
	protected void letPieceDown() {
		Log.d("TETRIS","before"+mPieceY);
		while (isMoveAllowed(mPieceType,mPieceRotation,mPieceX,mPieceY+1)) 
			mPieceY ++;
		Log.d("TETRIS","after"+mPieceY);
		clearPiece();
		storePiece(BOARD_PIECE);
	}
	
	protected void clearBoard() {
		for(int i=0;i<BOARD_HEIGHT;i++) 
		for(int j=0;j<BOARD_WIDTH;j++)
			mBoard[i][j] = BOARD_EMPTY;
	}
	protected void clearPiece() {
		for(int i=0;i<BOARD_HEIGHT;i++) 
		for(int j=0;j<BOARD_WIDTH;j++)
			if(mBoard[i][j] == BOARD_PIECE)
				mBoard[i][j] = BOARD_EMPTY;
	}
	protected void storePiece (short value) {
	    // Store each block of the piece into the board
	    for(int i=0;i<5;i++)
	    for(int j=0;j<5;j++)
	    	if (mPieces[mPieceType][mPieceRotation][i][j]>0)
	    		mBoard[i+mPieceY][j+mPieceX] = value;
	}
	
	protected boolean isGameOver() {
	    for (int i=0;i<BOARD_WIDTH;i++) 
	    	if (mBoard[i][0] == BOARD_PILE) return true;
	    return false;
	}
	
	protected void newPiece() {
		mPieceType     = mRandom.nextInt(7);
		mPieceRotation = mRandom.nextInt(4);
		mPieceX        = BOARD_WIDTH/2 - 2;
		mPieceY        = mPiecesInitialPosition[mPieceType][mPieceRotation];
		
		mDescentTime = 0;
		Log.d("TERIS",isMoveAllowed(mPieceType,mPieceRotation,mPieceX,mPieceY)+" "+mPieceType+" "+mPieceRotation+" "+mPieceX+" "+mPieceY);
		if (isMoveAllowed(mPieceType,mPieceRotation,mPieceX,mPieceY))
			storePiece(BOARD_PIECE);
		else 
			mIsFinished = true;
	}
	
	public GameTetris() {
		newPiece();
		mIsFinished = false;
		clearBoard();
	}
	
	@Override
	public short[][] doStep(UserAction userAction) {
		
		mDescentTime = (mDescentTime + 1) % DESCENT_TIME;
		int dx=0,dy=0,dr=0;
		if (userAction.isActionFire()) Log.d("TETRIS","FIREEEE");
		if (userAction.isActionFire()) letPieceDown();
		if (userAction.isActionDown()) dr = 3;
		if (userAction.isActionUp())   dr = 1;
		if (userAction.isActionLeft()) dx =-1;
		if (userAction.isActionRight())dx = 1;
		if (mDescentTime == 0)         dy = 1;
		
		if (isMoveAllowed(mPieceType,(mPieceRotation+dr)%4,mPieceX+dx,mPieceY+dy)) {
			mPieceRotation = (mPieceRotation+dr)%4;
			mPieceX += dx;
			mPieceY += dy;
			clearPiece();
			storePiece(BOARD_PIECE);
			return mBoard;
		}
		
		if(dy>0) {
			if (isMoveAllowed(mPieceType,mPieceRotation,mPieceX,mPieceY+dy)) {
				clearPiece();
				mPieceY += dy;
				storePiece(BOARD_PIECE);
			}
			else {
				storePiece(BOARD_PILE);
				findAndDeleteFullLines();
				newPiece();
			}
		}
		
		return mBoard;
	}
}
