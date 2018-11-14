package sudoku;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;

public class FileSudokuBoardDaoTest {

    @Test
    public void Test() throws SudokuDaoException {
        SudokuBoard sb = new SudokuBoard();
        BacktrackingSudokuSolver ss = new BacktrackingSudokuSolver();
        ss.solve(sb, new BacktrackingSudokuSolver.Field(0,0));
        SudokuBoardDaoFactory abdf = new SudokuBoardDaoFactory();
        FileSudokuBoardDao fsd = (FileSudokuBoardDao) abdf.getFileDao("zapis");
        fsd.write(sb);
        SudokuBoard sb2 = fsd.read();
        Assert.assertEquals(sb, sb2);
    }

    @Test (expected = NullPointerException.class)
    public void TestFileNotFound() throws SudokuDaoException {
        SudokuBoard sb = new SudokuBoard();
        BacktrackingSudokuSolver ss = new BacktrackingSudokuSolver();
        ss.solve(sb, new BacktrackingSudokuSolver.Field(0,0));
        SudokuBoardDaoFactory abdf = new SudokuBoardDaoFactory();
        FileSudokuBoardDao fsd = (FileSudokuBoardDao) abdf.getFileDao("aaaaaa");
        SudokuBoard sb2 = fsd.read();
    }


}