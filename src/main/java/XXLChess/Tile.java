package XXLChess;

import processing.core.PApplet;
import processing.core.PImage;

import static XXLChess.Manager.PieceType.*;

public class Tile {

    private PApplet p;

    public PApplet getP() {
        return p;
    }

    private int x, y;

    public boolean isEnableMove() {
        return enableMove;
    }

    public void setEnableMove(boolean enableMove) {
        this.enableMove = enableMove;
    }

    private boolean enableMove;

    public void setTileColor(int tileColor) {
        this.tileColor = tileColor;
    }

    private int tileColor;

    public int getTileColor() {
        return tileColor;
    }




    private Piece piece;

    public Tile(PApplet p, int x, int y,int tileColor) {
        this.p = p;
        this.x = x;
        this.y = y;
        this.enableMove = false;
        this.tileColor=  tileColor;
        this.piece =null;
    }
    public Tile(PApplet p, int x, int y,int tileColor,boolean enableMove) {
        this.p = p;
        this.x = x;
        this.y = y;
        this.enableMove = enableMove;
        this.tileColor=  tileColor;
        this.piece =null;
    }




    public void draw() {

        //draw tile
        switch(tileColor) {
            case 1:
                p.fill(170, 210, 220);
                break;
            case 2:
                p.fill(196, 225, 233);
                break;
            case 3:
                p.fill(106, 137, 77);
                break;
            case 4:
                p.fill(255, 163, 102);
                break;
            case 5:
                p.fill(177, 135, 99);
                break;
            case 6:
                p.fill(240, 218, 181);
                break;
            case 7:
                p.fill(170, 162, 58);
                break;
            case 8:
                p.fill(214, 0, 0);
                break;
            default:
                p.fill(255, 255, 153);
                break;
        }
        p.rect(x*App.CELLSIZE, y*App.CELLSIZE, App.CELLSIZE, App.CELLSIZE);

        //draw piece
        if(piece!=null &&piece.getImg()!=null){
            p.image(piece.getImg(), x*App.CELLSIZE, y*App.CELLSIZE,App.CELLSIZE,App.CELLSIZE);
        }

    }



    public void setPiece(PImage img,int i,boolean isWhite) {
        String temp = App.filenames[i];
        String[] pieceNames =temp.split("\\.");
        String pieceName = pieceNames[0];
        if (pieceName.contains("pawn")) {
            this.piece = new Piece(PAWN, x,y, isWhite, img);
        }
        else if (pieceName.contains("rook")) {
            this.piece = new Piece(ROOK, x,y, isWhite, img);
        }
        else if (pieceName.contains("king") && !pieceName.contains("knight")) {
            this.piece = new Piece(KING, x,y, isWhite, img);
        }
        else if (pieceName.contains("queen")) {
            this.piece = new Piece(QUEEN, x,y, isWhite, img);
        }
        else if (pieceName.contains("-bishop")) {
            this.piece = new Piece(BISHOP, x,y, isWhite, img);
        }
        else if (pieceName.contains("knight") && !pieceName.contains("king")) {
            this.piece = new Piece(KNIGHT, x,y, isWhite, img);
        }
        else if (pieceName.contains("camel")) {
            this.piece = new Piece(CAMEL, x,y, isWhite, img);
        }
        else if (pieceName.contains("knight-king")) {
            this.piece = new Piece(KNIGHT_KING, x,y, isWhite, img);
        }
        else if (pieceName.contains("amazon")) {
            this.piece = new Piece(AMAZON, x,y, isWhite, img);
        }
        else if (pieceName.contains("chancellor")) {
            this.piece = new Piece(CHANCELLOR, x,y, isWhite, img);
        }
        else if (pieceName.contains("archbishop")) {
            this.piece = new Piece(ARCHBISHOP, x,y, isWhite, img);
        }
    }
    public void setPiece(Piece piece ) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }
    public void removePiece() {
        this.piece = null;
    }


    public int getRow() {
        return y ;
    }

    public int getCol() {
        return x ;
    }

    public boolean isWhite() {
        return (getRow() + getCol()) % 2 == 0;
    }





}
