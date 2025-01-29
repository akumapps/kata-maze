
package fr.maze.helper;

import java.io.Serializable;

public class Metrics implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String algorythmName;

    private final long executionTime;

    private final int complexity;

    private final boolean solvable;

    private final long memoryUsage;

    private final long rows;

    private final long columns;

    public Metrics(final String algorythmName, final long executionTime, final long memoryUsage2, final int complexity,
            final boolean solvable, final long rows, final long columns) {
        this.algorythmName = algorythmName;
        this.executionTime = executionTime;
        this.memoryUsage = memoryUsage2;
        this.complexity = complexity;
        this.solvable = solvable;
        this.rows = rows;
        this.columns = columns;
    }

    public String getAlgorythmName() {
        return algorythmName;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public int getComplexity() {
        return complexity;
    }

    public boolean isSolvable() {
        return solvable;
    }

    public long getMemoryUsage() {
        return memoryUsage;
    }

    public long getRows() {
        return rows;
    }

    public long getColumns() {
        return columns;
    }

    @Override
    public String toString() {
        return "Metrics{" +
                "simpleName='" + algorythmName + '\'' +
                ", rows=" + rows +
                ", columns=" + columns +
                ", executionTime=" + executionTime + " ms" +
                ", complexity=" + complexity +
                ", solvable=" + solvable +
                ", memoryUsage=" + (memoryUsage / (1024 * 1024)) + " MB" +
                '}';
    }
}
