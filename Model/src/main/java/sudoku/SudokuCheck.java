package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class SudokuCheck {

    protected List<Integer> val = new ArrayList<Integer>();

    protected boolean verify(int value) {
        for (int i = 0; i < 9; i++) {
            if (val.get(i) == value) {
                return false;
            }
        }
        return true;
    }

    protected boolean verify() {
        int[] tmp = new int[9];
        for (int i = 0; i < 9; i++) {
            tmp[i] = val.get(i);
        }
        Arrays.sort(tmp);
        for (int j = 0; j < 9; j++) {
            if (tmp[j] != j + 1) {
                return false;
            }
        }
        return true;

    }

}
