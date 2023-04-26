package XXLChess;

import processing.core.PApplet;
import processing.core.PImage;

import java.io.IOException;
import java.util.ArrayList;

public class Board {

    private static final int TILE_SIZE = 48;
    private static final int SIDE_BAR_WIDTH = 120;
    private static final int BOARD_WIDTH = 14;
    private static final int BOARD_HEIGHT = 14;


    public static final int HIGHLIGHT_BLUE = 1;
    public static final int LIGHT_BLUE = 2;
    public static final int GREEN = 3;
    public static final int ORANGE = 4;

    public static final int BROWN = 5;

    public static final int LIGHT_YELLOW = 6;
    public static final int GREEN_YELLOW = 7;
    public static final int RED = 8;




    private PApplet p;
    public Tile[][] tiles;

    private boolean selected = false;
    private ArrayList<Tile> recover;

    private Tile selectedTile;


    public Board(PApplet parent, PImage[] sprites) throws IOException {
        this.p = parent;
        initTiles();
    }


    private void initTiles() throws IOException {
        tiles = new Tile[BOARD_WIDTH][BOARD_HEIGHT];
        int tileColor;
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                if ((i + j) % 2 == 0) {
                    tileColor = LIGHT_YELLOW;
                } else {
                    tileColor = BROWN;
                }
                tiles[i][j] = new Tile(p, i, j, tileColor);
            }
        }

        //init Pieces
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                switch (Manager.chessBoard[j][i]){
                    case 'P':
                        tiles[i][j].setPiece(App.sprites[14], 14, false);
                        break;
                    case 'p':
                        tiles[i][j].setPiece(App.sprites[29], 29, true);
                        break;
                    case 'R':
                        tiles[i][j].setPiece(App.sprites[13], 13, false);
                        break;
                    case 'r':
                        tiles[i][j].setPiece(App.sprites[28], 28, true);
                        break;
                    case 'N':
                        tiles[i][j].setPiece(App.sprites[12], 12, false);
                        break;
                    case 'n':
                        tiles[i][j].setPiece(App.sprites[27], 27, true);
                        break;
                    case 'B':
                        tiles[i][j].setPiece(App.sprites[2], 2, false);
                        break;
                    case 'b':
                        tiles[i][j].setPiece(App.sprites[17], 17, true);
                        break;
                    case 'H':
                        tiles[i][j].setPiece(App.sprites[3], 3, false);
                        break;
                    case 'h':
                        tiles[i][j].setPiece(App.sprites[18], 18, true);
                        break;
                    case 'C':
                        tiles[i][j].setPiece(App.sprites[9], 9, false);
                        break;
                    case 'c':
                        tiles[i][j].setPiece(App.sprites[24], 24, true);
                        break;
                    case 'G':
                        tiles[i][j].setPiece(App.sprites[8], 8, false);
                        break;
                    case 'g':
                        tiles[i][j].setPiece(App.sprites[23], 23, true);
                        break;
                    case 'A':
                        tiles[i][j].setPiece(App.sprites[6], 6, false);
                        break;
                    case 'a':
                        tiles[i][j].setPiece(App.sprites[21], 21, true);
                        break;
                    case 'K':
                        tiles[i][j].setPiece(App.sprites[7], 7, false);
                        break;
                    case 'k':
                        tiles[i][j].setPiece(App.sprites[22], 22, true);
                        break;
                    case 'E':
                        tiles[i][j].setPiece(App.sprites[10], 10, false);
                        break;
                    case 'e':
                        tiles[i][j].setPiece(App.sprites[25], 25, true);
                        break;
                    case 'Q':
                        tiles[i][j].setPiece(App.sprites[30], 30, false);
                        break;
                    case 'q':
                        tiles[i][j].setPiece(App.sprites[31], 31, true);
                        break;

                }
            }
        }

    }

    /**
     * Draw the chess board, including the tiles and any highlights.
     */
    public void drawBoard() {
        // Draw the tiles
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                tiles[i][j].draw();
            }
        }

    }

    public void clickEvent(int mouseX, int mouseY) {
        if(!App.manager.playerTurn){
            return;
        }

        int x = mouseX / 48;
        int y = mouseY / 48;

        //the first click
        if(!selected){
            if(tiles[x][y].getPiece() != null&&tiles[x][y].getPiece().getWhite()==App.manager.playerColourIsWhite){
                selected=true;
                this.selectedTile = tiles[x][y];
            }else{
                selected=false;
            }

            if (selected) {
                this.recover=tiles[x][y].getPiece().drawValidTiles(this,x,y);
            }
            return;
        }

        //the second click, move or cancel selected state.
        if(selected){
            if(tiles[x][y].isEnableMove()){
                Tile targetTile = tiles[x][y];

                App.manager.x = selectedTile.getCol()*48;
                App.manager.y = selectedTile.getRow()*48;
                App.manager.selectedTile = this.selectedTile;
                App.manager.targetTile = targetTile;


                Piece newPiece = new Piece(selectedTile.getPiece().getPieceType(),x,y,selectedTile.getPiece().getWhite(),selectedTile.getPiece().getImg());
                App.manager.selectedPiece = newPiece;
                tiles[selectedTile.getCol()][selectedTile.getRow()].removePiece();
                App.manager.exectMove = true;
                App.manager.playerTurn = false;
                App.manager.playerLeftTime= App.manager.playerLeftTime+App.manager.playerIncrement;
            }
            if(recover.size()!=0){
                recoverTiles(recover);
            }
            selected = false;
            return;
        }

    }



    private void recoverTiles(ArrayList<Tile> recover) {
        for(Tile tile:recover){
            tiles[tile.getCol()][tile.getRow()].setTileColor(tile.getTileColor());
            tiles[tile.getCol()][tile.getRow()].setEnableMove(tile.isEnableMove());
        }
    }

}