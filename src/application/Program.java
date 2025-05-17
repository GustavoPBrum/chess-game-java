package application;

import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		ChessMatch chessMatch = new ChessMatch();
		
		while(true) {
			UI.printBoard(chessMatch.getPieces());
			
			System.out.println();
			System.out.print("Source: ");
			//Posicao de origem que o usuario quer mover
			ChessPosition source = UI.readChessPosition(sc);  // Posicao lida e instanciada indicada pelo usuario;
			
			System.out.println();
			System.out.print("Target: ");
			ChessPosition target = UI.readChessPosition(sc);
			
			// Movendo agora a peca
			ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
		}

	}

}
