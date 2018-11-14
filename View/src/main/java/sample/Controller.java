package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sudoku.*;
import sudoku.DifficultLevel;


import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller {
    public Controller()
    {
        sudokuBoard = new SudokuBoard();
        BacktrackingSudokuSolver ss = new BacktrackingSudokuSolver();
        ss.solve(sudokuBoard, new BacktrackingSudokuSolver.Field(0,0));
    }


    @FXML
    private Button StartGameButton;
    @FXML
    private TextField fileNameTextField;
    private boolean levelCheked = false;
    private boolean fromFile = false;

    private SudokuBoard sudokuBoard;

    public SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }

    public void setSudokuBoard(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    private int choosen = 0;

    @FXML
    public void createBoard(ActionEvent actionEvent) {
        if(levelCheked || fromFile) {

            switch(choosen){
                case 1:
                    DifficultLevel.EASY.removeFileds(sudokuBoard);
                    break;
                case 2:
                    DifficultLevel.MEDIUM.removeFileds(sudokuBoard);
                    break;
                case 3:
                    DifficultLevel.HARD.removeFileds(sudokuBoard);
                    break;
            }

            Stage oldStage = (Stage) StartGameButton.getScene().getWindow();
            oldStage.hide();
            Stage stage = new Stage();
            Parent root = null;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/board.fxml"));
            ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
            loader.setResources(bundle);
            try {
                loader.load();

            } catch (IOException e) {
                e.printStackTrace();
            }
            BoardController bc = loader.getController();
            bc.setSudokuBoard(this.sudokuBoard);
            bc.setBundle(bundle);
            root = loader.getRoot();
            stage.setTitle(bundle.getString("title"));
            stage.setScene(new Scene(root));
            stage.show();
        }



    }

    @FXML
    private void readFile(){
        FileSudokuBoardDao fsd = (FileSudokuBoardDao) SudokuBoardDaoFactory.getFileDao(fileNameTextField.getText());
        try {
            sudokuBoard = fsd.read();
            fromFile = true;
        } catch (SudokuDaoException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void readDB() throws DataBaseException {
        JdbcSudokuBoardDao fsd = (JdbcSudokuBoardDao) SudokuBoardDaoFactory.getJdbcDao(fileNameTextField.getText());
        try {
            sudokuBoard = fsd.read();
            fromFile = true;
        } catch (SudokuDaoException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void setLevelEasy(ActionEvent actionEvent){
        this.choosen = 1;
        levelCheked = true;
    }

    @FXML
    public void setLevelMedium(ActionEvent actionEvent){
        this.choosen = 2;
        levelCheked = true;
    }

    @FXML
    public void setLevelHard(ActionEvent actionEvent){
        this.choosen = 3;
        levelCheked = true;
    }

    @FXML
    public void onAuthorClick()
    {
        ResourceBundle authors = ResourceBundle.getBundle("sample.Credits");
        StringBuilder message = new StringBuilder();


        message.append((String) authors.getObject("Author1"));
        message.append((String) authors.getObject("Author2"));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Authors");
        alert.setContentText(message.toString());
        alert.showAndWait();
    }
    public void initialize(URL location, ResourceBundle resources) {


    }
    @FXML
    public void setEnglishLanguage(ActionEvent actionEvent) {
        Locale.setDefault(new Locale("eng"));
        this.refresh();
    }

    @FXML
    public void setPolishLanguage(ActionEvent actionEvent) {
        Locale.setDefault(new Locale("pl"));
        this.refresh();
    }

    private void refresh(){
        Stage oldStage = (Stage) StartGameButton.getScene().getWindow();
        oldStage.hide();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/sample.fxml"),bundle);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle(bundle.getString("title"));
        stage.setScene(new Scene(root));
        stage.show();
    }

}
