package sudoku;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SudokuBoxTest {

    @Test
    public void SudokuBoxTest() {
        List<SudokuField> board= new ArrayList<SudokuField>();
        for(int i = 0; i < 81; i++)
            board.add(new SudokuField());
        board.get(0).setFieldValue(1);
        board.get(1).setFieldValue(2);
        board.get(2).setFieldValue(3);
        board.get(9).setFieldValue(4);
        board.get(10).setFieldValue(5);
        board.get(11).setFieldValue(0);
        board.get(18).setFieldValue(7);
        board.get(19).setFieldValue(8);
        board.get(20).setFieldValue(9);
        SudokuBox sb = new SudokuBox(0,0,board);
            Assert.assertEquals(true, sb.verify(6));

    }

    @Test
    public void SudokuBoxTest2() {
        List<SudokuField> board= new ArrayList<SudokuField>();
        for(int i = 0; i < 81; i++)
            board.add(new SudokuField());
        board.get(30).setFieldValue(1);
        board.get(31).setFieldValue(2);
        board.get(32).setFieldValue(3);
        board.get(39).setFieldValue(4);
        board.get(40).setFieldValue(5);
        board.get(41).setFieldValue(0);
        board.get(48).setFieldValue(7);
        board.get(49).setFieldValue(8);
        board.get(50).setFieldValue(9);
        SudokuBox sb = new SudokuBox(4-(4%3),5-(5%3),board);
        Assert.assertEquals(true, sb.verify(6));

    }

}