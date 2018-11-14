package sudoku;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.*;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable{

    public static final String JDBCDriver = "org.apache.derby.jdbc.EmbeddedDriver";
    public static final String dbName="Sudoku";
    public static final String JDBC_URL = "jdbc:derby:" + dbName +";create=true";
    private static final Logger log = LoggerFactory.getLogger(JdbcSudokuBoardDao.class);
    private Statement s;
    String fileName;
    Connection conn = null;

    public JdbcSudokuBoardDao(final String fileName) throws DataBaseException {
        this.fileName = fileName;
        try {
            Class.forName(JDBCDriver);
            conn = DriverManager.getConnection(JDBC_URL);
            log.info("Database connection!");
        } catch (ClassNotFoundException | SQLException e) {
            throw new DataBaseException("Failed to cennect", e);
        }
    }

    @Override
    public SudokuBoard read() throws SudokuDaoException {
        PreparedStatement preparedStatement;
        SudokuBoard sudokuBoard = new SudokuBoard();
        String fields;
        ResultSet resultSet;
        int[] tab = new int[81];

        try {
            s = conn.createStatement();

                log.info("Reading from table");
                preparedStatement = conn.prepareStatement("SELECT SudokuBoards.name, SudokuBoards.fields from SudokuBoards where SudokuBoards.name=?");
                preparedStatement.setString(1, fileName);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    fields = resultSet.getString(2);
                } else {
                    throw new DataBaseException("File not exists");
                }
                for (int i = 0; i < 81; i++) {
                    tab[i] = (Character.getNumericValue(fields.charAt(i)));
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                sudokuBoard.set(i, j, tab[i * 9 + j]);

            }
        }
        try {
            conn.close();
            log.info("Closed table!");
        } catch (SQLException e) {
            throw new DataBaseException("Failed to close connection", e);
        }
        return sudokuBoard;
    }

    @Override
    public void write(final SudokuBoard obj) throws SudokuDaoException {
        PreparedStatement preparedStatement;
        boolean flag = false;
        try {
            s = conn.createStatement();

        } catch (SQLException e) {
            throw new DataBaseException("Failed to create statement", e);
        }
        try {

            s.execute("CREATE TABLE SudokuBoards(name varchar(20) PRIMARY KEY, fields varchar(81))");
            log.info("Created table!");
        } catch (SQLException e) {


        }
        try {
            s =  conn.createStatement();
            preparedStatement = conn.prepareStatement("UPDATE SudokuBoards SET fields =? WHERE name=?");
            log.info("Saved SudokuBoard!");
            preparedStatement.setString(1, obj.stringOfFields());
            preparedStatement.setString(2, fileName);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            flag = true;
        } catch (SQLException e) {

        }

        try {
            s =  conn.createStatement();
            preparedStatement = conn.prepareStatement("INSERT INTO SudokuBoards values(?, ?)");
            log.info("Saved SudokuBoard!");
            preparedStatement.setString(1, fileName);
            preparedStatement.setString(2, obj.stringOfFields());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            flag = true;
        } catch (SQLException e) {
            //throw new DataBaseException("Failed to execute query", e);
        }
        if (!flag) {
            throw new DataBaseException("Failed to execute query");
        }
        try {
            conn.close();
            log.info("Closed table!");
        } catch (SQLException e) {
            throw new DataBaseException("Failed to close connection", e);
        }
    }

    @Override
    public void close() throws Exception {

    }
}

