package chess;

import boardgame.Board;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch { // Coracao do sistema de xadrez, onde ficara as regras do jogo

	private Board board;

	public ChessMatch() {
		board = new Board(8, 8);
		initialSetup();
	}
	
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getrows()][board.getcolumns()];
		for(int i = 0; i < board.getrows(); i++) {
			for(int j = 0; j < board.getcolumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);  //  Recebe cada peca do tabuleiro e faz o downcasting para peca de xadrez
			}
		}
		return mat;
	}
	
	// Instanciamos uma posicao, passando as coordenadas/posicoes do xadrez
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		// Primeiro instanciamos a posicao de xadrez, e apos utilizamos o metodo para muda-la para posicao de matriz
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	private void initialSetup() {
		placeNewPiece('b', 6, new Rook(board, Color.WHITE));
		placeNewPiece('e', 8, new King(board, Color.BLACK));
		placeNewPiece('e', 1, new King(board, Color.WHITE));
	}
}
