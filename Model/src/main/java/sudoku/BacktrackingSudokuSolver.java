package sudoku;

public class BacktrackingSudokuSolver implements SudokuSolver {

    public static class Field {
        public int X;
        public int Y;

        public Field(int X, int Y) {
            this.X = X;
            this.Y = Y;
        }
    }

    private boolean freeField(final SudokuBoard sb) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sb.get(i, j) == 0) {
                    return true;
                }
            }
        }
        return false;
    }



    private Field getNextField(final SudokuBoard sb, final Field current) {
        if (!freeField(sb)) {
            return null;
        }

        int X = current.X;
        int Y = current.Y;

        Y++;
        if (Y > 8) {
            Y = 0;
            X++;
        }
        if (X > 8) {
            X = 0;
        }
        Field next = new Field(X, Y);
        return next;
    }

    @Override
    public boolean solve(final SudokuBoard sb, final Field current) {
        //sb.drawBoard();
        int tmp = 0;
        if (current == null) {
            return true;
        }
        if (sb.get(current.X, current.Y) != 0) {
            return solve(sb, getNextField(sb, current));
        }
loop:       for (int val = 1; val <= 9; val++, tmp++) {
                if (!sb.checkBoard(current.X, current.Y, val)) {
                    if (val == 9) {
                        return false;
                    } else {
                        continue loop;
                    }
                } else {
                    sb.set(current.X, current.Y, val);
                    if (solve(sb, getNextField(sb, current))) {
                        return true;
                    }
                    sb.set(current.X, current.Y, 0);
                }
        }
        return false;
    }
}
//