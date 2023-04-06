package XXLChess;

import processing.core.PApplet;

public class Tile {

    private int x, y;
    private int size;
    private boolean isBlack;
    private boolean isHighlighted;
    private Piece piece;

    public Tile(int x, int y, int size, boolean isBlack) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.isBlack = isBlack;
        this.isHighlighted = false;
        this.piece = null;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void drawTile(PApplet p) {
        if (this.isHighlighted) {
            p.fill(100, 100, 255);
        } else if (this.isBlack) {
            p.fill(0);
        } else {
            p.fill(255);
        }
        p.rect(this.x, this.y, this.size, this.size);
        if (this.piece != null) {
            this.piece.drawPiece(p, this.x, this.y);
        }
    }

    public boolean isBlack() {
        return this.isBlack;
    }

    public void setHighlighted(boolean isHighlighted) {
        this.isHighlighted = isHighlighted;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece removePiece() {
        Piece removedPiece = this.piece;
        this.piece = null;
        return removedPiece;
    }

}
