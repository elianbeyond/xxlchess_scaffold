package XXLChess;

import processing.core.PApplet;
//This class judges whether it is a round of white or black, and calculates the score and time这个类判断是白子还是黑子的回合，计算得分和时间
public class Manager {
    private PApplet p;
    public Boolean exectMove;
    public int x;
    public int y;
    public Tile selectedTile;
    public Tile targetTile;
    public Piece selectedPiece;
    public Manager (PApplet parent,Boolean exectMove){
        this.p= parent;
        this.exectMove = exectMove;
    }
    public void speedControl(){


        int targetX = targetTile.getCol()*48;
        int targetY = targetTile.getRow()*48;

        if (this.x != targetX || this.y != targetY) { // 到达目标点后的处理

            if (this.x < targetX) { // 判断是否到达目标点
                this.x += 6; // 每一帧移动的距离
            }
            if(this.x > targetX){
                this.x-=6;
            }
            if (this.y < targetY) {
                this.y += 6;
            }
            if(this.y>targetY){
                this.y-=6;
            }


        }else{
            //When the conditions are met, pawn becomes queen
            this.exectMove =false;
            if(this.y==6*48&&selectedPiece.getPieceName().contains("pawn")&&selectedPiece.getWhite()){
                selectedPiece.setPieceName("w-queen");
                selectedPiece.setImg(App.sprites[31]);
            }

            selectedPiece.setCurrentTile(targetTile);
            Board.tiles[targetTile.getCol()][targetTile.getRow()].setPiece(selectedPiece);
        }
    }

    public void drawPiece(){
        p.image(selectedPiece.getImg(), this.x, this.y,48,48);
    }

    public Boolean getExectMove() {
        return exectMove;
    }

    public void setExectMove(Boolean exectMove) {
        this.exectMove = exectMove;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


}
