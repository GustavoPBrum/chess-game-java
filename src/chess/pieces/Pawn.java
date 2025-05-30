package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getrows()][getBoard().getcolumns()];

		Position p = new Position(0, 0); // Valor inicial

		if (getColor() == Color.WHITE) {
			p.setValues(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() - 2, position.getColumn());
			// Para pular duas casas na primeira vez, a primeira tem que estar livre
			Position p2 = new Position(position.getRow() - 1, position.getColumn());

			// Se pode mover para duas casas no primeiro movimento
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2)
					&& !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			// Diagonal esquerda
			p.setValues(position.getRow() - 1, position.getColumn() - 1);
			if (getBoard().positionExists(p) && isOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			// Diagonal Direita
			p.setValues(position.getRow() - 1, position.getColumn() + 1);
			if (getBoard().positionExists(p) && isOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			// #specialmove en passant white
			if (position.getRow() == 3) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				// Confirma se existe a posicao, se eh oponente, e se a peca que estiver no
				// tabuleiro eh um enPassantVunerable
				if (getBoard().positionExists(left) && isOpponentPiece(left)
						&& getBoard().piece(left) == chessMatch.getEnPassantVunerable()) {
					mat[left.getRow() - 1][left.getColumn()] = true;
				}
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				if (getBoard().positionExists(right) && isOpponentPiece(right)
						&& getBoard().piece(right) == chessMatch.getEnPassantVunerable()) {
					mat[right.getRow() - 1][right.getColumn()] = true;
				}
			}
		} else {
			p.setValues(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() + 2, position.getColumn());
			// Para pular duas casas na primeira vez, a primeira tem que estar livre
			Position p2 = new Position(position.getRow() + 1, position.getColumn());

			// Se pode mover para duas casas no primeiro movimento
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2)
					&& !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			// Diagonal esquerda
			p.setValues(position.getRow() + 1, position.getColumn() - 1);
			if (getBoard().positionExists(p) && isOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			// Diagonal Direita
			p.setValues(position.getRow() + 1, position.getColumn() + 1);
			if (getBoard().positionExists(p) && isOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			// #specialmove en passant black
			if (position.getRow() == 4) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if (getBoard().positionExists(left) && isOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVunerable()) {
					mat[left.getRow() + 1][left.getColumn()] = true;
				}
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				if (getBoard().positionExists(right) && isOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVunerable()) {
					mat[right.getRow() + 1][right.getColumn()] = true;
				}
			}
		}

		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}
}
