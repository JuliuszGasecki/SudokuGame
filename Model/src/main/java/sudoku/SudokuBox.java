package sudoku;

import java.io.Serializable;
import java.util.List;

public class SudokuBox extends SudokuCheck implements Cloneable, Serializable {

    public SudokuBox(int x, int y, final List<SudokuField> board) {
        prepareBox(x, y, board);
    }
    private void prepareBox(int x, int y, final List<SudokuField> board) {
        for (int i = x; i <  x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                val.add(board.get(i * 9 + j).getFieldValue());
                }
            }
        }
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    }
