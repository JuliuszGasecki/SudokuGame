package sudoku;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import com.google.common.base.*;

public class SudokuBoard implements Serializable, Cloneable {

    private List<SudokuField> board = Arrays.asList(new SudokuField[81]);


    public SudokuBoard() {

        prepareBoard();
    }

    public void prepareBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.set(9 * i + j, new SudokuField());
                this.set(i, j, 0);
            }
        }
        Random rx = new Random();
        for (int k = 1; k <= 9; k++) {
            this.set(rx.nextInt(8), rx.nextInt(8), k);
        }
    }

    public SudokuRow getRow(int y) {
        return new SudokuRow(y, this.board);
    }

    public  SudokuColumn getColumn(int x) {
        return new SudokuColumn(x, this.board);
    }

    public SudokuBox getBox(int x, int y) {
        return new SudokuBox((x - (x % 3)), (y - (y % 3)), this.board);
    }

    boolean checkBoard(int x, int y, int val) {
        return getBox(x, y).verify(val)
                && getRow(y).verify(val)
                && getColumn(x).verify(val);
    }

    public boolean checkBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!(getBox(i, j).verify()
                        || getRow(j).verify()
                        || getColumn(i).verify())) {
                    return false;
                }
            }
        }
        return true;
    }


    public void set(int x, int y, int val) {
        board.get(x * 9 + y).setFieldValue(val);
    }

    public void set(int x, int val) {
        board.get(x).setFieldValue(val);
    }



    public int get(int x, int y) {
        return board.get(x * 9 + y).getFieldValue();
    }

    public SudokuField getField(int x, int y) {
        return board.get(x * 9 + y);
    }

    public String stringOfFields() {
        StringBuilder tmp = new StringBuilder();
        for (SudokuField field :board) {
            tmp.append(field.getFieldValue());
        }
        return tmp.toString();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("board", board)
                .toString();
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(
                this.board
        );
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SudokuBoard other = (SudokuBoard) obj;
        return Objects.equals(this.board, other.board);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
