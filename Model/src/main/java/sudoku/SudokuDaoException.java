package sudoku;

public class SudokuDaoException extends Exception{

    public SudokuDaoException(final String s) {
        super(s);
    }

    public SudokuDaoException(final String s, final Throwable throwable) {
        super(s, throwable);
    }
}
