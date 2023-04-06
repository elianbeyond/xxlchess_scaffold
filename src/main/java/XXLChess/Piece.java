package XXLChess;

import processing.core.PApplet;
import processing.core.PImage;

public class Piece {

    private int x;
    private int y;
    private PImage image;
    private boolean isWhite;

    public static final int SIZE = 48;

    public Piece(int x, int y, PImage image, boolean isWhite) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.isWhite = isWhite;
    }


    public void drawPiece(PApplet app, int x, int y) {
        app.image(this.image, x, y);
    }

    // Add any additional methods or attributes you want.
}
