package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getrows()][getBoard().getcolumns()];

		Position p = new Position(0, 0); // Valor inicial

		// Acima 
		// 'p' -> variavel posicao p criada para auxiliar
		// position -> posicao atual da nossa torre que usamos para setar a variavel auxiliar
		p.setValues(position.getRow() - 1, position.getColumn());  // Usando a posicao atual da torre para setar os valores de posicao da 'Position p'

		// Enquanto existir posicao e nao for uma peca, recebera true (isto para subir)
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() - 1);
		}
		if (getBoard().positionExists(p) && isOpponentPiece(p)) { // Se houver uma posicao e for uma peca oponente
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// Esquerda
		p.setValues(position.getRow(), position.getColumn() - 1);

		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}
		if (getBoard().positionExists(p) && isOpponentPiece(p)) { // Se houver uma posicao e for uma peca oponente
			mat[p.getRow()][p.getColumn()] = true;
		} 

		// Direita
		p.setValues(position.getRow(), position.getColumn() + 1);

		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}
		if (getBoard().positionExists(p) && isOpponentPiece(p)) { // Se houver uma posicao e for uma peca oponente
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// Abaixo
		p.setValues(position.getRow() + 1, position.getColumn());

		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);
		}
		if (getBoard().positionExists(p) && isOpponentPiece(p)) { // Se houver uma posicao e for uma peca oponente
			mat[p.getRow()][p.getColumn()] = true;
		}

		return mat;
	}
}
