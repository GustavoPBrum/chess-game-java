package boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		if(rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getrows() {
		return rows;
	}

	public int getcolumns() {
		return columns;
	}

	public Piece piece(int row, int column) {
		if(!positionExists(row, column)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[row][column];  // Retorna a peca respectiva daquela linha e coluna
	}
	
	// Sobrecarga de metodo
	public Piece piece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	// Metodo responsavel por colocar a peca em determinada posicao
	public void placePiece(Piece piece, Position position) {
		if(thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;  // Na matriz de peca, na determinada linha/coluna a peca do argumento sera atribuida
		piece.position = position; 
	}
	
	public Piece removePiece(Position position) {
		// Verificando se a posicao existe
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		if(piece(position) == null) {  // Se a peca na determinada posicao for nula
			return null;
		}
		Piece aux = piece(position);  // Recebe a peca que estiver no tabuleiro nesta posicao
		aux.position = null;  // Peca retirada do tabuleiro
		
		pieces[position.getRow()][position.getColumn()] = null;  // Peca retirada da matriz do tabuleiro
		return aux;
	}
	
	// Mais facil verificar pela linha e pela coluna, dentro desta classe
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsAPiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return piece(position) != null;  // Se a peca na posicao passada for diferente de nulo
	}
}




