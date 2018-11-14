package sudoku;

import java.io.Serializable;
import java.util.List;

public class SudokuColumn extends SudokuCheck implements Cloneable, Serializable {

    public SudokuColumn(int x, final List<SudokuField> board) {
        prepareColum(x, board);
    }
    private void prepareColum(int x, final List<SudokuField> board) {
        for (int i = 0; i < 9; i++) {
            val.add(board.get(i + 9 * x).getFieldValue());
        }
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
