//package XXLChess;
//
//import processing.core.PApplet;
//import processing.data.JSONObject;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Scanner;
//
///**
// * Handles the action of every object in this game.
// */
//public class Manager {
//    /* Map Object*/
//    private Map map;
//    private char[][] textMap;
//
//    public Manager() {
//        this.map = new Map();
//        this.textMap = new char[13][15];
//    }
//
//    /**
//     * Read game config Json file and save the valid data.
//     *
//     * @return valid data for this game in config file.
//     */
//    public String readJsonFile() {
//        String data = "";
//
//        try {
//            File f = new File("config.json");
//            Scanner scan = new Scanner(f);
//            data = scan.useDelimiter("\\A").next();
//
//        } catch(FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        return data;
//    }
//
//    /**
//     * Configure the game by the data wrote in config file.
//     * Set game levels, game time, bomb guy lives, text map file path.
//     */
//    public void getConfig() {
//        String jsonText = readJsonFile();
//
//        JSONObject jsObj = JSONObject.parse(jsonText);
//
//        String layout = (String) jsObj.get("layout");
//
//
//
//        try {
//            File f = new File(layout);
//            Scanner scan = new Scanner(f);
//            int row = 0;
//            while(scan.hasNextLine()){
//                String data = scan.nextLine();
//
//                for (int i = 0; i < data.length(); i++) {
//                    this.textMap[row][i] = data.charAt(i);
//                }
//                row++;
//            }
//
//        } catch(FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Initializion the game.
//     * Put every objects back at the origin position as decribed in text map.
//     * Reset the time and enemies list.
//     */
//    public void initialization(PApplet app) {
//        this.map.initialization();
//        this.map.setTextMap(this.textMap);
//        this.map.collectPosition();
//
//
//    }
//
//
//
//}