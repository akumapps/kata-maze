package fr.maze.original;

import fr.maze.original.model.Cell;

public abstract class MazeAlgorithm {
    protected Cell[][] grid;
    protected int rows, columns;

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public MazeAlgorithm(int rows, int columns) {
        this.grid = new Cell[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }

    public abstract void initialize();
  
    public abstract void compute();

    public void render() {
        
        // Display the maze
        StringBuffer sb = new StringBuffer();

        sb.append("+");
        for (int colCount = 0; colCount < grid.length; colCount++) {
            sb.append("---+");
        }
        sb.append("\n");

        for (Cell[] row : grid) {
            StringBuffer top = new StringBuffer();
            top.append("|");
            StringBuffer bottom = new StringBuffer();
            bottom.append("+");

            for (Cell cell : row) {
                cell = (cell == null ? new Cell(-1, -1) : cell);

                boolean islinked = (cell.getNeighbors().get(cell.getEast()) != null
                        ? cell.getNeighbors().get(cell.getEast()).booleanValue()
                        : false);

                String eastBoundary = (islinked ? " " : "|");
                top.append("   ").append(eastBoundary);

                islinked = (cell.getNeighbors().get(cell.getSouth()) != null
                        ? cell.getNeighbors().get(cell.getSouth()).booleanValue()
                        : false);

                String southBoundary = (islinked ? "   " : "---");
                bottom.append(southBoundary).append("+");
            }

            sb.append(top).append("\n");
            sb.append(bottom).append("\n");
        }

        System.out.println(sb.toString());
    }
    public Cell[][] getGrid() {
        return grid;
    }

    protected Cell getGridCell(int row, int column, Cell[][] grid, int rows, int columns) {
        Cell resultCell = null;
        if ((row >= 0 && row < rows) &&
                (column >= 0 && (column < columns))) {
            resultCell = grid[row][column];
        }
        return resultCell;
    }
}
