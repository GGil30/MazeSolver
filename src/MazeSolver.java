/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

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
        Stack<MazeCell> st = new Stack<MazeCell>();
        int row = maze.getEndCell().getRow();
        int col = maze.getEndCell().getCol();
        int tempRow;
        int tempCol;

        while(!(row == maze.getStartCell().getRow() && col == maze.getStartCell().getCol())){
            st.add(maze.getCell(row, col));
            tempRow = row;
            tempCol = col;
            row = maze.getCell(tempRow, tempCol).getParent().getRow();
            col = maze.getCell(tempRow, tempCol).getParent().getCol();
        }
        // Should be from start to end cells

        // Put everything in ArrayList
        ArrayList<MazeCell> solution = new ArrayList<MazeCell>();
        solution.add(maze.getStartCell());
        while(!st.empty()){
            solution.add(st.pop());
        }
        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Stack<MazeCell> cellsToVisit = new Stack<MazeCell>();
        int row = maze.getStartCell().getRow();
        int col = maze.getStartCell().getCol();
        while(!(row == maze.getEndCell().getRow() && col == maze.getEndCell().getCol())){
            if(maze.isValidCell(row-1, col)) {
                cellsToVisit.add(maze.getCell(row-1, col));
                maze.getCell(row-1, col).setExplored(true);
                maze.getCell(row-1, col).setParent(maze.getCell(row, col));
            }
            if(maze.isValidCell(row, col+1)) {
                cellsToVisit.add(maze.getCell(row, col+1));
                maze.getCell(row, col+1).setExplored(true);
                maze.getCell(row, col+1).setParent(maze.getCell(row, col));
            }
            if(maze.isValidCell(row+1, col)) {
                cellsToVisit.add(maze.getCell(row+1, col));
                maze.getCell(row+1, col).setExplored(true);
                maze.getCell(row+1, col).setParent(maze.getCell(row, col));
            }
            if(maze.isValidCell(row, col-1)) {
                cellsToVisit.add(maze.getCell(row, col-1));
                maze.getCell(row, col-1).setExplored(true);
                maze.getCell(row, col-1).setParent(maze.getCell(row, col));
            }
            row = cellsToVisit.peek().getRow();
            col = cellsToVisit.pop().getCol();

        }

        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Queue<MazeCell> cellsToVisit = new LinkedList<MazeCell>();
        int row = maze.getStartCell().getRow();
        int col = maze.getStartCell().getCol();
        while(!(row == maze.getEndCell().getRow() && col == maze.getEndCell().getCol())){
            if(maze.isValidCell(row-1, col)) {
                cellsToVisit.add(maze.getCell(row-1, col));
                maze.getCell(row-1, col).setExplored(true);
                maze.getCell(row-1, col).setParent(maze.getCell(row, col));
            }
            if(maze.isValidCell(row, col+1)) {
                cellsToVisit.add(maze.getCell(row, col+1));
                maze.getCell(row, col+1).setExplored(true);
                maze.getCell(row, col+1).setParent(maze.getCell(row, col));
            }
            if(maze.isValidCell(row+1, col)) {
                cellsToVisit.add(maze.getCell(row+1, col));
                maze.getCell(row+1, col).setExplored(true);
                maze.getCell(row+1, col).setParent(maze.getCell(row, col));
            }
            if(maze.isValidCell(row, col-1)) {
                cellsToVisit.add(maze.getCell(row, col-1));
                maze.getCell(row, col-1).setExplored(true);
                maze.getCell(row, col-1).setParent(maze.getCell(row, col));
            }
            row = cellsToVisit.peek().getRow();
            col = cellsToVisit.remove().getCol();

        }

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
