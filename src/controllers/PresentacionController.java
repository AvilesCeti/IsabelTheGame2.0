/*
 */
package controllers;

import Game.GamePanel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import main.IsabelTheGame;
import tile.UtilityTool;

/**
 * FXML Controller class
 *
 * @author avile
 */
public class PresentacionController implements Initializable
{    
    @FXML
    private ImageView bkg0;
    @FXML
    private ImageView bkg1;
    @FXML
    private ImageView bkg2;
    @FXML
    private ImageView bkg3;
    @FXML
    private ImageView bkg4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        IsabelTheGame.soundHandler.play("song");
        scaleImages();
        animateImages();
    }

    public BufferedImage loadImage(String path, int width, int height)
    {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream(path));
            image = uTool.scaleImage(image, width, height);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return image;
    }

    private void scaleImages()
    {
        Image img = SwingFXUtils.toFXImage(loadImage("/par_principal/par1.png", (int) bkg0.getFitWidth(), (int) bkg0.getFitHeight()), null);
        bkg0.setImage(img);

        img = SwingFXUtils.toFXImage(loadImage("/par_principal/par2.png", (int) bkg0.getFitWidth(), (int) bkg0.getFitHeight()), null);
        bkg1.setImage(img);

        img = SwingFXUtils.toFXImage(loadImage("/par_principal/par3.png", (int) bkg0.getFitWidth(), (int) bkg0.getFitHeight()), null);
        bkg2.setImage(img);

        img = SwingFXUtils.toFXImage(loadImage("/par_principal/par4.png", (int) bkg0.getFitWidth(), (int) bkg0.getFitHeight()), null);
        bkg3.setImage(img);

        img = SwingFXUtils.toFXImage(loadImage("/par_principal/par5.png", (int) bkg0.getFitWidth(), (int) bkg0.getFitHeight()), null);
        bkg4.setImage(img);
    }

    @FXML
    private void continuar(ActionEvent event)
    {
        IsabelTheGame.soundHandler.stop("song");
    }

    @FXML
    private void jugar(ActionEvent event)
    {
        IsabelTheGame.soundHandler.stop("song");
        IsabelTheGame.stage.hide();
        JFrame window = new JFrame("Isabel The Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        GamePanel gamePanel = new GamePanel();

        gamePanel.setupGame();        //Method that creates and locate things like objects etc.
        gamePanel.startGameThread();      //When the actual game starts
        gamePanel.player.isFalling = true;

        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    @FXML
    private void opciones(ActionEvent event)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/scenes/Options.fxml"));
            IsabelTheGame.scene.setRoot(root);
        } catch (IOException ex)
        {
            Logger.getLogger(PresentacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void creditos(ActionEvent event)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/scenes/Credits.fxml"));
            IsabelTheGame.scene.setRoot(root);
        } catch (IOException ex)
        {
            Logger.getLogger(PresentacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void salir(ActionEvent event)
    {
        System.exit(0);
    }

    private void animateImages()
    {
        Timeline tmr = new Timeline();
        tmr.getKeyFrames().add(new KeyFrame(Duration.millis(15), (event) ->
        {
            //MOUNTAINS
            if (bkg2.getLayoutX() < (bkg2.getFitWidth() / 2) * -1)
            {
                bkg2.setLayoutX(0);
            } else
            {
                bkg2.setLayoutX(bkg2.getLayoutX() - .125);
            }
            //FAR TREES
            if (bkg3.getLayoutX() < (bkg3.getFitWidth() / 2) * -1)
            {
                bkg3.setLayoutX(0);
            } else
            {
                bkg3.setLayoutX(bkg3.getLayoutX() - .5);
            }
            //TREES
            if (bkg4.getLayoutX() < (bkg4.getFitWidth() / 2) * -1)
            {
                bkg4.setLayoutX(0);
            } else
            {
                bkg4.setLayoutX(bkg4.getLayoutX() - 1.25);
            }
        }));
        tmr.setCycleCount(Timeline.INDEFINITE);
        tmr.play();
    }

}
