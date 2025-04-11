// Gabriel Gil, April 10, March 2025

/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

// Import the necessary classes
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        // Create variables to store the startCell row and col values to avoid repeatedly calling those getter methods
        int startCellRow =  maze.getStartCell().getRow();
        int startCellCol = maze.getStartCell().getCol();

        // Create a stack to store the MazeCells in the solution as we traverse backwards through the solution
        Stack<MazeCell> st = new Stack<MazeCell>();

        // Create a MazeCell to keep track of which cell we are currently on
        MazeCell currentCell = maze.getEndCell();

        // While we haven't reached the start cell, keep adding the cell to the stack and moving to its parent as to go
        // backwards through the solution
        while(!(currentCell.getRow() == startCellRow && currentCell.getCol() == startCellCol)){
            st.add(currentCell);
            currentCell = currentCell.getParent();
        }

        // Now we have the cells in the solution in a stack, with the first step/cell at the top of the stack and the
        // last step/cell at the end of the stack. Create an arrayList to store the solution in the correct order and
        // add the startCell
        ArrayList<MazeCell> solution = new ArrayList<MazeCell>();
        solution.add(maze.getStartCell());

        // While the stack isn't empty, keep adding the cells in the stack to the solution arrayList
        while(!st.empty()){
            solution.add(st.pop());
        }
        // Return the solution arrayList
        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        // Create a cellsToVisit stack since we are doing depth-first search, so cellsToVisit is a stack
        Stack<MazeCell> cellsToVisit = new Stack<MazeCell>();

        // Create a MazeCell to keep track of which cell we are currently on
        MazeCell currentCell = maze.getStartCell();

        // Create variables to store the endCell row and col values to avoid repeatedly calling those getter methods
        int endCellRow = maze.getEndCell().getRow();
        int endCellCol = maze.getEndCell().getCol();

        // While the currentCell is not the endCell, "explore" or add the cells around it to the cellsToVisit stack in
        // a North, East, South, West order assuming that those cells are valid to be explored. If those cells are
        // valid to be explored, correctly assign their parent as the currentCell and set their "explored" to true
        while(!(currentCell.getRow() == endCellRow && currentCell.getCol() == endCellCol)) {
            int row = currentCell.getRow();
            int col = currentCell.getCol();

            // Explore the cells in a North, East, South, West order
            if (maze.isValidCell(row - 1, col)) {
                cellsToVisit.add(maze.getCell(row - 1, col));
                maze.getCell(row - 1, col).setExplored(true);
                maze.getCell(row - 1, col).setParent(currentCell);
            }
            if (maze.isValidCell(row, col + 1)) {
                cellsToVisit.add(maze.getCell(row, col + 1));
                maze.getCell(row, col + 1).setExplored(true);
                maze.getCell(row, col + 1).setParent(currentCell);
            }
            if (maze.isValidCell(row + 1, col)) {
                cellsToVisit.add(maze.getCell(row + 1, col));
                maze.getCell(row + 1, col).setExplored(true);
                maze.getCell(row + 1, col).setParent(currentCell);
            }
            if (maze.isValidCell(row, col - 1)) {
                cellsToVisit.add(maze.getCell(row, col - 1));
                maze.getCell(row, col - 1).setExplored(true);
                maze.getCell(row, col - 1).setParent(currentCell);
            }
            // Set current cell to the cell at the top of cellsToVisit
            currentCell = cellsToVisit.pop();
        }
        // Call getSolution on the maze now that we have done a depth first search and return that solution
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        // Create a cellsToVisit queue since we are doing breadth-first search, so cellsToVisit is a queue
        Queue<MazeCell> cellsToVisit = new LinkedList<MazeCell>();

        // Create a MazeCell to keep track of which cell we are currently on
        MazeCell currentCell = maze.getStartCell();

        // Create variables to store the endCell row and col values to avoid repeatedly calling those getter methods
        int endCellRow = maze.getEndCell().getRow();
        int endCellCol = maze.getEndCell().getCol();

        // While the currentCell is not the endCell, "explore" or add the cells around it to the cellsToVisit queue in
        // a North, East, South, West order assuming that those cells are valid to be explored. If those cells are
        // valid to be explored, correctly assign their parent as the currentCell and set their "explored" to true
        while(!(currentCell.getRow() == endCellRow && currentCell.getCol() == endCellCol)){
            int row = currentCell.getRow();
            int col = currentCell.getCol();

            // Explore the cells in a North, East, South, West order
            if(maze.isValidCell(row-1, col)) {
                cellsToVisit.add(maze.getCell(row-1, col));
                maze.getCell(row-1, col).setExplored(true);
                maze.getCell(row-1, col).setParent(currentCell);
            }
            if(maze.isValidCell(row, col+1)) {
                cellsToVisit.add(maze.getCell(row, col+1));
                maze.getCell(row, col+1).setExplored(true);
                maze.getCell(row, col+1).setParent(currentCell);
            }
            if(maze.isValidCell(row+1, col)) {
                cellsToVisit.add(maze.getCell(row+1, col));
                maze.getCell(row+1, col).setExplored(true);
                maze.getCell(row+1, col).setParent(currentCell);
            }
            if(maze.isValidCell(row, col-1)) {
                cellsToVisit.add(maze.getCell(row, col-1));
                maze.getCell(row, col-1).setExplored(true);
                maze.getCell(row, col-1).setParent(currentCell);
            }
            // Set current cell to the cell that is first in line to be removed from the cellsToVisit queue
            currentCell = cellsToVisit.remove();
        }
        // Call getSolution on the maze now that we have done a breadth first search and return that solution
        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
