/*
 */
package controllers;

import Persistencia.GameSettings;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import main.IsabelTheGame;

/**
 * FXML Controller class
 *
 * @author avile
 */
public class NothingController implements Initializable
{

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        IsabelTheGame.soundHandler.play("nothing");
    }

    @FXML
    private void regresar(MouseEvent event)
    {
        IsabelTheGame.soundHandler.stop("nothing");
        IsabelTheGame.scene.setRoot(IsabelTheGame.originalRoot);
        IsabelTheGame.soundHandler.play("song");
    }

}
