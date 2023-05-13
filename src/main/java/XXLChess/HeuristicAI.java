package XXLChess;

import java.util.Iterator;
import java.util.List;

public class HeuristicAI {

    private boolean isWhite; // AI棋子颜色
    private BoardState boardState; // 当前局面



    public HeuristicAI(boolean isWhite, BoardState boardState) {
        this.isWhite = isWhite;
        this.boardState = boardState;

    }

    public Move findBestMove() {

        Move bestMove = null;
        double bestScore = -9999;

        List<Move> originLegalMoves = this.boardState.generateLegalMoves(this.isWhite); // 获取所有合法着法

        deleteInvalid(originLegalMoves);
        for (Move move : originLegalMoves) {
            BoardState newBoardState = this.boardState.makeMove(move); // 生成新局面
            double score = this.evaluate(newBoardState); // 判断新局面的分数
            if (score > bestScore) { // 更新最优着法
                bestMove = move;
                bestScore = score;
            }


        }

        return bestMove;
    }



    private void deleteInvalid(List<Move> legalMoves) {
        Iterator<Move> iter = legalMoves.iterator();
        while (iter.hasNext()) {
            Move move = iter.next();
            if (App.board.tiles[move.getPiece().col][move.getPiece().row].getPiece() == null
                    || (move.getPiece().col == move.getX() && move.getPiece().row == move.getY())) {
                iter.remove();
            }
        }
    }


    private double evaluate(BoardState boardState) {
        // 简单的估值函数，对各个棋子的位置和价值进行加权求和
        int score = 0;
        for (int i=0;i<14;i++) {
            for(int j =0;j<14;j++){
                Piece piece = boardState.getPieceAt(i,j);
                if(piece!=null){// 统计AI棋子的得分
                    if(piece.getWhite() == this.isWhite){
                        score += piece.getValue() + piece.getPositionScore();
                    }else{
                        score -= piece.getValue() + piece.getPositionScore();
                    }
                }
            }

        }
        return score;
    }
}