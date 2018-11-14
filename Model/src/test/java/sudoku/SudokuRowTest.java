package sudoku;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SudokuRowTest {

    @Test
    public void SudokuRowTest() {
        List<SudokuField> board = new ArrayList<SudokuField>();
        for (int i = 0; i < 81; i++)
            board.add(new SudokuField());
        board.get(8).setFieldValue(0);
        board.get(17).setFieldValue(2);
        board.get(26).setFieldValue(3);
        board.get(35).setFieldValue(4);
        board.get(44).setFieldValue(5);
        board.get(53).setFieldValue(6);
        board.get(62).setFieldValue(7);
        board.get(71).setFieldValue(8);
        board.get(80).setFieldValue(9);
        SudokuRow sr = new SudokuRow(8, board);
        Assert.assertEquals(true, sr.verify(1));
    }
}