package XXLChess;

import processing.core.PApplet;
import processing.core.PImage;

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
    public static Tile[][] tiles;
    private boolean selected = false;
    private ArrayList<Tile> recover;

    private Tile selectedTile;


    public Board(PApplet parent, PImage[] sprites) {
        this.p = parent;
        initTiles();
    }


    private void initTiles() {
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
            tiles[i][0].setPiece(App.sprites[i], i, false);
            tiles[i][1].setPiece(App.sprites[14], 14, false);
            tiles[i][13].setPiece(App.sprites[i + 15], i + 15, true);
            tiles[i][12].setPiece(App.sprites[29], 29, true);
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


        int x = mouseX / 48;
        int y = mouseY / 48;
        if(selected&&tiles[x][y].isEnableMove()){
            Tile targetTile = tiles[x][y];
            drawMove(selectedTile,targetTile);
            selected=false;
            recoverTiles(recover);
            return;
        }

        if(!selected&&tiles[x][y].getPiece() != null){
            selected=true;
            this.selectedTile = tiles[x][y];
        }else{
            selected=false;
        }
        if(!selected&&recover.size()!=0){
            recoverTiles(recover);
        }

        if (selected) {
            this.recover=tiles[x][y].getPiece().drawValidTiles();
        }


    }

    private void drawMove(Tile selectedTile,Tile targetTile ) {
        Piece newPiece = new Piece(selectedTile.getPiece().getPieceName(),selectedTile.getPiece().getCurrentTile(),selectedTile.getPiece().getWhite(),selectedTile.getPiece().getImg());

        tiles[selectedTile.getCol()][selectedTile.getRow()].getPiece().setImg(null);

        int currentX = selectedTile.getCol()*48;
        int currentY = selectedTile.getRow()*48;
        int targetX = targetTile.getCol()*48;
        int targetY = targetTile.getRow()*48;


        while (currentX != targetX || currentY != targetY) { // 到达目标点后的处理

            if (currentX < targetX) { // 判断是否到达目标点
                currentX += 1; // 每一帧移动的距离
            }
            if(currentX > targetX){
                currentX-=1;
            }
            if (currentY < targetY) {
                currentY += 1;
            }
            if(currentY>targetY){
                currentY-=1;
            }
            p.image(newPiece.getImg(), currentX,currentY , 48, 48);

        }
        // do something



        tiles[selectedTile.getCol()][selectedTile.getRow()].removePiece();

        //update targetTile
        newPiece.setCurrentTile(targetTile);
        tiles[targetTile.getCol()][targetTile.getRow()].setPiece(newPiece);
    }

    private void recoverTiles(ArrayList<Tile> recover) {
        for(Tile tile:recover){
            tiles[tile.getCol()][tile.getRow()].setTileColor(tile.getTileColor());
            tiles[tile.getCol()][tile.getRow()].setEnableMove(tile.isEnableMove());
        }
    }


    /**
     * Draw a blue highlight at the given tile coordinates.
     */
    private void drawBlueHighlight(int x, int y) {
        p.fill(0, 0, 255, 100);
        p.rect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
}