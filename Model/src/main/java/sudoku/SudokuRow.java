package sudoku;

import java.io.Serializable;
import java.util.List;

public class SudokuRow extends SudokuCheck implements Cloneable, Serializable {

    public SudokuRow(int y, final List<SudokuField> board) {
        prepareRow(y, board);
    }
    private void prepareRow(int y, final List<SudokuField> board) {
        for (int i = 0; i < 9; i++) {
            val.add(board.get(y + 9 * i).getFieldValue());

        }
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
