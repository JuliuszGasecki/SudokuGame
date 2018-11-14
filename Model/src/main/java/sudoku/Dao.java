package sudoku;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Dao<T> {
    T read() throws IOException, ClassNotFoundException, SudokuDaoException;
    void write(T obj) throws IOException, SudokuDaoException;
}
