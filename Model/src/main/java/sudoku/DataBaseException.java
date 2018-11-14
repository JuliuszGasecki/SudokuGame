package sudoku;

public class DataBaseException extends SudokuDaoException {
    public DataBaseException(final String s) {
        super(s);
    }

    public DataBaseException(final String s, final Throwable throwable) {
        super(s, throwable);
    }
}
