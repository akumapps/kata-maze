package fr.maze.helper;

import fr.maze.model.Cell;

public class ComplexitySolver {
    public static int complexity(Cell[][] grid) {
        int complexity = 0;
        int rows = grid.length;
        int columns = grid[0].length;

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Cell cell = grid[row][column];
                int neighborCount = 0;

                if (cell.getNorth() != null
                        && Boolean.TRUE.equals(cell.getNeighbors().get(cell.getNorth()))
                        && cell.getNorth().getColumn() > 0) {
                    neighborCount++;
                }
                if (cell.getSouth() != null && Boolean.TRUE.equals(cell.getNeighbors().get(cell.getSouth()))
                        && cell.getSouth().getColumn() < column - 1) {
                    neighborCount++;
                }
                if (cell.getEast() != null && Boolean.TRUE.equals(cell.getNeighbors().get(cell.getEast()))
                        && cell.getEast().getRow() > 0) {
                    neighborCount++;
                }
                if (cell.getWest() != null && Boolean.TRUE.equals(cell.getNeighbors().get(cell.getWest()))
                        && cell.getWest().getRow() < row - 1) {
                    neighborCount++;
                }

                if (neighborCount == 3) {
                    complexity++;
                }
                if ((row == 0 || row == rows - 1 || column == 0 || column == columns + 1)
                        && cell.getNeighbors().size() == 2) {
                    complexity++;

                }
            }
        }

        return complexity - 2; // removing start and end cells;
    }
}