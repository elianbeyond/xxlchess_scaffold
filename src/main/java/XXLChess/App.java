package XXLChess;

//import org.reflections.Reflections;
//import org.reflections.scanners.Scanners;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.event.MouseEvent;

import java.io.File;
import java.io.IOException;

public class App extends PApplet {


    public static final int CELLSIZE = 48;
    public static final int SIDEBAR = 120;
    public static final int BOARD_WIDTH = 14;

    public static int WIDTH = CELLSIZE*BOARD_WIDTH+SIDEBAR;
    public static int HEIGHT = BOARD_WIDTH*CELLSIZE;

    public static final int FPS = 60;
    public static int  startTime;

	
    public String configPath;
    public static Manager manager;

    public App() {
        this.configPath = "config.json";
    }

    /**
     * Initialise the setting of the window size.
    */
    public void settings() {
        size(WIDTH, HEIGHT);
    }
    public static PImage[] sprites = new PImage[32];
    public static String[] filenames = new String[] {
            "b-rook.png",
            "b-knight.png",
            "b-bishop.png",
            "b-archbishop.png",
            "b-camel.png",
            "b-knight-king.png",
            "b-amazon.png",
            "b-king.png",
            "b-knight-king.png",
            "b-camel.png",
            "b-chancellor.png",
            "b-bishop.png",
            "b-knight.png",
            "b-rook.png",
            "b-pawn.png",
            "w-rook.png",
            "w-knight.png",
            "w-bishop.png",
            "w-archbishop.png",
            "w-camel.png",
            "w-knight-king.png",
            "w-amazon.png",
            "w-king.png",
            "w-knight-king.png",
            "w-camel.png",
            "w-chancellor.png",
            "w-bishop.png",
            "w-knight.png",
            "w-rook.png",
            "w-pawn.png",
            "b-queen.png",
            "w-queen.png",
    };
    public static Board board;

    /**
     * Load all resources such as images. Initialise the elements such as the player, enemies and map elements.
    */
    public void setup() {
        frameRate(FPS);
        startTime = millis();

        // Load images during setup
        manager =new Manager(this,false);
        try {

            manager.getConfig();
            manager.getLayout();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // PImage spr = loadImage("src/main/resources/XXLChess/"+...);
        for (int i = 0; i < filenames.length; i++) {
            sprites[i] = loadImage("src/main/resources/XXLChess/" + filenames[i]);
        }
        try {
            this.board = new Board(this, sprites);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // load config
        JSONObject conf = loadJSONObject(new File(this.configPath));



    }

    /**
     * Receive key pressed signal from the keyboard.
    */
    public void keyPressed(){


    }
    
    /**
     * Receive key released signal from the keyboard.
    */
    public void keyReleased(){

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(manager.gameOver){
            return;
        }else{
            this.board.clickEvent(mouseX,mouseY);
        }


    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    /**
     * Draw all elements in the game by current frame. 
    */
    public void draw() {

        background(200); // add a background color to avoid flickering



        if(!manager.gameOver){
            // 显示玩家1和玩家2的剩余时间
            int elapsedTime = (millis() - startTime) / 1000;

            // 更新玩家1和玩家2的剩余时间
            int player1Time = manager.playerLeftTime - elapsedTime;
            int player2Time = manager.computerLeftTime - elapsedTime;
            textAlign(CENTER);
            textSize(20);
            fill(255); // 设置文本颜色为白色
            text(player1Time/60+":"+player1Time%60, 48*15, 48*2);
            text(player2Time/60+":"+player2Time%60, 48*15, 48*10);
        }

        // draw the board
        this.board.drawBoard();
        //test
        //
        if(manager.exectMove){
            manager.moveTimeCounter ++;
            manager.speedControl();
            manager.drawPiece();
        }

        if(!manager.playerTurn&&manager.aiTurn){
            this.manager.computerMove();
            manager.computerLeftTime = manager.computerLeftTime+manager.computerIncrement;
        }
        if(manager.whiteKingCaptured){
            manager.gameOver = true;
            if(manager.playerColourIsWhite){
                manager.endInfo = "player lose";
            }else{
                manager.endInfo = "player win";
            }
            manager.computerLeftTime = manager.computerLeftTime+manager.computerIncrement;

        }
        if(manager.blackKingCaptured){
            manager.gameOver = true;
            if(manager.playerColourIsWhite){
                manager.endInfo = "player win";
            }else{
                manager.endInfo = "player lose";
            }
            manager.blackKingCaptured = !manager.blackKingCaptured;
        }

        if(manager.gameOver){
            // 显示
            textAlign(CENTER);
            textSize(20);
            fill(255); // 设置文本颜色为白色
            text(manager.endInfo, 48*15+10, 48*6);
        }
        
    }
	
	// Add any additional methods or attributes you want. Please put classes in different files.


    public static void main(String[] args) {
        System.out.println(filenames[0]);
        PApplet.main("XXLChess.App");
    }

}
