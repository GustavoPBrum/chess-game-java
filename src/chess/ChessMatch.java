package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch { // Coracao do sistema de xadrez, onde ficara as regras do jogo

	private int turn;
	private Color currentPlayer;
	private Board board;
	private boolean check;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
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
		
		if(testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can't put yourself in check");
		}
		
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		 
		// A peca de origem que esta no destino ja esta no tabuleiro, apenas retornamos a peca que foi removida pois atualizaremos o tabuleiro com ela
		// E na interface grafica podemos informar as pecas removidas de cada cor para o jogador

		nextTurn(); 
		
		return (ChessPiece)capturedPiece;
	}
	
	// Alem de mover a peca de origem para a peca de destino, tambem e capturar qualquer peca que estiver na posicao de destino
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);  // Remove a peca da pos de origem
		
		// Tenta remover a peca na pos de destino, se houver ela sera retornada e armazenada na variavel
		Piece capturedPiece = board.removePiece(target);  
		
		// Pegamos a peca p de origem e atribuimos a ela a posicao de destino
		board.placePiece(p, target);
		
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece); 
			capturedPieces.add(capturedPiece);  
		}
		
		return capturedPiece;
	}
	
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		// Desfazemos a jogada e peca capturada
		Piece p = board.removePiece(target);
		board.placePiece(p, source);
		
		if(capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
	}
	
	private void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {  // Para caso nao exista uma peca nessa posicao
			throw new ChessException("There is no piece on source position");
		} 
		if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece is not yours");
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
	
	private void nextTurn() {
		turn++;
		// Fazemos a operacao ternaria
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece king(Color color) {
		// Tem que ser a lista de pecas em jogo
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for(Piece p : list) {
			if(p instanceof King) {
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("There is no " + color + " king on the board"); 
	}
	
	private boolean testCheck(Color color) { 
		// Pegamos a posicao do Rei com king
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		
		for(Piece p : opponentPieces) {  // Varre as pecas adversarias
			boolean[][] mat = p.possibleMoves();  // Teste os movimentos possiveis de cada peca
			if(mat[kingPosition.getRow()][kingPosition.getColumn()]) {  // Se alguma coincidir com a pos do rei...
				return true;
			}
		}
		return false;
	}
	
	// Instanciamos uma posicao, passando as coordenadas/posicoes do xadrez
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		// Primeiro instanciamos a posicao de xadrez, e apos utilizamos o metodo para muda-la para posicao de matriz
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		
		// Adicionamos a lista de pecas no tabuleiro
		piecesOnTheBoard.add(piece);
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
