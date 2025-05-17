package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		ChessMatch chessMatch = new ChessMatch();
		
		while(true) {
			try { 
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces());
				
				System.out.println();
				System.out.print("Source: ");
				//Posicao de origem que o usuario quer mover
				ChessPosition source = UI.readChessPosition(sc);  // Posicao lida e instanciada indicada pelo usuario;
				
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
				
				// Movemos agora a peca no tabuleiro e guardamos a peca removida (caso tenha)
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);  
			}
			catch (ChessException e ) {
				System.out.println(e.getMessage());
				sc.nextLine();
				sc.nextLine();
			}
			catch (InputMismatchException e ) {
				System.out.println(e.getMessage());
				sc.nextLine();
				sc.nextLine();
			}
		}

	}

}
