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
	
	// Este metodo serve para que possamos imprimir na aplicacao as posicoes *possiveis* apartir da posicao de **origem**
	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();  // Convertemos  de uma pos de xadrez para de matriz normal
		validateSourcePosition(position);
		// Movimentos possiveis da peca, nesta posicao
		return board.piece(position).possibleMoves();
	}
	
	// Metodo de movimento da peca de xadrez
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		// Convertendo essas posicoes para posicoes da matriz
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
		// Temos que validar se a posicao inicial da peca existe
		validateSourcePosition(source);
		
		validateTargetPosition(source, target);
		
		Piece capturedPiece = makeMove(source, target);  // A peca que foi removida sera a peca capturada e armazenada na variavel
		
		// A peca de origem que esta no destino ja esta no tabuleiro, apenas retornamos a peca que foi removida pois atualizaremos o tabuleiro com ela
		// E na interface grafica podemos informar as pecas removidas de cada cor para o jogador
		return (ChessPiece)capturedPiece;
	}
	
	// Alem de mover a peca de origem para a peca de destino, tambem e capturar qualquer peca que estiver na posicao de destino
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);  // Remove a peca da pos de origem
		
		// Tenta remover a peca na pos de destino, se houver ela sera retornada e armazenada na variavel
		Piece capturedPiece = board.removePiece(target);  
		
		// Pegamos a peca p de origem e atribuimos a ela a posicao de destino
		board.placePiece(p, target);
		
		return capturedPiece;
	}
	
	private void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {  // Para caso nao exista uma peca nessa posicao
			throw new ChessException("There is no piece on source position");
		} 
		if(!board.piece(position).isThereAnyPossibleMove()) {  // Se nao existir nenhum movimento possivel
			throw new ChessException("There is no possible moves for the chosen piece");
		}
	}
	
	private void validateTargetPosition (Position source, Position target) {
		// Se a peca na posicao de orige nao conseguir mover para a posicao de destino...
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("The Chosen piece can't move to target position");
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
