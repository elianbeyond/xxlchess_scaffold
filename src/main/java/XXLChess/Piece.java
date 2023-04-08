package XXLChess;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Piece {
    private PApplet p;
    private String pieceName;
    private Tile currentTile;
    private Boolean isWhite;
    private PImage img;

    private ArrayList<Tile> validMoves;


    public PImage getImg() {
        return img;
    }

    public void setImg(PImage img) {
        this.img = img;
    }

    public Piece(String pieceName, Tile currentTile, Boolean isWhite, PImage img) {
        this.p = currentTile.getP();
        this.pieceName = pieceName;
        this.currentTile = currentTile;
        this.isWhite = isWhite;
        this.img = img;
        this.validMoves = null;
    }

    public ArrayList<Tile> getValidMoves() {
        //For each chess piece, write its possible moving points.
        ArrayList<Tile> availableTiles = new ArrayList<>();
        int x = currentTile.getCol();
        int y = currentTile.getRow();
        //pawn
        if (pieceName.contains("pawn")) {
            if (isWhite) {
                if (y == 12) {
                    y--;
                    if (!(x >= 0 && x <= 13 && y >= 0 && y <= 13)) {
                        return availableTiles;
                    }

                    if (Board.tiles[x][y].getPiece() == null) {
                        availableTiles.add(Board.tiles[x][y]);
                    }
                    x--;
                    if (x >= 0 && x <= 13 && y >= 0 && y <= 13) {
                        if (Board.tiles[x][y].getPiece() != null && !Board.tiles[x][y].getPiece().isWhite) {
                            availableTiles.add(Board.tiles[x][y]);
                        }
                    }
                    x = x + 2;
                    if (x >= 0 && x <= 13 && y >= 0 && y <= 13) {
                        if (Board.tiles[x][y].getPiece() != null && !Board.tiles[x][y].getPiece().isWhite) {
                            availableTiles.add(Board.tiles[x][y]);
                        }
                    }
                    x = x - 1;
                    y--;
                    if (!(x >= 0 && x <= 13 && y >= 0 && y <= 13)) {
                        return availableTiles;
                    }

                    if (Board.tiles[x][y].getPiece() != null) {
                        return availableTiles;
                    } else {
                        availableTiles.add(Board.tiles[x][y]);
                    }
                } else {
                    //pawn is not the first act
                    y--;
                    if (!(x >= 0 && x <= 13 && y >= 0 && y <= 13)) {
                        return availableTiles;
                    }

                    if (Board.tiles[x][y].getPiece() == null) {
                        availableTiles.add(Board.tiles[x][y]);
                    }
                    x--;
                    if (x >= 0 && x <= 13 && y >= 0 && y <= 13) {
                        if (Board.tiles[x][y].getPiece() != null && !Board.tiles[x][y].getPiece().isWhite) {
                            availableTiles.add(Board.tiles[x][y]);
                        }
                    }
                    x = x + 2;
                    if (x >= 0 && x <= 13 && y >= 0 && y <= 13) {
                        if (Board.tiles[x][y].getPiece() != null && !Board.tiles[x][y].getPiece().isWhite) {
                            availableTiles.add(Board.tiles[x][y]);
                        }
                    }
                    return availableTiles;
                }
            }
        }

        //rook
        if (pieceName.contains("rook")) {
            if (isWhite) {
                int left = x, right = x, up = y, down = y;
                while (left > 0) {
                    left--;
                    if (Board.tiles[left][y].getPiece() == null) {
                        availableTiles.add(Board.tiles[left][y]);
                    } else if (!Board.tiles[left][y].getPiece().isWhite) {
                        availableTiles.add(Board.tiles[left][y]);
                        break;
                    } else {
                        break;
                    }
                }
                while (right < 13) {
                    right++;
                    if (Board.tiles[right][y].getPiece() == null) {
                        availableTiles.add(Board.tiles[right][y]);
                    } else if (!Board.tiles[right][y].getPiece().isWhite) {
                        availableTiles.add(Board.tiles[right][y]);
                        break;
                    } else {
                        break;
                    }
                }
                while (up > 0) {
                    up--;
                    if (Board.tiles[x][up].getPiece() == null) {
                        availableTiles.add(Board.tiles[x][up]);
                    } else if (!Board.tiles[x][up].getPiece().isWhite) {
                        availableTiles.add(Board.tiles[x][up]);
                        break;
                    } else {
                        break;
                    }
                }
                while (down < 13) {
                    down++;
                    if (Board.tiles[x][down].getPiece() == null) {
                        availableTiles.add(Board.tiles[x][down]);
                    } else if (!Board.tiles[x][down].getPiece().isWhite) {
                        availableTiles.add(Board.tiles[x][down]);
                        break;
                    } else {
                        break;
                    }
                }
            }
        }
        //king
        if (pieceName.contains("king")) {
            if (isWhite) {
                for(int i = x-1;i<x+2;i++){
                    for(int j = y-1;j<y+2;j++){
                        if(i==x&&j==y){
                            continue;
                        }
                        if (i >= 0 && i <= 13 && j >= 0 && j <= 13){
                            if (Board.tiles[i][j].getPiece() == null||!Board.tiles[i][j].getPiece().isWhite) {
                                availableTiles.add(Board.tiles[i][j]);
                            }
                        }
                    }
                }
            }
        }
        //queen
        if (pieceName.contains("queen")) {
            if (isWhite) {
                //queen extends in six directions

            }
        }
        return availableTiles;
    }


    public ArrayList<Tile> drawValidTiles() {

        this.validMoves = getValidMoves();
        //draw and return previous color information
        ArrayList<Tile> recover = new ArrayList<>();
        for (Tile tile : validMoves) {
            recover.add(new Tile(p, tile.getCol(), tile.getRow(), tile.getTileColor(), false));
            if (tile.getTileColor() == Board.LIGHT_YELLOW && tile.getPiece() == null) {
                tile.setTileColor(Board.LIGHT_BLUE);
            } else if (tile.getTileColor() == Board.LIGHT_YELLOW && tile.getPiece() != null) {
                tile.setTileColor(Board.ORANGE);
            } else if (tile.getTileColor() == Board.BROWN && tile.getPiece() == null) {
                tile.setTileColor(Board.HIGHLIGHT_BLUE);
            } else {
                tile.setTileColor(Board.RED);
            }
            tile.setEnableMove(true);
        }
        recover.add(new Tile(p, currentTile.getCol(), currentTile.getRow(), currentTile.getTileColor(), false));
        currentTile.setTileColor(Board.GREEN);

        return recover;
    }


    public String getPieceName() {
        return pieceName;
    }

    public void setPieceName(String pieceName) {
        this.pieceName = pieceName;
    }

    public Boolean getWhite() {
        return isWhite;
    }

    public void setWhite(Boolean white) {
        isWhite = white;
    }

    public void setCurrentTile(Tile tile) {
        this.currentTile = tile;
    }

    public Tile getCurrentTile() {
        return this.currentTile;
    }

}

