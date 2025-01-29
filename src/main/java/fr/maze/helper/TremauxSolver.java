package fr.maze.helper;

import fr.maze.model.Cell;

import java.util.HashSet;
import java.util.Set;

public class TremauxSolver {

  public static boolean solve(Cell start, Cell end) {
    Set<Cell> visited = new HashSet<>();
    return solveMaze(start, end, visited);
  }

  private static boolean solveMaze(Cell current, Cell end, Set<Cell> visited) {
    if (current == null || visited.contains(current)) {
      return false;
    }
    if (current == end) {
      return true;
    }

    visited.add(current);

    if (current.getNeighbors().get(current.getNorth()) != null && solveMaze(current.getNorth(), end, visited)) {
      return true;
    }
    if (current.getNeighbors().get(current.getSouth()) != null && solveMaze(current.getSouth(), end, visited)) {
      return true;
    }
    if (current.getNeighbors().get(current.getEast()) != null && solveMaze(current.getEast(), end, visited)) {
      return true;
    }
    if (current.getNeighbors().get(current.getWest()) != null && solveMaze(current.getWest(), end, visited)) {
      return true;
    }

    return false;
  }
}