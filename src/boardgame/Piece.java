package boardgame;

public abstract class Piece {
	
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
	
	public abstract boolean[][] possibleMoves();  // Metodo que sera implementado por uma subclasse concreta da classe piece
	
	public boolean possibleMove(Position position) {
		
		// Chama uma possivel implementacao de uma subclasse concreta da classe *Piece*
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();
		
		for(int i = 0; i < mat.length; i ++) {
			for(int j = 0; j < mat.length; j ++) {
				if(mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}
