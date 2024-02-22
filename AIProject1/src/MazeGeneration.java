import java.util.ArrayList;
import java.util.Random;
import java.util.*;

public class MazeGeneration {
    static int numbCols = 5;
    static int numbRows = 5;
    static int numbMazes = 3;
    static ArrayList<Boolean[][]> mazeList = new ArrayList<>();

    static double chanceBlocked = .3;
    static int randomSeed = 500;

    public static void main(String[] args) {
        generateMazes();
        drawMazes();
    }

    public static void generateMazes(){
        Random rand = new Random(randomSeed);
        //Start off on first maze.
        for(int i = 0; i < numbMazes; i++) {
            Boolean[][] maze = new Boolean[numbCols][numbRows];
            for(int j = 0; j < numbRows; j++){
                for(int k = 0; k < numbCols; k++){
                    double random = rand.nextInt(10);
                    random *= .1;
                    if((j == 0 && k == 0) || (j == numbRows - 1 && k == numbCols - 1)){
                        maze[j][k] = true;
                    }
                    else if(random <= chanceBlocked){
                        maze[j][k] = false;
                    }
                    else{
                        maze[j][k] = true;
                    }
                }
            }
            // At this point a full maze has been done generating, so add it to
            // finished maze array.
            mazeList.add(maze);
        }
    }

    public static void generateMazes2(){
        Stack<Agent> stack = new Stack<>();
        Random rand = new Random(randomSeed);
        Boolean[][] mazeVisited = new Boolean[numbCols][numbRows];
        //Start off on first maze.
        for(int i = 0; i < numbMazes; i++) {
            Boolean[][] mazeUnblocked = new Boolean[numbCols][numbRows];
            int startRow = rand.nextInt(numbRows);
            int startCol = rand.nextInt(numbCols);
            Agent a = new Agent(startRow, startCol);
            mazeVisited[startRow][startCol] = true;
            mazeUnblocked[startRow][startCol] = true;

        }
    }

    public static void findAndPickNeighbor(Agent a, Stack s){
        boolean picked = false;
        while(picked = false){

        }
        if(a.row + 1 < numbRows){
        }
        if(a.row - 1 >= 0){
        }
        if(a.col - 1 >= 0){
        }
        if(a.col + 1 < numbCols){
        }
    }

    public static void drawMazes(){
        for(int i = 0; i < numbMazes; i++){
            System.out.println("---------- MAZE " + (i + 1) + " ----------");
            for(int j = 0; j < numbRows; j++){
                for(int k = 0; k < numbCols; k++){
                    if (mazeList.get(i)[j][k] == true){
                        System.out.print("□ ");
                    }
                    else{
                        System.out.print("■ ");
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}