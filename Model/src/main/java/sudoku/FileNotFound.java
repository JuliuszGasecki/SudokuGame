package sudoku;

public class FileNotFound extends SudokuDaoException {


    public FileNotFound(final String s) {
        super(s);
    }

    public FileNotFound(final String s, final Throwable throwable) {
        super(s, throwable);
    }
}
