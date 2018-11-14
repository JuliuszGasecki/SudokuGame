package sudoku;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class JdbcSudokuBoardDaoTest {


    @Test
    public void Test()  {
        SudokuBoard sb = new SudokuBoard();
        BacktrackingSudokuSolver ss = new BacktrackingSudokuSolver();
        ss.solve(sb, new BacktrackingSudokuSolver.Field(0,0));
        JdbcSudokuBoardDao fsd = null;
        JdbcSudokuBoardDao fsd2 = null;
        try {
            fsd = (JdbcSudokuBoardDao) SudokuBoardDaoFactory.getJdbcDao("test");
            fsd2 = (JdbcSudokuBoardDao) SudokuBoardDaoFactory.getJdbcDao("test");
            fsd.write(sb);
            SudokuBoard sb2 = fsd2.read();
            Assert.assertEquals(sb, sb2);
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SudokuDaoException e) {
            e.printStackTrace();
        }


    }

    @Test (expected = DataBaseException.class)
    public void TestFileNotFound() throws SudokuDaoException {
        SudokuBoard sb = new SudokuBoard();
        BacktrackingSudokuSolver ss = new BacktrackingSudokuSolver();
        ss.solve(sb, new BacktrackingSudokuSolver.Field(0,0));
        JdbcSudokuBoardDao fsd = (JdbcSudokuBoardDao) SudokuBoardDaoFactory.getJdbcDao("aaaaaa");
        SudokuBoard sb2 = fsd.read();
    }


}