package sudoku;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private String fileName;
    private boolean closeFlag = false;
    private FileInputStream fis = null;
    private FileOutputStream fos = null;
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public FileSudokuBoardDao(final String fileName) {
        this.fileName = fileName + ".txt";
    }

    @Override
    protected void finalize() throws FileNotFound {
        if (!this.closeFlag) {
            try {
                this.fis.close();
                this.ois.close();
                this.fos.close();
                this.oos.close();
            } catch (IOException e) {
                throw new FileNotFound("Unable to close file", e);
            }
        }
    }

    @Override
    public SudokuBoard read() throws SudokuDaoException {
        SudokuBoard sb = null;

        try {
            this.fis = new FileInputStream(new File(this.fileName));
            this.ois = new ObjectInputStream(fis);
            sb = (SudokuBoard) this.ois.readObject();
            this.fis.close();
            this.ois.close();
            this.closeFlag = true;
            log.info("Reading file");
            log.debug("Reading file");
        } catch (IOException e) {
            throw new FileNotFound("File not found", e);
        } catch (ClassNotFoundException e) {
            throw new SudokuNotFound("File doesn't contain sudoku", e);
        } finally {
            this.finalize();
        }
        return sb;
    }

    @Override
    public void write(final SudokuBoard obj) throws SudokuDaoException {

        try {
            this.fos = new FileOutputStream(new File(this.fileName));
            this.oos = new ObjectOutputStream(this.fos);
            this.oos.writeObject(obj);
            this.fos.close();
            this.oos.close();
            this.closeFlag = true;
            log.info("Writing to file");
            log.debug("Writing to file");
        } catch (IOException e) {
            throw new FileNotFound("File not found", e);
        } finally {
            this.finalize();
        }
    }
}
