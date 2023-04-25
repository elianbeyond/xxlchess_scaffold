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

    public ArrayList<Tile> getValidMoves(Board board) {
        //For each chess piece, write its possible moving points.
        ArrayList<Tile> availableTiles = new ArrayList<>();
        int x = currentTile.getCol();
        int y = currentTile.getRow();
        //pawn
        switch (pieceType) {
            case PAWN:
                availableTiles = pawnRule(x, y,board);
                break;
            case ROOK:
                availableTiles = rookRule(x, y,board);
                break;
            case KNIGHT:
                availableTiles = knightRule(x, y,board);
                break;
            case BISHOP:
                availableTiles = bishopRule(x, y,board);
                break;
            case QUEEN:
                availableTiles = queenRule(x, y,board);
                break;
            case KING:
                availableTiles = kingRule(x, y,board);
                break;
            case CAMEL:
                availableTiles = camelRule(x, y,board);
                break;
            case KNIGHT_KING:
                availableTiles = knightKingRule(x, y,board);
                break;
            case AMAZON:
                availableTiles = amazonRule(x, y,board);
                break;
            case CHANCELLOR:
                availableTiles = chancellorRule(x, y,board);
                break;
            case ARCHBISHOP:
                availableTiles = archbishopRule(x, y,board);
                break;
        }
        return availableTiles;

    }

    public ArrayList<Tile> pawnRule(int x, int y,Board board) {
        ArrayList<Tile> availableTiles = new ArrayList<>();

        if (isWhite) {

            if (y == 12) {
                y--;

                if (board.tiles[x][y].getPiece() == null) {
                    availableTiles.add(board.tiles[x][y]);
                }
                x--;
                if (x >= 0 && x <= 13 && y >= 0 && y <= 13) {
                    if (board.tiles[x][y].getPiece() != null && !board.tiles[x][y].getPiece().isWhite) {
                        availableTiles.add(board.tiles[x][y]);
                    }
                }
                x = x + 2;
                if (x >= 0 && x <= 13 && y >= 0 && y <= 13) {
                    if (board.tiles[x][y].getPiece() != null && !board.tiles[x][y].getPiece().isWhite) {
                        availableTiles.add(board.tiles[x][y]);
                    }
                }
                x = x - 1;
                y--;
                if (!(x >= 0 && x <= 13 && y >= 0 && y <= 13)) {
                    return availableTiles;
                }

                if (board.tiles[x][y].getPiece() != null) {
                    return availableTiles;
                } else {
                    availableTiles.add(board.tiles[x][y]);
                }
            } else {
                //pawn is not the first act
                y--;
                if (!(x >= 0 && x <= 13 && y >= 0 && y <= 13)) {
                    return availableTiles;
                }

                if (board.tiles[x][y].getPiece() == null) {
                    availableTiles.add(board.tiles[x][y]);
                }
                x--;
                if (x >= 0 && x <= 13 && y >= 0 && y <= 13) {
                    if (board.tiles[x][y].getPiece() != null && !board.tiles[x][y].getPiece().isWhite) {
                        availableTiles.add(board.tiles[x][y]);
                    }
                }
                x = x + 2;
                if (x >= 0 && x <= 13 && y >= 0 && y <= 13) {
                    if (board.tiles[x][y].getPiece() != null && !board.tiles[x][y].getPiece().isWhite) {
                        availableTiles.add(board.tiles[x][y]);
                    }
                }
                return availableTiles;
            }

        } else {
            // black pawn
            if (y == 1) {
                y++;

                if (board.tiles[x][y].getPiece() == null) {
                    availableTiles.add(board.tiles[x][y]);
                }
                x--;
                if (x >= 0 && x <= 13 && y >= 0 && y <= 13) {
                    if (board.tiles[x][y].getPiece() != null && !board.tiles[x][y].getPiece().isWhite) {
                        availableTiles.add(board.tiles[x][y]);
                    }
                }
                x = x + 2;
                if (x >= 0 && x <= 13 && y >= 0 && y <= 13) {
                    if (board.tiles[x][y].getPiece() != null && !board.tiles[x][y].getPiece().isWhite) {
                        availableTiles.add(board.tiles[x][y]);
                    }
                }
                x = x - 1;
                y++;

                if (board.tiles[x][y].getPiece() != null) {
                    return availableTiles;
                } else {
                    availableTiles.add(board.tiles[x][y]);
                }
            } else {
                //pawn is not the first act
                y++;
                if (!(x >= 0 && x <= 13 && y >= 0 && y <= 13)) {
                    return availableTiles;
                }

                if (board.tiles[x][y].getPiece() == null) {
                    availableTiles.add(board.tiles[x][y]);
                }
                x--;
                if (x >= 0 && x <= 13 && y >= 0 && y <= 13) {
                    if (board.tiles[x][y].getPiece() != null && !board.tiles[x][y].getPiece().isWhite) {
                        availableTiles.add(board.tiles[x][y]);
                    }
                }
                x = x + 2;
                if (x >= 0 && x <= 13 && y >= 0 && y <= 13) {
                    if (board.tiles[x][y].getPiece() != null && !board.tiles[x][y].getPiece().isWhite) {
                        availableTiles.add(board.tiles[x][y]);
                    }
                }
                return availableTiles;
            }
        }
        return availableTiles;
    }

    public ArrayList<Tile> rookRule(int x, int y,Board board) {
        ArrayList<Tile> availableTiles = new ArrayList<>();

        int left = x, right = x, up = y, down = y;
        while (left > 0) {
            left--;
            if (board.tiles[left][y].getPiece() == null) {
                availableTiles.add(board.tiles[left][y]);
            } else if (board.tiles[left][y].getPiece().getWhite() != board.tiles[x][y].getPiece().getWhite()) {
                availableTiles.add(board.tiles[left][y]);
                break;
            } else {
                break;
            }
        }
        while (right < 13) {
            right++;
            if (board.tiles[right][y].getPiece() == null) {
                availableTiles.add(board.tiles[right][y]);
            } else if (board.tiles[right][y].getPiece().isWhite != board.tiles[x][y].getPiece().getWhite()) {
                availableTiles.add(board.tiles[right][y]);
                break;
            } else {
                break;
            }
        }
        while (up > 0) {
            up--;
            if (board.tiles[x][up].getPiece() == null) {
                availableTiles.add(board.tiles[x][up]);
            } else if (board.tiles[x][up].getPiece().isWhite != board.tiles[x][y].getPiece().getWhite()) {
                availableTiles.add(board.tiles[x][up]);
                break;
            } else {
                break;
            }
        }
        while (down < 13) {
            down++;
            if (board.tiles[x][down].getPiece() == null) {
                availableTiles.add(board.tiles[x][down]);
            } else if (board.tiles[x][down].getPiece().isWhite != board.tiles[x][y].getPiece().getWhite()) {
                availableTiles.add(board.tiles[x][down]);
                break;
            } else {
                break;
            }
        }
        return availableTiles;
    }

    public ArrayList<Tile> kingRule(int x, int y,Board board) {
        ArrayList<Tile> availableTiles = new ArrayList<>();

        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (i == x && j == y) {
                    continue;
                }
                if (i >= 0 && i <= 13 && j >= 0 && j <= 13) {
                    if (board.tiles[i][j].getPiece() == null || (board.tiles[i][j].getPiece().getWhite() != board.tiles[x][y].getPiece().getWhite())) {
                        availableTiles.add(board.tiles[i][j]);
                    }
                }
            }
        }
        return availableTiles;
    }

    public ArrayList<Tile> bishopRule(int x, int y,Board board) {
        ArrayList<Tile> availableTiles = new ArrayList<>();

        int i = x, j = y;
        while (i < 13 && j < 13) {
            i++;
            j++;
            if (board.tiles[i][j].getPiece() == null) {
                availableTiles.add(board.tiles[i][j]);
            } else if (board.tiles[i][j].getPiece().getWhite() != board.tiles[x][y].getPiece().getWhite()) {
                availableTiles.add(board.tiles[i][j]);
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
            if (board.tiles[i][j].getPiece() == null) {
                availableTiles.add(board.tiles[i][j]);
            } else if (board.tiles[i][j].getPiece().getWhite() != board.tiles[x][y].getPiece().getWhite()) {
                availableTiles.add(board.tiles[i][j]);
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
            if (board.tiles[i][j].getPiece() == null) {
                availableTiles.add(board.tiles[i][j]);
            } else if (board.tiles[i][j].getPiece().getWhite() != board.tiles[x][y].getPiece().getWhite()) {
                availableTiles.add(board.tiles[i][j]);
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
            if (board.tiles[i][j].getPiece() == null) {
                availableTiles.add(board.tiles[i][j]);
            } else if (board.tiles[i][j].getPiece().getWhite() != board.tiles[x][y].getPiece().getWhite()) {
                availableTiles.add(board.tiles[i][j]);
                break;
            } else {
                break;
            }
        }

        return availableTiles;

    }

    public ArrayList<Tile> queenRule(int x, int y,Board board) {
        ArrayList<Tile> availableTiles = new ArrayList<>();
        ArrayList<Tile> rookAvailableTiles = rookRule(x, y,board);
        ArrayList<Tile> bishopAvailableTiles = bishopRule(x, y,board);

        availableTiles.addAll(rookAvailableTiles);
        availableTiles.addAll(bishopAvailableTiles);
        return availableTiles;
    }

    public ArrayList<Tile> knightRule(int x, int y,Board board) {
        ArrayList<Tile> availableTiles = new ArrayList<>();

        for (int i = x - 2; i <= x + 2; i++) {
            for (int j = y - 2; j <= y + 2; j++) {
                if ((Math.abs(x - i) == 1 && Math.abs(y - j) == 2) || ((Math.abs(x - i) == 2 && Math.abs(y - j) == 1))) {
                    if (i >= 0 && i <= 13 && j >= 0 && j <= 13) {
                        if (board.tiles[i][j].getPiece() == null || (board.tiles[i][j].getPiece().getWhite() != board.tiles[x][y].getPiece().getWhite())) {
                            availableTiles.add(board.tiles[i][j]);
                        }
                    }
                }
            }
        }

        return availableTiles;

    }

    public ArrayList<Tile> camelRule(int x, int y,Board board) {
        ArrayList<Tile> availableTiles = new ArrayList<>();

        for (int i = x - 3; i <= x + 3; i++) {
            for (int j = y - 3; j <= y + 3; j++) {
                if ((Math.abs(x - i) == 1 && Math.abs(y - j) == 3) || ((Math.abs(x - i) == 3 && Math.abs(y - j) == 1))) {
                    if (i >= 0 && i <= 13 && j >= 0 && j <= 13) {
                        if (board.tiles[i][j].getPiece() == null || (board.tiles[i][j].getPiece().getWhite() != board.tiles[x][y].getPiece().getWhite())) {
                            availableTiles.add(board.tiles[i][j]);
                        }
                    }
                }
            }
        }

        return availableTiles;
    }

    public ArrayList<Tile> knightKingRule(int x, int y,Board board) {
        ArrayList<Tile> availableTiles = new ArrayList<>();
        ArrayList<Tile> kingAvailableTiles = kingRule(x, y,board);
        ArrayList<Tile> knightAvailableTiles = knightRule(x, y,board);

        availableTiles.addAll(kingAvailableTiles);
        availableTiles.addAll(knightAvailableTiles);
        return availableTiles;
    }

    public ArrayList<Tile> amazonRule(int x, int y,Board board) {
        ArrayList<Tile> availableTiles = new ArrayList<>();

        ArrayList<Tile> knightAvailableTiles = knightRule(x, y,board);
        ArrayList<Tile> bishopAvailableTiles = bishopRule(x, y,board);
        ArrayList<Tile> rookAvailableTiles = rookRule(x, y,board);
        availableTiles.addAll(knightAvailableTiles);
        availableTiles.addAll(bishopAvailableTiles);
        availableTiles.addAll(rookAvailableTiles);
        return availableTiles;
    }

    public ArrayList<Tile> chancellorRule(int x, int y,Board board) {
        ArrayList<Tile> availableTiles = new ArrayList<>();

        ArrayList<Tile> knightAvailableTiles = knightRule(x, y,board);
        ArrayList<Tile> rookAvailableTiles = rookRule(x, y,board);

        availableTiles.addAll(knightAvailableTiles);
        availableTiles.addAll(rookAvailableTiles);
        return availableTiles;
    }

    public ArrayList<Tile> archbishopRule(int x, int y,Board board) {
        ArrayList<Tile> availableTiles = new ArrayList<>();
        ArrayList<Tile> knightAvailableTiles = knightRule(x, y,board);
        ArrayList<Tile> bishopAvailableTiles = bishopRule(x, y,board);
        availableTiles.addAll(knightAvailableTiles);
        availableTiles.addAll(bishopAvailableTiles);
        return availableTiles;
    }

    public ArrayList<Tile> drawValidTiles(Board board) {

        this.validMoves = getValidMoves(board);
        //draw and return previous color information
        ArrayList<Tile> recover = new ArrayList<>();
        for (Tile tile : validMoves) {
            recover.add(new Tile(p, tile.getCol(), tile.getRow(), tile.getTileColor(), false));
            if (tile.getTileColor() == board.LIGHT_YELLOW && tile.getPiece() == null) {
                tile.setTileColor(board.LIGHT_BLUE);
            } else if (tile.getTileColor() == board.LIGHT_YELLOW && tile.getPiece() != null) {
                tile.setTileColor(board.ORANGE);
            } else if (tile.getTileColor() == board.BROWN && tile.getPiece() == null) {
                tile.setTileColor(board.HIGHLIGHT_BLUE);
            } else {
                tile.setTileColor(board.RED);
            }
            tile.setEnableMove(true);
        }
        recover.add(new Tile(p, currentTile.getCol(), currentTile.getRow(), currentTile.getTileColor(), false));
        currentTile.setTileColor(board.GREEN);

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

