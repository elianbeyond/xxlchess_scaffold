package XXLChess;

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
}
