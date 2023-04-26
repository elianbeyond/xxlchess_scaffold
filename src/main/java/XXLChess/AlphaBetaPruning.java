package XXLChess;

import java.util.Iterator;
import java.util.List;

public class AlphaBetaPruning {
    private final int MAX_DEPTH = 3; // 搜索深度
    private boolean isWhite; // AI棋子颜色
    private BoardState boardState; // 当前局面
    private int nodesCount; // 搜索节点数
    int count = 0;//test


    public AlphaBetaPruning(boolean isWhite, BoardState boardState) {
        this.isWhite = isWhite;
        this.boardState = boardState;
        this.nodesCount = 0;
    }

    public Move findBestMove() {
        this.nodesCount = 0; // 搜索节点数初始化
        Move bestMove = null;
        int bestScore = Integer.MIN_VALUE;
        int alpha = Integer.MIN_VALUE; // 初始化alpha
        int beta = Integer.MAX_VALUE; // 初始化beta
        List<Move> originLegalMoves = this.boardState.generateLegalMoves(this.isWhite); // 获取所有合法着法

        deleteInvalid(originLegalMoves);
        for (Move move : originLegalMoves) {
            BoardState newBoardState = this.boardState.makeMove(move); // 生成新局面
            int score = this.alphabeta(newBoardState, this.MAX_DEPTH - 1, alpha, beta, !this.isWhite); // 进行alpha-beta搜索
            if (score > bestScore) { // 更新最优着法
                bestMove = move;
                bestScore = score;
            }


        }
        System.out.println("Searched " + this.nodesCount + " nodes");
        return bestMove;
    }

    private int alphabeta(BoardState boardState, int depth, int alpha, int beta, boolean isMaximizingPlayer) {
        this.nodesCount++; // 搜索节点数加1
//        if (depth == 0 || boardState.isGameOver()) { // 达到搜索深度或者游戏结束，返回估值
        if (depth == 0 ) { // 达到搜索深度或者游戏结束，返回估值
            return this.evaluate(boardState);
        }
        List<Move> legalMoves = boardState.generateLegalMoves(isMaximizingPlayer); // 获取所有合法着法
        deleteInvalid(legalMoves);
        if (isMaximizingPlayer) { // 极大节点
            int maxScore = Integer.MIN_VALUE;
            for (Move move : legalMoves) {
                BoardState newBoard = boardState.makeMove(move); // 生成新局面
                int score = this.alphabeta(newBoard, depth - 1, alpha, beta, false); // 递归调用alphabeta搜索
                maxScore = Math.max(maxScore, score); // 取最大值
                alpha = Math.max(alpha, score); // 更新alpha
                if (beta <= alpha) { // 剪枝
                    break;
                }
            }
            return maxScore;
        } else { // 极小节点
            int minScore = Integer.MAX_VALUE;
            for (Move move : legalMoves) {
                BoardState newBoardState = this.boardState.makeMove(move); // 生成新局面
                int score = this.alphabeta(newBoardState, depth - 1, alpha, beta, true); // 递归调用alphabeta搜索
                minScore = Math.min(minScore, score); // 取最小值
                beta = Math.min(beta, score); // 更新beta
                if (beta <= alpha) { // 剪枝
                    break;
                }
            }
            return minScore;
        }
    }

    private void deleteInvalid(List<Move> legalMoves) {
        Iterator<Move> iter = legalMoves.iterator();
        while (iter.hasNext()) {
            Move move = iter.next();
            if (App.board.tiles[move.getPiece().col][move.getPiece().row].getPiece() == null
                    || move.getPiece().col == move.getX() && move.getPiece().row == move.getY()) {
                iter.remove();
            }
        }
    }


    private int evaluate(BoardState boardState) {
        // 简单的估值函数，对各个棋子的位置和价值进行加权求和
        int score = 0;
        for (int i=0;i<14;i++) {
            for(int j =0;j<14;j++){
                Piece piece = boardState.getPieceAt(i,j);
                if(piece==null){
                    continue;
                }
                if (piece.getWhite() == this.isWhite) { // 统计AI棋子的得分
                    score += piece.getValue() + piece.getPositionScore();
                } else { // 统计对手棋子的得分
                    score -= piece.getValue() + piece.getPositionScore();
                }
            }

        }
        return score;
    }
}