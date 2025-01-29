package fr.maze.original.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Cell {
  private final int row, column;
  private Map<Cell, Boolean> neighbors;
  private Cell north, south, east, west;
  private int id;

  public int getId() {
    return id;
  }


  public Cell(int row, int column) {
    this.row = row;
    this.column = column;
    this.neighbors = new HashMap<Cell, Boolean>();
  }

  public Cell(int row, int column, int id) {
    this.row = row;
    this.column = column;
    this.id = id;
    this.neighbors = new HashMap<Cell, Boolean>();
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  public Map<Cell, Boolean> getNeighbors() {
    return neighbors;
  }

  public void setNeighbors(Map<Cell, Boolean> neighbors) {
    this.neighbors = neighbors;
  }

  public Cell getNorth() {
    return north;
  }

  public void setNorth(Cell north) {
    this.north = north;
  }

  public Cell getSouth() {
    return south;
  }

  public void setSouth(Cell south) {
    this.south = south;
  }

  public Cell getEast() {
    return east;
  }

  public void setEast(Cell east) {
    this.east = east;
  }

  public Cell getWest() {
    return west;
  }

  public void setWest(Cell west) {
    this.west = west;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Cell cell = (Cell) o;
    return row == cell.row && column == cell.column;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, column);
  }


  public void setId(int id) {
      this.id = id;
  }
}
