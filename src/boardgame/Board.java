package boardgame;

public class Board {

	private int row;
	private int column;
	private Piece[][] pieces;
	
	public Board(int row, int column) {
		super();
		this.row = row;
		this.column = column;
		pieces = new Piece[row][column];
	}

	public int getrow() {
		return row;
	}

	public void setrow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	public Piece piece(int row, int column) {
		return pieces[row][column];  // Retorna a peca respectiva daquela linha e coluna
	}
	
	// Sobrecarga de metodo
	public Piece piece(Position position) {
		return pieces[position.getRow()][position.getColumn()];
	}
	
	// Metodo responsavel por colocar a peca em determinada posicao
	public void placePiece(Piece piece, Position position) {
		pieces[position.getRow()][position.getColumn()] = piece;  // Na matriz de peca, na determinada linha/coluna a peca do argumento sera atribuida
		piece.position = position; 
	}
	
}




