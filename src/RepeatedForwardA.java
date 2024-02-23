import java.util.ArrayList;

public class RepeatedForwardA {
    private Coordinate startState = new Coordinate(0, 0);
    private Coordinate agent = new Coordinate(0, 0);
    private static Coordinate goal;
    private int heuristics[][];
    // Open list, sort by f value, g(s) + h(s)
    // g(s) is from start state to current state, h(s) is heuristic (Manhattan distances)
    private BinaryHeap openList = new BinaryHeap();
    private ArrayList<Coordinate> openListTracker = new ArrayList<>();
    private ArrayList<Coordinate> closedList = new ArrayList<>();
    private Boolean[][] maze;
    private Boolean[][] emptyMaze;

    ArrayList<Coordinate> path = new ArrayList<Coordinate>();

    public RepeatedForwardA(Coordinate goal, int heuristics[][], Boolean[][] maze){
        this.goal = new Coordinate(goal.row, goal.col);
        this.heuristics = heuristics;
        this.maze = maze;
        agent.setFValue(calculateFValue(agent));
    }

    private int findGofS(Coordinate state){
        int gOfS = 0;
        int row = startState.row;
        int col = startState.col;
        while(row != state.row){
            row++;
            gOfS++;
        }
        while(col != state.col){
            col++;
            gOfS++;
        }
        return gOfS;
    }

    private ArrayList<Coordinate> aStar(Coordinate start){
        
    }

    private boolean findShortestPath(){
        int loopTracker = 0;
        openListTracker.add(agent);
        while(agent.row != goal.row || agent.col != goal.col){
            // First move from starting agent cell, knowing what neighbors
            // are blocked.
            // Place state in open list.
            //openList.addElement(agent);
            // Find current state neighbors and add current state to closed list
            if(listContains(agent, closedList)){
                return false;
            }
            ArrayList<Coordinate> validNeighbors = validNeighbors(agent);
            closedList.add(agent);
            for(int i = 0; i < validNeighbors.size(); i++){
                Coordinate neighbor = new Coordinate(validNeighbors.get(i).row,
                        validNeighbors.get(i).col);
                if(!listContains(neighbor, closedList) && !listContains(neighbor, openListTracker)){
                    neighbor.setFValue(calculateFValue(neighbor));
                    neighbor.addToPath(agent);
                    openList.addElement(neighbor);
                    openListTracker.add(neighbor);
                }
            }
            agent = openList.getMin();
            openList.deleteMin();
            openListTracker.remove(agent);
        }
        return true;
    }

    private boolean listContains(Coordinate c, ArrayList<Coordinate> a){
        for(int i = 0; i < a.size(); i++){
            if(c.row == a.get(i).row && c.col == a.get(i).col){
                return true;
            }
        }
        return false;
    }

    public void printCoords(){
        /*
        if(findShortestPath()){
            for(int i = 0; i < goal.path.size(); i++){
                System.out.println("(" + goal.path.get(i).row + ", "
                        + goal.path.get(i).col + ")");
            }
        }
        else{
            System.out.println("No solution found.");
        }
        */

        ArrayList<Coordinate> path = findShortestPath();
        if (path != null) {
            for (Coordinate coordinate : path) {
                System.out.println("(" + coordinate.row + ", " + coordinate.col + ")");
            }
        } else {
            System.out.println("No path found.");
        }
    }

    private int calculateFValue(Coordinate state){
        int fValue = findGofS(state) + heuristics[state.row][state.col];
        return fValue;
    }

    private ArrayList<Coordinate> validNeighbors(Coordinate c){
        ArrayList<Coordinate> neighbors = getNeighbors(c);
        ArrayList<Coordinate> validNeighbors = new ArrayList<>();
        for(int i = 0; i < neighbors.size(); i++){
            Coordinate n = neighbors.get(i);
            if(maze[n.row][n.col]){
                validNeighbors.add(n);
            }
        }
        return validNeighbors;
    }

    private ArrayList<Coordinate> getNeighbors(Coordinate c){
        ArrayList<Coordinate> neighbors = new ArrayList<>();
        // Down neighbor
        if(c.row + 1 < goal.row){
            Coordinate h = new Coordinate(c.row + 1, c.col, calculateFValue(new Coordinate(c.row + 1, c.col)));
            neighbors.add(h);
        }
        // Up neighbor
        if(c.row - 1 >= 0){
            neighbors.add(new Coordinate(c.row - 1, c.col, calculateFValue(new Coordinate(c.row - 1, c.col))));
        }
        // Left neighbor
        if(c.col - 1 >= 0){
            neighbors.add(new Coordinate(c.row, c.col - 1, calculateFValue(new Coordinate(c.row, c.col - 1))));
        }
        // Right neighbor
        if(c.col + 1 < goal.col){
            neighbors.add(new Coordinate(c.row, c.col + 1, calculateFValue(new Coordinate(c.row, c.col + 1))));
        }
        return neighbors;
    }
}
