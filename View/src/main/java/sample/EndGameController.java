package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EndGameController {

    @FXML
    private Button Ok;
    @FXML
    void closeWindow(){
        Stage oldStage = (Stage) Ok.getScene().getWindow();
        oldStage.hide();
    }
}
