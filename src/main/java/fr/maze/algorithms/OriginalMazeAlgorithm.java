package fr.maze.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.maze.MazeAlgorithm;
import fr.maze.model.Cell;

public class OriginalMazeAlgorithm extends MazeAlgorithm {

    public OriginalMazeAlgorithm(int rows, int columns) {
        super(rows, columns);

    }

    @Override
    public void initialize() {
        // initialize the maze
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                grid[row][column] = new Cell(row, column);
            }
        }

        for (Cell[] gridRow : grid) {
            for (Cell cell : gridRow) {
                int row = cell.getRow();
                int column = cell.getColumn();

                cell.setNorth(getGridCell(row - 1, column, grid, rows, columns));
                cell.setSouth(getGridCell(row + 1, column, grid, rows, columns));
                cell.setWest(getGridCell(row, column - 1, grid, rows, columns));
                cell.setEast(getGridCell(row, column + 1, grid, rows, columns));
            }
        }
    }

    @Override
    public void compute() {
        Random r = new Random();
        for (Cell[] gridRow : grid) {
            for (Cell cell : gridRow) {
                List<Cell> neighbors = new ArrayList<Cell>();
                if (cell.getNorth() != null) {
                    neighbors.add(cell.getNorth());
                }
                if (cell.getEast() != null) {
                    neighbors.add(cell.getEast());
                }

                Cell neighborCell = null;
                if (neighbors.size() > 0) {
                    int randomIndex = r.ints(1, 0, neighbors.size()).findFirst().getAsInt();
                    neighborCell = neighbors.get(randomIndex);
                }

                if (neighborCell != null) {
                    cell.getNeighbors().put(neighborCell, true);
                    neighborCell.getNeighbors().put(cell, true);
                }
            }
        }
    }

}
