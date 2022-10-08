/*
 */
package controllers;

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
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import main.IsabelTheGame;
import tile.UtilityTool;

/**
 * FXML Controller class
 *
 * @author avile
 */
public class CreditsController implements Initializable
{

    @FXML
    private ImageView bkg0;
    @FXML
    private ImageView bkg1_5;
    @FXML
    private ImageView bkg2;
    @FXML
    private ImageView bkg3;
    @FXML
    private ImageView bkg4;
    @FXML
    private ImageView bkg5;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        scaleImages();
        animateImages();
    }

    private void animateImages()
    {
        Timeline tmr = new Timeline();
        tmr.getKeyFrames().add(new KeyFrame(Duration.millis(30), (event) ->
        {
            //FAR CLOUDS
            if (bkg1_5.getLayoutX() < (bkg1_5.getFitWidth() / 2) * -1)
            {
                bkg1_5.setLayoutX(0);
            } else
            {
                bkg1_5.setLayoutX(bkg1_5.getLayoutX() - .25);
            }
            //MOUNTAINS
            if (bkg2.getLayoutX() < (bkg2.getFitWidth() / 2) * -1)
            {
                bkg2.setLayoutX(0);
            } else
            {
                bkg2.setLayoutX(bkg2.getLayoutX() - .10);
            }
            // BLUE CLOUDS
            if (bkg3.getLayoutX() < (bkg3.getFitWidth() / 2) * -1)
            {
                bkg3.setLayoutX(0);
            } else
            {
                bkg3.setLayoutX(bkg3.getLayoutX() - .3);
            }
            // FAR NEAR CLOUDS
            if (bkg4.getLayoutX() < (bkg4.getFitWidth() / 2) * -1)
            {
                bkg4.setLayoutX(0);
            } else
            {
                bkg4.setLayoutX(bkg4.getLayoutX() - .5);
            }
            //NEAR CLOUDS
            if (bkg5.getLayoutX() < (bkg5.getFitWidth() / 2) * -1)
            {
                bkg5.setLayoutX(0);
            } else
            {
                bkg5.setLayoutX(bkg5.getLayoutX() - .9);
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
        Image img;

        img = SwingFXUtils.toFXImage(loadImage("/par_credits/par1_5.png", (int) bkg0.getFitWidth(), (int) bkg0.getFitHeight()), null);
        bkg1_5.setImage(img);

        img = SwingFXUtils.toFXImage(loadImage("/par_credits/par2.png", (int) bkg0.getFitWidth(), (int) bkg0.getFitHeight()), null);
        bkg2.setImage(img);

        img = SwingFXUtils.toFXImage(loadImage("/par_credits/par3.png", (int) bkg0.getFitWidth(), (int) bkg0.getFitHeight()), null);
        bkg3.setImage(img);

        img = SwingFXUtils.toFXImage(loadImage("/par_credits/par4.png", (int) bkg0.getFitWidth(), (int) bkg0.getFitHeight()), null);
        bkg4.setImage(img);

        img = SwingFXUtils.toFXImage(loadImage("/par_credits/par5.png", (int) bkg0.getFitWidth(), (int) bkg0.getFitHeight()), null);
        bkg5.setImage(img);
    }

    @FXML
    private void volver(ActionEvent event)
    {
        IsabelTheGame.scene.setRoot(IsabelTheGame.originalRoot);
    }

    @FXML
    private void nothing(MouseEvent event)
    {
        try
        {
            IsabelTheGame.soundHandler.stop("song");
            IsabelTheGame.soundHandler.sleep(200);
            Parent root = FXMLLoader.load(getClass().getResource("/scenes/Nothing.fxml"));
            IsabelTheGame.scene.setRoot(root);
        } catch (IOException ex)
        {
            Logger.getLogger(PresentacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
