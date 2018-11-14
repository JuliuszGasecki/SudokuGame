package sudoku;

import com.google.common.base.*;

import java.io.Serializable;
import java.util.Objects;

public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {

    private int value;

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("value", value)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                this.value
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
        final SudokuField other = (SudokuField) obj;
        return Objects.equals(this.value, other.value);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(final SudokuField o) {
        SudokuField s = o;
        if (this.value == o.value) {
            return 0;
        }
        if (this.value < o.value) {
            return -1;
        } else {
            return 1;
        }

    }
}
