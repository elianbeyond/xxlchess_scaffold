package XXLChess;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Piece {
    private PApplet p;
    private Tile currentTile;
    private Boolean isWhite;
    private PImage img;
    private Manager.PieceType pieceType;

    public void setPieceType(Manager.PieceType pieceType) {
        this.pieceType = pieceType;
    }


    private ArrayList<Tile> validMoves;


    public PImage getImg() {
        return img;
    }

    public void setImg(PImage img) {
        this.img = img;
    }

    public Piece(Manager.PieceType pieceType, Tile currentTile, Boolean isWhite, PImage img) {
        this.p = currentTile.getP();
        this.pieceType = pieceType;
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
        switch (pieceType) {
            case PAWN:
                availableTiles = pawnRule(x, y);
                break;
            case ROOK:
                availableTiles = rookRule(x, y);
                break;
            case KNIGHT:
                availableTiles = knightRule(x, y);
                break;
            case BISHOP:
                availableTiles = bishopRule(x, y);
                break;
            case QUEEN:
                availableTiles = queenRule(x, y);
                break;
            case KING:
                availableTiles = kingRule(x, y);
                break;
            case CAMEL:
                availableTiles = camelRule(x, y);
                break;
            case KNIGHT_KING:
                availableTiles = knightKingRule(x, y);
                break;
            case AMAZON:
                availableTiles = amazonRule(x, y);
                break;
            case CHANCELLOR:
                availableTiles = chancellorRule(x, y);
                break;
            case ARCHBISHOP:
                availableTiles = archbishopRule(x, y);
                break;
        }
        return availableTiles;

    }

    public ArrayList<Tile> pawnRule(int x, int y) {
        ArrayList<Tile> availableTiles = new ArrayList<>();

        if (isWhite) {

            if (y == 12) {
                y--;

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

        } else {
            // black pawn
            if (y == 1) {
                y++;

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
                y++;

                if (Board.tiles[x][y].getPiece() != null) {
                    return availableTiles;
                } else {
                    availableTiles.add(Board.tiles[x][y]);
                }
            } else {
                //pawn is not the first act
                y++;
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
        return availableTiles;
    }

    public ArrayList<Tile> rookRule(int x, int y) {
        ArrayList<Tile> availableTiles = new ArrayList<>();

        int left = x, right = x, up = y, down = y;
        while (left > 0) {
            left--;
            if (Board.tiles[left][y].getPiece() == null) {
                availableTiles.add(Board.tiles[left][y]);
            } else if (Board.tiles[left][y].getPiece().getWhite() != Board.tiles[x][y].getPiece().getWhite()) {
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
            } else if (Board.tiles[right][y].getPiece().isWhite != Board.tiles[x][y].getPiece().getWhite()) {
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
            } else if (Board.tiles[x][up].getPiece().isWhite != Board.tiles[x][y].getPiece().getWhite()) {
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
            } else if (Board.tiles[x][down].getPiece().isWhite != Board.tiles[x][y].getPiece().getWhite()) {
                availableTiles.add(Board.tiles[x][down]);
                break;
            } else {
                break;
            }
        }
        return availableTiles;
    }

    public ArrayList<Tile> kingRule(int x, int y) {
        ArrayList<Tile> availableTiles = new ArrayList<>();

        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (i == x && j == y) {
                    continue;
                }
                if (i >= 0 && i <= 13 && j >= 0 && j <= 13) {
                    if (Board.tiles[i][j].getPiece() == null || (Board.tiles[i][j].getPiece().getWhite() != Board.tiles[x][y].getPiece().getWhite())) {
                        availableTiles.add(Board.tiles[i][j]);
                    }
                }
            }
        }
        return availableTiles;
    }

    public ArrayList<Tile> bishopRule(int x, int y) {
        ArrayList<Tile> availableTiles = new ArrayList<>();

        int i = x, j = y;
        while (i < 13 && j < 13) {
            i++;
            j++;
            if (Board.tiles[i][j].getPiece() == null) {
                availableTiles.add(Board.tiles[i][j]);
            } else if (Board.tiles[i][j].getPiece().getWhite() != Board.tiles[x][y].getPiece().getWhite()) {
                availableTiles.add(Board.tiles[i][j]);
                break;
            } else {
                break;
            }
        }
        i = x;
        j = y;
        while (i > 0 && j > 0) {
            i--;
            j--;
            if (Board.tiles[i][j].getPiece() == null) {
                availableTiles.add(Board.tiles[i][j]);
            } else if (Board.tiles[i][j].getPiece().getWhite() != Board.tiles[x][y].getPiece().getWhite()) {
                availableTiles.add(Board.tiles[i][j]);
                break;
            } else {
                break;
            }
        }
        i = x;
        j = y;
        while (i < 13 && j > 0) {
            i++;
            j--;
            if (Board.tiles[i][j].getPiece() == null) {
                availableTiles.add(Board.tiles[i][j]);
            } else if (Board.tiles[i][j].getPiece().getWhite() != Board.tiles[x][y].getPiece().getWhite()) {
                availableTiles.add(Board.tiles[i][j]);
                break;
            } else {
                break;
            }
        }
        i = x;
        j = y;
        while (i > 0 && j < 13) {
            i--;
            j++;
            if (Board.tiles[i][j].getPiece() == null) {
                availableTiles.add(Board.tiles[i][j]);
            } else if (Board.tiles[i][j].getPiece().getWhite() != Board.tiles[x][y].getPiece().getWhite()) {
                availableTiles.add(Board.tiles[i][j]);
                break;
            } else {
                break;
            }
        }

        return availableTiles;

    }

    public ArrayList<Tile> queenRule(int x, int y) {
        ArrayList<Tile> availableTiles = new ArrayList<>();
        ArrayList<Tile> rookAvailableTiles = rookRule(x, y);
        ArrayList<Tile> bishopAvailableTiles = bishopRule(x, y);

        availableTiles.addAll(rookAvailableTiles);
        availableTiles.addAll(bishopAvailableTiles);
        return availableTiles;
    }

    public ArrayList<Tile> knightRule(int x, int y) {
        ArrayList<Tile> availableTiles = new ArrayList<>();

        for (int i = x - 2; i <= x + 2; i++) {
            for (int j = y - 2; j <= y + 2; j++) {
                if ((Math.abs(x - i) == 1 && Math.abs(y - j) == 2) || ((Math.abs(x - i) == 2 && Math.abs(y - j) == 1))) {
                    if (i >= 0 && i <= 13 && j >= 0 && j <= 13) {
                        if (Board.tiles[i][j].getPiece() == null || (Board.tiles[i][j].getPiece().getWhite() != Board.tiles[x][y].getPiece().getWhite())) {
                            availableTiles.add(Board.tiles[i][j]);
                        }
                    }
                }
            }
        }

        return availableTiles;

    }

    public ArrayList<Tile> camelRule(int x, int y) {
        ArrayList<Tile> availableTiles = new ArrayList<>();

        for (int i = x - 3; i <= x + 3; i++) {
            for (int j = y - 3; j <= y + 3; j++) {
                if ((Math.abs(x - i) == 1 && Math.abs(y - j) == 3) || ((Math.abs(x - i) == 3 && Math.abs(y - j) == 1))) {
                    if (i >= 0 && i <= 13 && j >= 0 && j <= 13) {
                        if (Board.tiles[i][j].getPiece() == null || (Board.tiles[i][j].getPiece().getWhite() != Board.tiles[x][y].getPiece().getWhite())) {
                            availableTiles.add(Board.tiles[i][j]);
                        }
                    }
                }
            }
        }

        return availableTiles;
    }

    public ArrayList<Tile> knightKingRule(int x, int y) {
        ArrayList<Tile> availableTiles = new ArrayList<>();
        ArrayList<Tile> kingAvailableTiles = kingRule(x, y);
        ArrayList<Tile> knightAvailableTiles = knightRule(x, y);

        availableTiles.addAll(kingAvailableTiles);
        availableTiles.addAll(knightAvailableTiles);
        return availableTiles;
    }

    public ArrayList<Tile> amazonRule(int x, int y) {
        ArrayList<Tile> availableTiles = new ArrayList<>();

        ArrayList<Tile> knightAvailableTiles = knightRule(x, y);
        ArrayList<Tile> bishopAvailableTiles = bishopRule(x, y);
        ArrayList<Tile> rookAvailableTiles = rookRule(x, y);
        availableTiles.addAll(knightAvailableTiles);
        availableTiles.addAll(bishopAvailableTiles);
        availableTiles.addAll(rookAvailableTiles);
        return availableTiles;
    }

    public ArrayList<Tile> chancellorRule(int x, int y) {
        ArrayList<Tile> availableTiles = new ArrayList<>();

        ArrayList<Tile> knightAvailableTiles = knightRule(x, y);
        ArrayList<Tile> rookAvailableTiles = rookRule(x, y);

        availableTiles.addAll(knightAvailableTiles);
        availableTiles.addAll(rookAvailableTiles);
        return availableTiles;
    }

    public ArrayList<Tile> archbishopRule(int x, int y) {
        ArrayList<Tile> availableTiles = new ArrayList<>();
        ArrayList<Tile> knightAvailableTiles = knightRule(x, y);
        ArrayList<Tile> bishopAvailableTiles = bishopRule(x, y);
        availableTiles.addAll(knightAvailableTiles);
        availableTiles.addAll(bishopAvailableTiles);
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




    public Boolean getWhite() {
        return isWhite;
    }


    public void setCurrentTile(Tile tile) {
        this.currentTile = tile;
    }

    public Tile getCurrentTile() {
        return this.currentTile;
    }
    public Manager.PieceType getPieceType() {
        return pieceType;
    }


    public int getValue() {
        return pieceType.getIntValue();
    }

    public int getPositionScore() {
        int x= currentTile.getCol();
        int y =currentTile.getRow();
        x = Math.min(x,13-x);
        y = Math.min(y,13-y);
        return (x+y)/4;
    }
}

