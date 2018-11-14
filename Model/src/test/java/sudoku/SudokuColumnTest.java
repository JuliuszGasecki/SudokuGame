package sudoku;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SudokuColumnTest {

    @Test
    public void SudokuColumnTest() {
        List<SudokuField> board = new ArrayList<SudokuField>();
        for (int i = 0; i < 81; i++)
            board.add(new SudokuField());
        board.get(9).setFieldValue(1);
        board.get(10).setFieldValue(2);
        board.get(11).setFieldValue(3);
        board.get(12).setFieldValue(4);
        board.get(13).setFieldValue(5);
        board.get(14).setFieldValue(6);
        board.get(15).setFieldValue(0);
        board.get(16).setFieldValue(8);
        board.get(17).setFieldValue(9);
        SudokuColumn sc = new SudokuColumn(1, board);
        Assert.assertEquals(true, sc.verify(7));
    }
}