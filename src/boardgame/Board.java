package boardgame;

public class Board {

	private int rows;
	private int column;
	private Piece[][] pieces;
	
	public Board(int rows, int column) {
		super();
		this.rows = rows;
		this.column = column;
		pieces = new Piece[rows][column];
	}
	
	
}
