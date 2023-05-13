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
    boolean whiteKingCaptured;
    boolean blackKingCaptured;
    public static int  moveTimeCounter;
    public int maxMovementTime;

    String endInfo;
    boolean gameOver;
    public enum PieceType{
        PAWN(1),
        ROOK(5.25),
        KNIGHT(2),
        KNIGHT_KING(50),
        BISHOP(3.625),
        ARCHBISHOP(7.5),
        CAMEL(2),
        GUARD(5),
        AMAZON(12),
        KING(99999),
        CHANCELLOR(8.5),
        QUEEN(9.5);
        private final double doubleValue;

        private PieceType(double doubleValue) {
            this.doubleValue = doubleValue;
        }

        public double getDoubleValue() {
            return doubleValue;
        }
    }

    public Manager (PApplet parent,Boolean exectMove){
        this.p= parent;
        this.exectMove = exectMove;
        whiteKingCaptured = false;
        blackKingCaptured = false;
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
        maxMovementTime=jsonObject.getInt("max_movement_time");

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




        if ((this.x != targetX || this.y != targetY)&&moveTimeCounter<maxMovementTime*60) { // 到达目标点后的处理

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
            //end condition
            Piece capturedPiece = App.board.tiles[targetTile.getCol()][targetTile.getRow()].getPiece();
            if(capturedPiece!=null){
                if(capturedPiece.getPieceType().toString().equals("KING")){
                    if(capturedPiece.getWhite()){
                        this.whiteKingCaptured = true;
                    }else{
                        this.blackKingCaptured = true;
                    }
                }
            }
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

        if(this.gameOver){
            return;
        }
        //ai只执行一次
        aiTurn = false;

        HeuristicAI alphaBetaPruning = new HeuristicAI(!playerColourIsWhite,new BoardState(App.board));
        Move move = alphaBetaPruning.findBestMove();
        if(move==null){
            return;
        }
        //执行移动，修改状态
        Piece capturedPiece = App.board.tiles[move.getX()][move.getY()].getPiece();

        if(capturedPiece!=null){
            if(capturedPiece.getPieceType().toString().equals("KING")){
                if(capturedPiece.getWhite()){
                    this.whiteKingCaptured = true;
                }else{
                    this.blackKingCaptured = true;
                }
            }
        }


//        //test move
//        Move move = new Move(App.board.tiles[5][12].getPiece(),5,11);

        selectedTile = App.board.tiles[move.getPiece().col][move.getPiece().row];
        selectedTile.removePiece();
        targetTile = App.board.tiles[move.getX()][move.getY()];


        Piece newPiece = new Piece(move.getPiece().getPieceType(),move.getPiece().col,move.getPiece().row,move.getPiece().getWhite(),move.getPiece().getImg());
        App.manager.selectedPiece = newPiece;
        App.board.tiles[selectedTile.getCol()][selectedTile.getRow()].removePiece();
        x=selectedTile.getCol()*48;
        y=selectedTile.getRow()*48;
        exectMove = true;
        moveTimeCounter = 0;

        App.manager.playerLeftTime= App.manager.playerLeftTime+App.manager.playerIncrement;



        System.out.println("computer move");




    }
    public void drawPiece(){
        p.image(selectedPiece.getImg(), this.x, this.y,48,48);

    }

}
