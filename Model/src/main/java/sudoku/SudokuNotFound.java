package sudoku;

public class SudokuNotFound extends SudokuDaoException {
    public SudokuNotFound(final String s) {
        super(s);
    }

    public SudokuNotFound(final String s, final Throwable throwable) {
        super(s, throwable);
    }
}
