package fr.maze;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.maze.algorithms.KruskalMazeAlgorithm;
import fr.maze.algorithms.OptimizedOriginalMazeAlgorithm;
import fr.maze.algorithms.OriginalMazeAlgorithm;
import fr.maze.helper.ComplexitySolver;
import fr.maze.helper.Metrics;
import fr.maze.helper.TremauxSolver;
import fr.maze.model.Cell;

public class Main {

  public static void main(String[] args) {

    List<Metrics> metrics = new ArrayList<Metrics>();

    int iterations = args.length > 0 ? Integer.parseInt(args[0]) : 10;
    String fileName = args.length > 1 ? args[1] : "metrics.json";

    // Run the different algorithms and collect metrics
    for (int i = 0; i < iterations; i++) {
      metrics.add(testMazeAlgo(new OriginalMazeAlgorithm(5, 5), true));
      metrics.add(testMazeAlgo(new OriginalMazeAlgorithm(10, 10), true));
      metrics.add(testMazeAlgo(new OriginalMazeAlgorithm(50, 50), false));
      metrics.add(testMazeAlgo(new OriginalMazeAlgorithm(100, 100), false));

      metrics.add(testMazeAlgo(new OptimizedOriginalMazeAlgorithm(5, 5), true));
      metrics.add(testMazeAlgo(new OptimizedOriginalMazeAlgorithm(10, 10), true));
      metrics.add(testMazeAlgo(new OptimizedOriginalMazeAlgorithm(50, 50), false));
      metrics.add(testMazeAlgo(new OptimizedOriginalMazeAlgorithm(100, 100), false));

      metrics.add(testMazeAlgo(new KruskalMazeAlgorithm(5, 5), true));
      metrics.add(testMazeAlgo(new KruskalMazeAlgorithm(10, 10), true));
      metrics.add(testMazeAlgo(new KruskalMazeAlgorithm(50, 50), false));
      metrics.add(testMazeAlgo(new KruskalMazeAlgorithm(100, 100), false));
    }

    printResult(metrics, fileName);
  }

  private static Metrics testMazeAlgo(MazeAlgorithm mazeAlgorithm, boolean render) {

    int rows = mazeAlgorithm.getRows();
    int columns = mazeAlgorithm.getColumns();

    mazeAlgorithm.initialize();
    Runtime runtime = Runtime.getRuntime();
    runtime.gc();
    long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

    long startTime = System.currentTimeMillis();

    mazeAlgorithm.compute();

    long endTime = System.currentTimeMillis();
    long executionTime = endTime - startTime;

    long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
    long memoryUsage = memoryAfter - memoryBefore;

    if (render) {
      mazeAlgorithm.render();
    }

    Cell[][] grid = mazeAlgorithm.getGrid();
    boolean solvable = TremauxSolver.solve(grid[0][0], grid[rows - 1][columns - 1]);

    int complexity = ComplexitySolver.complexity(grid);

    Metrics metrics = new Metrics(mazeAlgorithm.getClass().getSimpleName(), executionTime, memoryUsage, complexity,
        solvable, rows, columns);
    System.out.println(metrics);

    return metrics;
  }

  private static void printResult(List<Metrics> metrics, String fileName) {
    ObjectMapper mapper = new ObjectMapper();

    try {

      mapper.writeValue(new File(fileName), metrics);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}