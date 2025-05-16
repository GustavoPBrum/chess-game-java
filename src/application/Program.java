package application;

import chess.ChessMatch;

public class Program {

	public static void main(String[] args) {
		
		try {
			ChessMatch chessMatch =  new ChessMatch();
			
			UI.printBoard(chessMatch.getPieces());;  // Usa a matriz de pecas de xadrez
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		
	}

}
