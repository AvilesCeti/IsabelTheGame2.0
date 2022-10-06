/*
 */
package controllers;

import Persistencia.GameSettings;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import main.IsabelTheGame;
import tile.UtilityTool;

/**
 * FXML Controller class
 *
 * @author avile
 */
public class OptionsController implements Initializable
{

    @FXML
    private Slider barMusic;
    @FXML
    private Slider barSE;
    @FXML
    private CheckBox fullS;
    @FXML
    private ImageView bkg0;
    @FXML
    private ImageView bkg1;
    @FXML
    private ImageView bkg2;
    @FXML
    private ImageView bkg3;
    @FXML
    private Label labelMusic;
    @FXML
    private Label labelSE;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        barMusic.valueProperty().addListener((observable) ->
        {
            labelMusic.setText("Volumen Musica: " + (int) (barMusic.getValue()) + "%");
            IsabelTheGame.music.setVolume(barMusic.getValue());
            GameSettings.musicVolume = barMusic.getValue();
        });
        barSE.valueProperty().addListener((observable) ->
        {
            labelSE.setText("Efectos Sonido: " + (int) (barSE.getValue()) + "%");
            IsabelTheGame.soundEffect.setVolume(barSE.getValue());
            GameSettings.soundEVolume = barSE.getValue();
        });
        barMusic.setValue(GameSettings.musicVolume);
        labelMusic.setText("Volumen Musica: " + (int) (barMusic.getValue()) + "%");
        barSE.setValue(GameSettings.soundEVolume);
        labelSE.setText("Efectos Sonido: " + (int) (barSE.getValue()) + "%");
        scaleImages();
        animateImages();
    }

    private void animateImages()
    {
        Timeline tmr = new Timeline();
        tmr.getKeyFrames().add(new KeyFrame(Duration.millis(30), (event) ->
        {
            //MOUNTAINS
            if (bkg1.getLayoutX() < (bkg1.getFitWidth() / 2) * -1)
            {
                bkg1.setLayoutX(0);
            } else
            {
                bkg1.setLayoutX(bkg1.getLayoutX() - .25);
            }
            //MOUNTAINS
            if (bkg2.getLayoutX() < (bkg2.getFitWidth() / 2) * -1)
            {
                bkg2.setLayoutX(0);
            } else
            {
                bkg2.setLayoutX(bkg2.getLayoutX() - .25);
            }
            //FAR TREES
            if (bkg3.getLayoutX() < (bkg3.getFitWidth() / 2) * -1)
            {
                bkg3.setLayoutX(0);
            } else
            {
                bkg3.setLayoutX(bkg3.getLayoutX() - 1);
            }
        }));
        tmr.setCycleCount(Timeline.INDEFINITE);
        tmr.play();
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
        Image img = SwingFXUtils.toFXImage(loadImage("/par_options/par1.png", (int) bkg0.getFitWidth(), (int) bkg0.getFitHeight()), null);
        bkg0.setImage(img);

        img = SwingFXUtils.toFXImage(loadImage("/par_options/par2.png", (int) bkg0.getFitWidth(), (int) bkg0.getFitHeight()), null);
        bkg1.setImage(img);

        img = SwingFXUtils.toFXImage(loadImage("/par_options/par3.png", (int) bkg0.getFitWidth(), (int) bkg0.getFitHeight()), null);
        bkg2.setImage(img);

        img = SwingFXUtils.toFXImage(loadImage("/par_options/par4.png", (int) bkg0.getFitWidth(), (int) bkg0.getFitHeight()), null);
        bkg3.setImage(img);
    }

    @FXML
    private void volver(ActionEvent event)
    {
        IsabelTheGame.scene.setRoot(IsabelTheGame.originalRoot);
    }

}
