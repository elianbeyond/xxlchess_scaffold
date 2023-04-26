package XXLChess;

import java.util.ArrayList;
import java.util.List;

public class BoardState {
    public Piece[][] pieces;

    public BoardState(Board board) {
        pieces = new Piece[14][14];
        for(int i=0;i<14;i++){
            for (int j = 0; j < 14; j++) {
                if(board.tiles[i][j].getPiece()!=null){
                    pieces[i][j]=board.tiles[i][j].getPiece();
                }else{
                    pieces[i][j]=null;
                }
            }

        }
    }
    public BoardState(BoardState boardState) {
        pieces = new Piece[14][14];
        for(int i=0;i<14;i++){
            for (int j = 0; j < 14; j++) {
                if(boardState.pieces[i][j]!=null){
                    this.pieces[i][j]=boardState.pieces[i][j];
                }else{
                    this.pieces[i][j]=null;
                }
            }

        }
    }

    public BoardState makeMove(Move move) {
        BoardState newBoardState = new BoardState(this);
        Piece piece =new Piece(move.getPiece().getPieceType(), move.getX(),move.getY(), move.getPiece().getWhite(), move.getPiece().getImg());

        newBoardState.pieces[piece.col][piece.row] =null;
        newBoardState.pieces[move.getX()][move.getY()] = piece;
        return newBoardState;

    }

    public Piece getPieceAt(int i, int j) {
        return pieces[i][j];
    }

    public List<Move> generateLegalMoves(boolean isMaximizingPlayer) {
        List<Move> legalMoves = new ArrayList<>();
        for(int i=0;i<14;i++){
            for (int j = 0; j < 14; j++) {
                if(pieces[i][j]!=null &&pieces[i][j].getWhite()==isMaximizingPlayer){
                    legalMoves.addAll(pieces[i][j].getValidMoves(this,i,j));
                }
            }
        }
        return legalMoves;
    }
}
