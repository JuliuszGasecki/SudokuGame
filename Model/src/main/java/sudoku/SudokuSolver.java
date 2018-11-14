package sudoku;

public interface SudokuSolver {
    boolean solve(SudokuBoard sb, BacktrackingSudokuSolver.Field current);
}
