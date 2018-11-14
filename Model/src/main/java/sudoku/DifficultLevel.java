package sudoku;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public enum DifficultLevel {

    EASY(9),
    MEDIUM(18),
    HARD(27);
    int numberOfFields;

    DifficultLevel(int numberOfFields) {
        this.numberOfFields = numberOfFields;

    }

    public int getValue() {
        return numberOfFields;
    }

    public void removeFileds(final SudokuBoard sudokuBoard) {
        Random rand = new Random();
        Set used = new HashSet();
        int n = 0;
        int i = 0;
        int x = 0;
        while (i < numberOfFields) {
            if (x > 8) {
                x = 0;
            }
            n = rand.nextInt(8) + 0;
            if (!used.contains(x * 9 + n)) {
                sudokuBoard.set(x, n, 0);
                used.add(x * 9 + n);
                i++;
                x++;
            }
        }
    }
}
