/*
 */
package main;

import Persistencia.Persistencia;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author avile
 */
public class IsabelTheGame extends Application
{

    public static JFrame frame;
    public static Parent originalRoot;
    public static Stage stage;
    public static Scene scene;
    public static SoundHandler soundHandler = new SoundHandler();
    
    
    private Persistencia p;

    @Override
    public void start(Stage primaryStage)
    {
        new Persistencia();
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
                salir();
            });
            primaryStage.show();
        } catch (IOException ex)
        {
            System.err.println("WTF? Error en la matrix desde el principio?, Cagamos.");
            ex.printStackTrace();
        }
    }

    public static void salir()
    {
        Persistencia.saveData();
        System.exit(0);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

}
