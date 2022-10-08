/*
 */
package main;

import Persistencia.GameSettings;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author avile
 */
public class IsabelTheGame extends Application
{

    public static Parent originalRoot;
    public static Stage stage;
    public static Scene scene;
    public static SoundHandler soundHandler = new SoundHandler();

    @Override
    public void start(Stage primaryStage)
    {
        new GameSettings();
        new SoundLoader();
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
            primaryStage.setOnCloseRequest((WindowEvent event) ->
            {
                soundHandler.close();
                System.exit(0);
            });
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
