package fr.maze.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import fr.maze.MazeAlgorithm;
import fr.maze.model.Cell;

public class KruskalMazeAlgorithm extends MazeAlgorithm {

    private Map<Integer, Link> links;

    public KruskalMazeAlgorithm(int rows, int columns) {
        super(rows, columns);
    }

    @Override
    public void initialize() {
        this.links = new HashMap<>(columns * rows);
        this.grid = new Cell[rows][columns];

        int i = -1;
        // initialize the maze
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                int id = ++i;
                grid[row][column] = new Cell(row, column, id);
                Link link = new Link();

                // add one cell by link at initialization
                link.cells.add(grid[row][column]);
                this.links.put(id, link);
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
        while (links.size() > 1) {
            ArrayList<Integer> linkToDelete = new ArrayList<>();
            for (Link currentlink : links.values()) {
                // chose a random available neighbor to connect
                // if the two cells are not in the same set of link
                // connect the two cells and merge the two sets
                // if the two cells are in the same set, do nothing
                // delete set of link that have been merged
                // repeat until all cells are in the same set

                Cell currentCell = currentlink.cells.get(new Random().nextInt(currentlink.cells.size()));
                Cell nextCell = getRandomNeighbor(currentCell);

                if (nextCell != null && currentCell.getId() != nextCell.getId()) {

                    currentCell.getNeighbors().put(nextCell, true);
                    nextCell.getNeighbors().put(currentCell, true);

                    int currId = currentCell.getId();
                    int nextCellId = nextCell.getId();
                    Boolean currentLinkIsHavier = currentlink.cells.size() >= links.get(nextCellId).cells.size();

                    if (currentLinkIsHavier) {
                        mergeLink(currId, nextCellId);

                        linkToDelete.add(nextCellId);

                    } else {
                        mergeLink(nextCellId, currId);
                        linkToDelete.add(currId);
                    }
                }

            }

            // remove merged links
            for (Integer id : linkToDelete) {
                links.remove(id);
            }

        }

    }

    private void mergeLink(int bigLink, int smallLink) {
        links.get(bigLink).cells.addAll(links.get(smallLink).cells);
        for (Cell cell : links.get(smallLink).cells) {
            cell.setId(bigLink);
        }
    }

    private Cell getRandomNeighbor(Cell currentCell) {

        Random r = new Random();
        List<Cell> neighbors = new ArrayList<>();
        if (currentCell.getNorth() != null && currentCell.getNeighbors().get(currentCell.getNorth()) == null)
            neighbors.add(currentCell.getNorth());
        if (currentCell.getSouth() != null && currentCell.getNeighbors().get(currentCell.getSouth()) == null)
            neighbors.add(currentCell.getSouth());
        if (currentCell.getEast() != null && currentCell.getNeighbors().get(currentCell.getEast()) == null)
            neighbors.add(currentCell.getEast());
        if (currentCell.getWest() != null && currentCell.getNeighbors().get(currentCell.getWest()) == null)
            neighbors.add(currentCell.getWest());

        if (neighbors.isEmpty()) {
            return null;
        }
        return neighbors.get(r.nextInt(neighbors.size()));
    }

    // Link class to store the cells linked together (neighbors)
    private class Link {

        List<Cell> cells = new ArrayList<Cell>();
    }

}
