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
    public boolean aiTurn = false;
    public int playerLeftTime;
    public int computerLeftTime;
    public int playerIncrement;
    public int computerIncrement;
    public static char[][] chessBoard;
    public enum PieceType{
        PAWN(10),
        ROOK(50),
        KNIGHT(20),
        KNIGHT_KING(50),
        BISHOP(30),
        ARCHBISHOP(70),
        CAMEL(20),
        GUARD(50),
        AMAZON(120),
        KING(Integer.MAX_VALUE),
        CHANCELLOR(80),
        QUEEN(90);
        private final int intValue;

        private PieceType(int intValue) {
            this.intValue = intValue;
        }

        public int getIntValue() {
            return intValue;
        }
    }

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
            if(!playerTurn){
                playerTurn = true;
            }else{
                playerTurn =false;
                aiTurn = true;
            }




            if(this.y==6*48&&selectedPiece.getPieceType().equals(PieceType.PAWN)&&selectedPiece.getWhite()){
                selectedPiece.setPieceType(PieceType.QUEEN);
                selectedPiece.setImg(App.sprites[31]);
            }
            if(this.y==7*48&&selectedPiece.getPieceType().equals(PieceType.PAWN)&&!selectedPiece.getWhite()){
                selectedPiece.setPieceType(PieceType.QUEEN);
                selectedPiece.setImg(App.sprites[30]);
            }

            selectedPiece.setCol(targetTile.getCol());
            selectedPiece.setRow(targetTile.getRow());
            App.board.tiles[targetTile.getCol()][targetTile.getRow()].setPiece(selectedPiece);


        }
    }






    public void computerMove() {
        //ai只执行一次
        aiTurn = false;



        AlphaBetaPruning alphaBetaPruning = new AlphaBetaPruning(!playerColourIsWhite,new BoardState(App.board));
        Move move = alphaBetaPruning.findBestMove();
        //执行移动，修改状态


//        //test move
//        Move move = new Move(App.board.tiles[5][12].getPiece(),5,11);

        selectedTile = App.board.tiles[move.getPiece().col][move.getPiece().row];
        targetTile = App.board.tiles[move.getX()][move.getY()];


        Piece newPiece = new Piece(selectedTile.getPiece().getPieceType(),selectedTile.getPiece().col,selectedTile.getPiece().row,selectedTile.getPiece().getWhite(),selectedTile.getPiece().getImg());
        App.manager.selectedPiece = newPiece;
        App.board.tiles[selectedTile.getCol()][selectedTile.getRow()].removePiece();
        x=selectedTile.getCol()*48;
        y=selectedTile.getRow()*48;
        exectMove = true;


        App.manager.playerLeftTime= App.manager.playerLeftTime+App.manager.playerIncrement;



        System.out.println("computer move");


    }
    public void drawPiece(){
        p.image(selectedPiece.getImg(), this.x, this.y,48,48);

    }

}
