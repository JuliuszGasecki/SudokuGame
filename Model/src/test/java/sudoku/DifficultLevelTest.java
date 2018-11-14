package sudoku;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class DifficultLevelTest {

    @Test
    public void Test() {

        SudokuBoard sudokuBoard = new SudokuBoard();
        BacktrackingSudokuSolver ss = new BacktrackingSudokuSolver();
        ss.solve(sudokuBoard, new BacktrackingSudokuSolver.Field(0,0));
        DifficultLevel.EASY.removeFileds(sudokuBoard);
        int[] tmp = new int[81];
        for (int i = 0; i < 9 ; i++) {
            for ( int j =0 ; j < 9 ; j++)

            tmp[i*9 + j] = sudokuBoard.get(i, j);

        }
        Arrays.sort(tmp);
        int tmp2= 0;
        for(int i = 0; i < 81 ; i++) {
            if(tmp[i] == 0)
                tmp2 ++;
        }
        Assert.assertEquals(9, tmp2);
    }

}