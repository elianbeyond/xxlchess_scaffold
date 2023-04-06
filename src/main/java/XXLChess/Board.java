package XXLChess;

import processing.core.PApplet;
import processing.core.PImage;

public class Board {

    private Tile[][] tiles;
    private PImage[] pieceImages;
    private PApplet p;

    public Board(PApplet p, PImage[] pieceImages) {
        this.p = p;
        this.pieceImages = pieceImages;
        this.tiles = new Tile[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boolean isBlack = (i + j) % 2 == 1;
                this.tiles[i][j] = new Tile(i * Piece.SIZE, j * Piece.SIZE, Piece.SIZE, isBlack);
                if (j == 0) {
                    this.tiles[i][j].setPiece(new Piece(i * Piece.SIZE, j * Piece.SIZE, pieceImages[2], false));
                }
                if (j == 1) {
                    this.tiles[i][j].setPiece(new Piece(i * Piece.SIZE, j * Piece.SIZE, pieceImages[1], false));
                }
                if (j == 6) {
                    this.tiles[i][j].setPiece(new Piece(i * Piece.SIZE, j * Piece.SIZE, pieceImages[1], true));
                }
                if (j == 7) {
                    this.tiles[i][j].setPiece(new Piece(i * Piece.SIZE, j * Piece.SIZE, pieceImages[2], true));
                }
            }
        }
    }


    public void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.tiles[i][j].drawTile(p);
            }
        }
    }

    public Tile getTile(int x, int y) {
        if (x >= 0 && x < 8 && y >= 0 && y < 8) {
            return this.tiles[x][y];
        } else {
            return null;
        }
    }

    public void movePiece(Tile startTile, Tile endTile) {
        if (startTile != null && endTile != null && startTile.getPiece() != null && endTile.getPiece() == null) {
            endTile.setPiece(startTile.removePiece());
        }
    }

}
