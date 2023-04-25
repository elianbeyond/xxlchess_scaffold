package XXLChess;

import processing.core.PApplet;
import processing.data.JSONObject;

import java.io.*;

import static processing.core.PApplet.loadJSONObject;

//This class judges whether it is a round of white or black, and calculates the score and time这个类判断是白子还是黑子的回合，计算得分和时间
public class Manager {
    private PApplet p;
    public Boolean exectMove;
    public int x;
    public int y;
    public String layout;
    public Tile selectedTile;
    public Tile targetTile;
    public Piece selectedPiece;
    public boolean playerColourIsWhite;

    public boolean playerTurn = true;
    public int playerLeftTime;
    public int computerLeftTime;
    public int playerIncrement;
    public int computerIncrement;
    public static char[][] chessBoard;
    public Manager (PApplet parent,Boolean exectMove){
        this.p= parent;
        this.exectMove = exectMove;
    }

    public void getConfig() throws FileNotFoundException {
        //readJsonFile
        File f = new File("config.json");
        JSONObject jsonObject = loadJSONObject(f);

        //访问JSONObject的属性
        this.layout= jsonObject.getString("layout");

        String playerColourString = jsonObject.getString("player_colour");
        playerColourIsWhite = playerColourString.contains("white");
        playerLeftTime =jsonObject.getJSONObject("time_controls").getJSONObject("player").getInt("seconds");
        playerIncrement =jsonObject.getJSONObject("time_controls").getJSONObject("player").getInt("increment");
        computerLeftTime =jsonObject.getJSONObject("time_controls").getJSONObject("cpu").getInt("seconds");
        computerIncrement =jsonObject.getJSONObject("time_controls").getJSONObject("cpu").getInt("increment");

    }
    public void getLayout() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(layout));
        chessBoard = new char[14][14];
        //逐行读取文件
        String line;
        int row = 0;
        while ((line = reader.readLine()) != null) {
            //将每行的字符转换为字符数组
            char[] charArray = line.toCharArray();

            //将字符数组存储在二维数组中
            for (int col = 0; col < charArray.length; col++) {
                chessBoard[row][col] = charArray[col];
            }
            row++;
        }
        //关闭文件
        reader.close();

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
            this.playerTurn = !this.playerTurn;
            if(this.y==6*48&&selectedPiece.getPieceName().contains("pawn")&&selectedPiece.getWhite()){
                selectedPiece.setPieceName("w-queen");
                selectedPiece.setImg(App.sprites[31]);
            }
            if(this.y==7*48&&selectedPiece.getPieceName().contains("pawn")&&!selectedPiece.getWhite()){
                selectedPiece.setPieceName("b-queen");
                selectedPiece.setImg(App.sprites[30]);
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
