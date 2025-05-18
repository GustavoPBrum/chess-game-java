 package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();
		
		while(true) {
			try { 
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);
				
				System.out.println();
				System.out.print("Source: ");
				//Posicao de origem que o usuario quer mover
				ChessPosition source = UI.readChessPosition(sc);  // Posicao lida e instanciada indicada pelo usuario;
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);  // Sobrecarga onde imprime as pecas junto das posicoes possiveis coloridas
				
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
				
				// Movemos agora a peca no tabuleiro e guardamos a peca removida (caso tenha)
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);  
				
				if(capturedPiece != null) {
					captured.add(capturedPiece);
				}
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
