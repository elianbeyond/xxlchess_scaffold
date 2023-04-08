package XXLChess;

//import org.reflections.Reflections;
//import org.reflections.scanners.Scanners;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.event.MouseEvent;

import java.io.File;

public class App extends PApplet {

    public static final int SPRITESIZE = 480;
    public static final int CELLSIZE = 48;
    public static final int SIDEBAR = 120;
    public static final int BOARD_WIDTH = 14;

    public static int WIDTH = CELLSIZE*BOARD_WIDTH+SIDEBAR;
    public static int HEIGHT = BOARD_WIDTH*CELLSIZE;

    public static final int FPS = 60;
	
    public String configPath;

    public App() {
        this.configPath = "config.json";
    }

    /**
     * Initialise the setting of the window size.
    */
    public void settings() {
        size(WIDTH, HEIGHT);
    }
    public static PImage[] sprites = new PImage[30];
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
    };
    private Board board;

    /**
     * Load all resources such as images. Initialise the elements such as the player, enemies and map elements.
    */
    public void setup() {
        frameRate(FPS);

        // Load images during setup


        String[] filenames = new String[] {
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
        };

        // PImage spr = loadImage("src/main/resources/XXLChess/"+...);
        for (int i = 0; i < filenames.length; i++) {
            sprites[i] = loadImage("src/main/resources/XXLChess/" + filenames[i]);
        }
        this.board = new Board(this, sprites);

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
        this.board.clickEvent(mouseX,mouseY);

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    /**
     * Draw all elements in the game by current frame. 
    */
    public void draw() {

        background(200); // add a background color to avoid flickering

        // draw the board
        this.board.drawBoard();
        //test
        
    }
	
	// Add any additional methods or attributes you want. Please put classes in different files.


    public static void main(String[] args) {PApplet.main("XXLChess.App");
    }

}
