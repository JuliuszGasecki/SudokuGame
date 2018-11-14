package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import sudoku.*;
import sudoku.DifficultLevel;

import java.io.IOException;
import java.net.URL;
import java.text.ParsePosition;
import java.util.ResourceBundle;

public class BoardController implements Initializable {

    @FXML
    private GridPane Board;
    @FXML
    private TextField fileNameTextField;
    private ResourceBundle bundle;
    private String fileName;

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    private SudokuBoard sudokuBoard;

    private void createBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textfield = new TextField();
                textfield.setMaxWidth(50);
                textfield.setMaxHeight(50);
                textfield.setTextFormatter(new TextFormatter<>(c -> {
                    if (c.getControlNewText().isEmpty()) {
                        return c;
                    }
                    if (c.getControlNewText().matches("[0-9]")) {
                        return c;
                    }
                    return null;
                }));
                try {
                    Bindings.bindBidirectional(textfield.textProperty(),
                             new JavaBeanIntegerPropertyBuilder().bean(sudokuBoard.getField(i, j))
                                    .name("Field").getter("getFieldValue").setter("setFieldValue").build(), new NumberStringConverter());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                textfield.setText(Integer.toString(sudokuBoard.get(i, j)));

                if (Integer.parseInt(textfield.getText()) != 0)
                    textfield.setDisable(true);
                Board.add(textfield, i, j);
            }
        }

    }

    public void saveToSudokuBoard(){
        int i =0;
loop:   for (Node node : Board.getChildren()) {
            if(node instanceof TextField) {
                this.sudokuBoard.set(i, Integer.parseInt(((TextField) node).getText()));
                i++;
            }
            }
    }



    @FXML
    private void saveToFile(){
        saveToSudokuBoard();
        FileSudokuBoardDao fsd = (FileSudokuBoardDao) SudokuBoardDaoFactory.getFileDao(fileName());
        try {
            fsd.write(sudokuBoard);
        } catch (SudokuDaoException e) {
            e.printStackTrace();
        }

    }

    public void setSudokuBoard(SudokuBoard sudokuBoard)
    {
        this.sudokuBoard = sudokuBoard;
        this.createBoard();
    }


    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void endGame() {
        saveToSudokuBoard();
        if (!sudokuBoard.checkBoard())
            loadEndGameScene(false);
        else
            loadEndGameScene(true);
    }

    private void loadEndGameScene(boolean ifWin) {
        String fxmlName;
        if(ifWin)
            fxmlName = "/endGameWin.fxml";
        else
            fxmlName = "/endGame.fxml";

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(fxmlName),bundle);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle(bundle.getString("title"));
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    public void saveToDB() throws DataBaseException {

        saveToSudokuBoard();
        JdbcSudokuBoardDao fsd = (JdbcSudokuBoardDao) SudokuBoardDaoFactory.getJdbcDao(fileName());
        try {
            fsd.write(sudokuBoard);
        } catch (SudokuDaoException e) {
            e.printStackTrace();
        }
    }

    public String fileName(){
        return fileNameTextField.getText();
    }

}
