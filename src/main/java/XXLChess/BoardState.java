package XXLChess;

import java.util.ArrayList;
import java.util.List;

public class BoardState {
    private final int BOARD_SIZE = 14;
    private Piece[][] pieces;

    public BoardState() {
        // 初始化棋盘状态为空
        pieces = new Piece[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                pieces[i][j] = Board.tiles[i][j].getPiece();
            }
        }
    }

    public BoardState(BoardState originBoardState) {
        // 初始化棋盘状态为空
        this.pieces = new Piece[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                    this.pieces[i][j] = originBoardState.pieces[i][j];
            }
        }
    }

    // 获取指定位置的棋子
    public Piece getPieceAt(int x, int y) {
        if (x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE) {
            throw new IllegalArgumentException("Invalid position");
        }
        return pieces[x][y];
    }

    // 在指定位置放置棋子
    public void changePiecePosition(int x, int y, Piece piece) {
        int fromX= piece.getCurrentTile().getCol();
        int fromY= piece.getCurrentTile().getRow();

        piece.setCurrentTile(Board.tiles[x][y]);
        pieces[fromX][fromY] = null;
        pieces[x][y] = piece;


    }

    // 获取棋盘状态
    public Piece[][] getPieces() {
        return pieces;
    }


    // 生成新局面
    public BoardState makeMove(Move move) {
        // 复制当前局面状态到新局面
        BoardState newBoardState = new BoardState(this);
        
        // 在新局面上执行指定的走法
        newBoardState.changePiecePosition(move.getX(), move.getY(), move.getPiece());
        return newBoardState;
    }


    public Piece[][] getPieces() {
        return pieces;
    }

    public List<Move> generateLegalMoves(boolean isWhite) {
        List<Move> moves = new ArrayList<>();
        return null;
//        for(int i =0;i<14;i++){
//            for(int j=0;j<14;j++){
//                if(boardState[i][j].getWhite()==isWhite){
//                    ArrayList<Tile> validMoves = boardState[i][j].getValidMoves();
//                    for(Tile tile:validMoves){
//                        return null;
//                }
//            }
//        }
//    }
}}