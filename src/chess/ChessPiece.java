package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {

	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	protected boolean isOpponentPiece (Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);  // 'p' recebe a peca na determinada posicao
		
		// Retorna true apenas se a peca for diferente de nulo e se a cor for diferente da peca atual.
		return p != null &&  p.getColor() != color;
	}
}
