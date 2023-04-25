package XXLChess;

public class Move {
    private Piece piece;
    private int toX;
    private int toY;

    public Piece getPiece() {
        return piece;
    }

    public Move(Piece piece, int toX, int toY) {
        this.piece = piece;
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
