package boardgame;

public class Piece {
	
	// Protected pois queremos que essa posicao seja da matriz de tabuleiro
	protected Position position;
	
	private Board board;

	public Piece(Board board) {
		super();
		this.board = board;
	}

	protected Board getBoard() {  
		return board;
	}	
}
