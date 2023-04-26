package XXLChess;

import processing.core.PImage;

import java.util.ArrayList;

public class Piece {
    private Boolean isWhite;
    private PImage img;
    private Manager.PieceType pieceType;
    public int col;

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int row;

    public void setPieceType(Manager.PieceType pieceType) {
        this.pieceType = pieceType;
    }



    public PImage getImg() {
        return img;
    }

    public void setImg(PImage img) {
        this.img = img;
    }

    public Piece(Manager.PieceType pieceType, int x,int y, Boolean isWhite, PImage img) {

        this.pieceType = pieceType;
        this.isWhite = isWhite;
        this.img = img;
        col=x;
        row=y;
    }

    public ArrayList<Move> getValidMoves(BoardState boardState,int x,int y) {
        //For each chess piece, write its possible moving points.
        ArrayList<Move> availableMoves = new ArrayList<>();
        //pawn
        switch (pieceType) {
            case PAWN:
                availableMoves = pawnRule(x, y,boardState);
                break;
            case ROOK:
                availableMoves = rookRule(x, y,boardState);
                break;
            case KNIGHT:
                availableMoves = knightRule(x, y,boardState);
                break;
            case BISHOP:
                availableMoves = bishopRule(x, y,boardState);
                break;
            case QUEEN:
                availableMoves = queenRule(x, y,boardState);
                break;
            case KING:
                availableMoves = kingRule(x, y,boardState);
                break;
            case CAMEL:
                availableMoves = camelRule(x, y,boardState);
                break;
            case KNIGHT_KING:
                availableMoves = knightKingRule(x, y,boardState);
                break;
            case AMAZON:
                availableMoves = amazonRule(x, y,boardState);
                break;
            case CHANCELLOR:
                availableMoves = chancellorRule(x, y,boardState);
                break;
            case ARCHBISHOP:
                availableMoves = archbishopRule(x, y,boardState);
                break;
        }
        return availableMoves;

    }

    public ArrayList<Move> pawnRule(int x, int y,BoardState boardState) {
        ArrayList<Move> availableMoves = new ArrayList<>();
        int originX =x;
        int originY =y;

        if (isWhite) {

            if (y == 12) {
                y--;

                if (boardState.pieces[x][y] == null) {
                    availableMoves.add(new Move(boardState.pieces[originX][originY],x,y));
                }
                x--;
                if (x >= 0 && x <= 13 && y >= 0 && y <= 13) {
                    if (boardState.pieces[x][y] != null && !boardState.pieces[x][y].isWhite) {
                        availableMoves.add(new Move(boardState.pieces[originX][originY],x,y));
                    }
                }
                x = x + 2;
                if (x >= 0 && x <= 13 && y >= 0 && y <= 13) {
                    if (boardState.pieces[x][y] != null && !boardState.pieces[x][y].isWhite) {
                        availableMoves.add(new Move(boardState.pieces[originX][originY],x,y));
                    }
                }
                x = x - 1;
                y--;
                if (!(x >= 0 && x <= 13 && y >= 0 && y <= 13)) {
                    return availableMoves;
                }

                if (boardState.pieces[x][y] != null) {
                    return availableMoves;
                } else {
                    availableMoves.add(new Move(boardState.pieces[originX][originY],x,y));
                }
            } else {
                //pawn is not the first act
                y--;
                if (!(x >= 0 && x <= 13 && y >= 0 && y <= 13)) {
                    return availableMoves;
                }

                if (boardState.pieces[x][y] == null) {
                    availableMoves.add(new Move(boardState.pieces[originX][originY],x,y));
                }
                x--;
                if (x >= 0 && x <= 13 && y >= 0 && y <= 13) {
                    if (boardState.pieces[x][y] != null && !boardState.pieces[x][y].isWhite) {
                        availableMoves.add(new Move(boardState.pieces[originX][originY],x,y));
                    }
                }
                x = x + 2;
                if (x >= 0 && x <= 13 && y >= 0 && y <= 13) {
                    if (boardState.pieces[x][y] != null && !boardState.pieces[x][y].isWhite) {
                        availableMoves.add(new Move(boardState.pieces[originX][originY],x,y));
                    }
                }
                return availableMoves;
            }

        } else {
            // black pawn
            if (y == 1) {
                y++;

                if (boardState.pieces[x][y] == null) {
                    availableMoves.add(new Move(boardState.pieces[originX][originY],x,y));
                }
                x--;
                if (x >= 0 && x <= 13 && y >= 0 && y <= 13) {
                    if (boardState.pieces[x][y] != null && !boardState.pieces[x][y].isWhite) {
                        availableMoves.add(new Move(boardState.pieces[originX][originY],x,y));
                    }
                }
                x = x + 2;
                if (x >= 0 && x <= 13 && y >= 0 && y <= 13) {
                    if (boardState.pieces[x][y] != null && !boardState.pieces[x][y].isWhite) {
                        availableMoves.add(new Move(boardState.pieces[originX][originY],x,y));
                    }
                }
                x = x - 1;
                y++;

                if (boardState.pieces[x][y] != null) {
                    return availableMoves;
                } else {
                    availableMoves.add(new Move(boardState.pieces[originX][originY],x,y));
                }
            } else {
                //pawn is not the first act
                y++;
                if (!(x >= 0 && x <= 13 && y >= 0 && y <= 13)) {
                    return availableMoves;
                }

                if (boardState.pieces[x][y] == null) {
                    availableMoves.add(new Move(boardState.pieces[originX][originY],x,y));
                }
                x--;
                if (x >= 0 && x <= 13 && y >= 0 && y <= 13) {
                    if (boardState.pieces[x][y] != null && !boardState.pieces[x][y].isWhite) {
                        availableMoves.add(new Move(boardState.pieces[originX][originY],x,y));
                    }
                }
                x = x + 2;
                if (x >= 0 && x <= 13 && y >= 0 && y <= 13) {
                    if (boardState.pieces[x][y] != null && !boardState.pieces[x][y].isWhite) {
                        availableMoves.add(new Move(boardState.pieces[originX][originY],x,y));
                    }
                }
                return availableMoves;
            }
        }
        return availableMoves;
    }

    public ArrayList<Move> rookRule(int x, int y,BoardState boardState) {
        ArrayList<Move> availableMoves = new ArrayList<>();

        int left = x, right = x, up = y, down = y;
        while (left > 0) {
            left--;
            if (boardState.pieces[left][y] == null) {
                availableMoves.add(new Move(boardState.pieces[x][y],left,y));
            } else if (boardState.pieces[left][y].getWhite() != boardState.pieces[x][y].getWhite()) {
                availableMoves.add(new Move(boardState.pieces[x][y],left,y));
                break;
            } else {
                break;
            }
        }
        while (right < 13) {
            right++;
            if (boardState.pieces[right][y] == null) {
                availableMoves.add(new Move(boardState.pieces[x][y],right,y));
            } else if (boardState.pieces[right][y].isWhite != boardState.pieces[x][y].getWhite()) {
                availableMoves.add(new Move(boardState.pieces[x][y],right,y));
                break;
            } else {
                break;
            }
        }
        while (up > 0) {
            up--;
            if (boardState.pieces[x][up] == null) {
                availableMoves.add(new Move(boardState.pieces[x][y],x,up));
            } else if (boardState.pieces[x][up].isWhite != boardState.pieces[x][y].getWhite()) {
                availableMoves.add(new Move(boardState.pieces[x][y],x,up));
                break;
            } else {
                break;
            }
        }
        while (down < 13) {
            down++;
            if (boardState.pieces[x][down] == null) {
                availableMoves.add(new Move(boardState.pieces[x][y],x,down));
            } else if (boardState.pieces[x][down].isWhite != boardState.pieces[x][y].getWhite()) {
                availableMoves.add(new Move(boardState.pieces[x][y],x,down));
                break;
            } else {
                break;
            }
        }
        return availableMoves;
    }

    public ArrayList<Move> kingRule(int x, int y,BoardState boardState) {
        ArrayList<Move> availableMoves = new ArrayList<>();

        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (i >= 0 && i <= 13 && j >= 0 && j <= 13) {
                    if (boardState.pieces[i][j] == null || (boardState.pieces[i][j].getWhite() != boardState.pieces[x][y].getWhite())) {
                        availableMoves.add(new Move(boardState.pieces[x][y],i,j));
                    }
                }
            }
        }
        return availableMoves;
    }

    public ArrayList<Move> bishopRule(int x, int y,BoardState boardState) {
        ArrayList<Move> availableMoves = new ArrayList<>();

        int i = x, j = y;
        while (i < 13 && j < 13) {
            i++;
            j++;
            if (boardState.pieces[i][j] == null) {
                availableMoves.add(new Move(boardState.pieces[x][y],i,j));
            } else if (boardState.pieces[i][j].getWhite() != boardState.pieces[x][y].getWhite()) {
                availableMoves.add(new Move(boardState.pieces[x][y],i,j));
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
            if (boardState.pieces[i][j] == null) {
                availableMoves.add(new Move(boardState.pieces[x][y],i,j));
            } else if (boardState.pieces[i][j].getWhite() != boardState.pieces[x][y].getWhite()) {
                availableMoves.add(new Move(boardState.pieces[x][y],i,j));
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
            if (boardState.pieces[i][j] == null) {
                availableMoves.add(new Move(boardState.pieces[x][y],i,j));
            } else if (boardState.pieces[i][j].getWhite() != boardState.pieces[x][y].getWhite()) {
                availableMoves.add(new Move(boardState.pieces[x][y],i,j));
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
            if (boardState.pieces[i][j] == null) {
                availableMoves.add(new Move(boardState.pieces[x][y],i,j));
            } else if (boardState.pieces[i][j].getWhite() != boardState.pieces[x][y].getWhite()) {
                availableMoves.add(new Move(boardState.pieces[x][y],i,j));
                break;
            } else {
                break;
            }
        }

        return availableMoves;

    }

    public ArrayList<Move> queenRule(int x, int y,BoardState boardState) {
        ArrayList<Move> availableMoves = new ArrayList<>();
        ArrayList<Move> rookavailableMoves = rookRule(x, y,boardState);
        ArrayList<Move> bishopavailableMoves = bishopRule(x, y,boardState);

        availableMoves.addAll(rookavailableMoves);
        availableMoves.addAll(bishopavailableMoves);
        return availableMoves;
    }

    public ArrayList<Move> knightRule(int x, int y,BoardState boardState) {
        ArrayList<Move> availableMoves = new ArrayList<>();

        for (int i = x - 2; i <= x + 2; i++) {
            for (int j = y - 2; j <= y + 2; j++) {
                if ((Math.abs(x - i) == 1 && Math.abs(y - j) == 2) || ((Math.abs(x - i) == 2 && Math.abs(y - j) == 1))) {
                    if (i >= 0 && i <= 13 && j >= 0 && j <= 13) {
                        if (boardState.pieces[i][j] == null || (boardState.pieces[i][j].getWhite() != boardState.pieces[x][y].getWhite())) {
                            availableMoves.add(new Move(boardState.pieces[x][y],i,j));
                        }
                    }
                }
            }
        }

        return availableMoves;

    }

    public ArrayList<Move> camelRule(int x, int y,BoardState boardState) {
        ArrayList<Move> availableMoves = new ArrayList<>();

        for (int i = x - 3; i <= x + 3; i++) {
            for (int j = y - 3; j <= y + 3; j++) {
                if ((Math.abs(x - i) == 1 && Math.abs(y - j) == 3) || ((Math.abs(x - i) == 3 && Math.abs(y - j) == 1))) {
                    if (i >= 0 && i <= 13 && j >= 0 && j <= 13) {
                        if (boardState.pieces[i][j] == null || (boardState.pieces[i][j].getWhite() != boardState.pieces[x][y].getWhite())) {
                            availableMoves.add(new Move(boardState.pieces[x][y],i,j));
                        }
                    }
                }
            }
        }

        return availableMoves;
    }

    public ArrayList<Move> knightKingRule(int x, int y,BoardState boardState) {
        ArrayList<Move> availableMoves = new ArrayList<>();
        ArrayList<Move> kingavailableMoves = kingRule(x, y,boardState);
        ArrayList<Move> knightavailableMoves = knightRule(x, y,boardState);

        availableMoves.addAll(kingavailableMoves);
        availableMoves.addAll(knightavailableMoves);
        return availableMoves;
    }

    public ArrayList<Move> amazonRule(int x, int y,BoardState boardState) {
        ArrayList<Move> availableMoves = new ArrayList<>();

        ArrayList<Move> knightavailableMoves = knightRule(x, y,boardState);
        ArrayList<Move> bishopavailableMoves = bishopRule(x, y,boardState);
        ArrayList<Move> rookavailableMoves = rookRule(x, y,boardState);
        availableMoves.addAll(knightavailableMoves);
        availableMoves.addAll(bishopavailableMoves);
        availableMoves.addAll(rookavailableMoves);
        return availableMoves;
    }

    public ArrayList<Move> chancellorRule(int x, int y,BoardState boardState) {
        ArrayList<Move> availableMoves = new ArrayList<>();

        ArrayList<Move> knightavailableMoves = knightRule(x, y,boardState);
        ArrayList<Move> rookavailableMoves = rookRule(x, y,boardState);

        availableMoves.addAll(knightavailableMoves);
        availableMoves.addAll(rookavailableMoves);
        return availableMoves;
    }

    public ArrayList<Move> archbishopRule(int x, int y,BoardState boardState) {
        ArrayList<Move> availableMoves = new ArrayList<>();
        ArrayList<Move> knightavailableMoves = knightRule(x, y,boardState);
        ArrayList<Move> bishopavailableMoves = bishopRule(x, y,boardState);
        availableMoves.addAll(knightavailableMoves);
        availableMoves.addAll(bishopavailableMoves);
        return availableMoves;
    }

    public ArrayList<Tile> drawValidTiles(Board board,int x,int y) {
        BoardState boardState = new BoardState(board);


        ArrayList<Move> validMoves = getValidMoves(boardState,x,y);
        //draw and return previous color information
        ArrayList<Tile> recover = new ArrayList<>();
        for (Move move : validMoves) {
            Tile tile = App.board.tiles[move.getX()][move.getY()];
            Tile tileRecover = new Tile(tile.getP(),tile.getCol(),tile.getRow(),tile.getTileColor());
            tileRecover.setEnableMove(false);
            recover.add(tileRecover);
            if (tile.getTileColor() == board.LIGHT_YELLOW) {
                tile.setTileColor(board.LIGHT_BLUE);
            } else if (tile.getTileColor() == board.BROWN ) {
                tile.setTileColor(board.HIGHLIGHT_BLUE);
            } else {
                tile.setTileColor(board.RED);
            }
            tile.setEnableMove(true);
        }


        Tile tile = App.board.tiles[x][y];
        Tile tileRecover = new Tile(tile.getP(),tile.getCol(),tile.getRow(),tile.getTileColor());
        App.board.tiles[x][y].setTileColor(board.GREEN);
        recover.add(tileRecover);

        return recover;
    }




    public Boolean getWhite() {
        return isWhite;
    }



    public Manager.PieceType getPieceType() {
        return pieceType;
    }


    public int getValue() {
        return pieceType.getIntValue();
    }

    public int getPositionScore() {
        col = Math.min(col,13-col);
        row = Math.min(row,13-row);
        return (col*row);
    }
}

