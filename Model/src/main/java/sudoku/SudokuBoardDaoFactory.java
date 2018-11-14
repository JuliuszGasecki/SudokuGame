package sudoku;

public class SudokuBoardDaoFactory {
     public static Dao getFileDao(final String fileName) {
        return new FileSudokuBoardDao(fileName);
    }
    public static Dao getJdbcDao(final String fileName) throws DataBaseException {
        return new JdbcSudokuBoardDao(fileName);
    }

}
