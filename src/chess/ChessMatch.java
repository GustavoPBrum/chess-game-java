package chess;

import boardgame.Board;

public class ChessMatch { // Coracao do sistema de xadrez, onde ficara as regras do jogo

	private Board board;

	public ChessMatch() {
		board = new Board(8, 8);
	}
	
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getrow()][board.getColumn()];
		for(int i = 0; i < board.getrow(); i++) {
			for(int j = 0; j < board.getColumn(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);  //  Recebe cada peca do tabuleiro e faz o downcasting para peca de xadrez
			}
		}
		return mat;
	}
}
