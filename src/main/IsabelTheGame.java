/*
 */
package main;

import Game.Sound;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author avile
 */
public class IsabelTheGame extends Application
{

    public static Parent originalRoot;
    public static Stage stage;
    public static Scene scene;
    public static Sound music = new Sound();
    public static Sound soundEffect = new Sound();

    @Override
    public void start(Stage primaryStage)
    {
        stage = primaryStage;
        try
        {
            primaryStage.setResizable(false);
            Parent root = FXMLLoader.load(getClass().getResource("/scenes/Presentacion.fxml"));
            originalRoot = root;
            Scene scn = new Scene(root);
            IsabelTheGame.scene = scn;
            primaryStage.setTitle("IsabelTheGame");
            primaryStage.setScene(scn);
            primaryStage.sizeToScene();
            primaryStage.show();
        } catch (IOException ex)
        {
            System.err.println("WTF? Error en la matrix desde el principio?, Cagamos.");
            ex.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

}
