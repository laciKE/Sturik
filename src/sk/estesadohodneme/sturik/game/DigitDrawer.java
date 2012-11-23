package sk.estesadohodneme.sturik.game;

public class DigitDrawer {

	protected static short[][] digitBitmap(int n) {
		short k = 1;
			
		if (n == 1) {
			short bitmap[][] = {
				      { 0, k, 0 },
				      { 0, k, 0 },
				      { 0, k, 0 },
				      { 0, k, 0 },
				      { 0, k, 0 }
				    };
			return bitmap;
		}
		if (n == 2) {
			short bitmap[][] = {
				      { k, k, k },
				      { 0, 0, k },
				      { k, k, k },
				      { k, 0, 0 },
				      { k, k, k }
				    };
			return bitmap;
		}
		if (n == 3) {
			short bitmap[][] = {
				      { k, k, k },
				      { 0, 0, k },
				      { k, k, k },
				      { 0, 0, k },
				      { k, k, k }
				    };
			return bitmap;
		}
		if (n == 4) {
			short bitmap[][] = {
				      { k, 0, 0 },
				      { k, 0, 0 },
				      { k, k, k },
				      { 0, k, 0 },
				      { 0, k, 0 }
				    };
			return bitmap;
		}
		if (n == 5) {
			short bitmap[][] = {
				      { k, k, k },
				      { k, 0, 0 },
				      { k, k, k },
				      { 0, 0, k },
				      { k, k, k }
				    };
			return bitmap;
		}
		if (n == 6) {
			short bitmap[][] = {
				      { k, k, k },
				      { k, 0, 0 },
				      { k, k, k },
				      { k, 0, k },
				      { k, k, k }
				    };
			return bitmap;
		}
		if (n == 7) {
			short bitmap[][] = {
				      { k, k, k },
				      { 0, 0, k },
				      { 0, k, 0 },
				      { k, 0, 0 },
				      { k, 0, 0 }
				    };
			return bitmap;
		}
		if (n == 8) {
			short bitmap[][] = {
				      { k, k, k },
				      { k, 0, k },
				      { k, k, k },
				      { k, 0, k },
				      { k, k, k }
				    };
			return bitmap;
		}
		if (n == 9) {
			short bitmap[][] = {
				      { k, k, k },
				      { k, 0, k },
				      { k, k, k },
				      { 0, 0, k },
				      { k, k, k }
				    };
			return bitmap;
		}
		if (n == 0) {
			short bitmap[][] = {
				      { k, k, k },
				      { k, 0, k },
				      { k, 0, k },
				      { k, 0, k },
				      { k, k, k }
				    };
			return bitmap;
		}
		short bitmap[][] = {
			      { k, k, k },
			      { 0, k, 0 },
			      { k, 0, k },
			      { 0, k, 0 },
			      { k, k, k }
			    };
		return bitmap;
	}
	
	protected static short[][] showDigit(int number,short color,int dx,short[][] board) {
		int sx = board[0].length/2 + dx - 1;
		int sy = board.length/2 - 3;
		
		short[][] bitmap = digitBitmap(number);
		for(int i=0;i<5;i++)
		for(int j=0;j<3;j++)
			board[i+sy][j+sx] = (short) (bitmap[i][j] * color);
		
		return board;
	}
	
	protected static short[][] showNumberAtBoard(int number,short color,short[][] board) {
		if (number<10) {
			board = showDigit(number,color,0,board);
			return board;
		}
		if (number<100) {
			board = showDigit(number/10,color,-2,board);
			board = showDigit(number%10,color, 2,board);
			return board;
		}
		
		board = showDigit(number/100,color,-4,board);
		board = showDigit((number/10)%10,color,0,board);
		board = showDigit(number%10,color,4,board);
		
		return board;
	}
}
