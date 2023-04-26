package XXLChess;

public class Move {
    private Piece piece;
    private int toX;
    private int toY;

    public Piece getPiece() {
        return piece;
    }

    public Move(Piece piece, int toX, int toY) {
        this.piece = new Piece(piece.getPieceType(), piece.col,piece.row, piece.getWhite(), piece.getImg());
        this.toX = toX;
        this.toY = toY;
    }

    public int getX() {
        return toX;
    }

    public int getY() {
        return toY;
    }


}
