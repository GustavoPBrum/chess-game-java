package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
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
	
	// Metodo de movimento da peca de xadrez
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		// Convertendo essas posicoes para posicoes da matriz
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
		// Temos que validar se a posicao inicial da peca existe
		validateSourcePosition(source);
		
		Piece capturedPiece = makeMove(source, target);  // A peca que foi removida sera a peca capturada
		return (ChessPiece)capturedPiece;
	}
	
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);  // Remove a peca da pos de origem
		Piece capturedPiece = board.removePiece(target);  // Removemos a possivel peca que esteja na pos de destino
		
		// Pegamos a peca p de origem e atribuimos a ela a posicao de destino
		board.placePiece(p, target);
		
		return capturedPiece;
	}
	
	private void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {  // Para caso nao exista uma peca nessa posicao
			throw new ChessException("There is no piece on source position");
		}
	}
	
	// Instanciamos uma posicao, passando as coordenadas/posicoes do xadrez
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		// Primeiro instanciamos a posicao de xadrez, e apos utilizamos o metodo para muda-la para posicao de matriz
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
}
