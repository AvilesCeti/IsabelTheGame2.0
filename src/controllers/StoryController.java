/*
 */
package controllers;

import Game.GamePanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;
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
public class StoryController implements Initializable
{

    private Image[] images;
    @FXML
    private ImageView imagen;
    @FXML
    private Label texto;
    private int storyCounter = 1, charIndex = 0;
    private String[] historia;
    private String cadenaA = "";

    private boolean canNext = false;
    @FXML
    private AnchorPane titulo;
    @FXML
    private AnchorPane padre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        scaleImages();
        imagen.setVisible(false);
        texto.setText("");
        historia = new String[8];
        historia[0] = "Te voy a contar una historia";
        historia[1] = "Hace tiempo, dos razas vivian en armonia entre si. (Programadores y Mujeres).";
        historia[2] = "Un dia...\n"
                + "Una guerra se desato entre las dos razas, los programadores decidieron programar a sus propias novias.";
        historia[3] = "Tras una larga batalla, los programadores fueron victoriosos.";
        historia[4] = "Y las mujeres se vieron forzadas a buscar otro tipo de ingenieros...";
        historia[5] = "Muchos años mas tarde...";
        historia[6] = "(Whatsapp Web de Isabel)\n"
                + "{2022}";
        historia[7] = "La leyenda cuenta que las mujeres que llegan a hablar con un programador nunca regresan.";
        iniciarHistoria();
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
        images = new Image[9];
        for (int i = 0; i < 9; i++)
        {
            images[i] = SwingFXUtils.toFXImage(loadImage("/historia/historia_story_" + i + ".png", (int) imagen.getFitWidth(), (int) imagen.getFitHeight()), null);
        }
        imagen.setImage(images[0]);

    }

    private void iniciarJuego()
    {
        IsabelTheGame.stage.hide();
        IsabelTheGame.frame = new JFrame("Isabel The Game");
        IsabelTheGame.frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                IsabelTheGame.salir();
            }

        });
        IsabelTheGame.frame.setResizable(false);
        IsabelTheGame.frame.setUndecorated(true);

        GamePanel gamePanel = new GamePanel();
        IsabelTheGame.frame.add(gamePanel);

        IsabelTheGame.frame.pack();

        IsabelTheGame.frame.setLocationRelativeTo(null);
        IsabelTheGame.frame.setVisible(true);

        gamePanel.setupGame();        //Method that creates and locate things like objects etc.
        gamePanel.startGameThread();      //When the actual game starts.
    }

    private void iniciarHistoria()
    {
        siguienteParte(0, 90, 4);
        IsabelTheGame.scene.addEventHandler(KeyEvent.KEY_PRESSED, (event) ->
        {
            if (event.getCode() == KeyCode.ENTER)
            {
                if (canNext)
                {
                    switch (storyCounter)
                    {
                        case 1:
                            imagen.setVisible(true);
                            imagen.setImage(images[storyCounter - 1]);
                            texto.setTextAlignment(TextAlignment.LEFT);
                            siguienteParte(storyCounter, 90, 3.2);
                            storyCounter++;
                            break;
                        case 2:
                            imagen.setImage(images[storyCounter - 1]);
                            texto.setTextAlignment(TextAlignment.LEFT);
                            siguienteParte(storyCounter, 60, 2.7);
                            storyCounter++;
                            break;
                        case 3:
                            imagen.setImage(images[storyCounter - 1]);
                            siguienteParte(storyCounter, 60, 3.2);
                            storyCounter++;
                            break;
                        case 4:
                            imagen.setImage(images[storyCounter - 1]);
                            siguienteParte(storyCounter, 70, 3.2);
                            storyCounter++;
                            break;
                        case 5:
                            imagen.setVisible(false);
                            texto.setStyle("-fx-font-size:4em;");
                            texto.setText(historia[storyCounter]);
                            storyCounter++;
                            break;
                        case 6:
                            imagen.setVisible(true);
                            imagen.setImage(images[storyCounter - 2]);
                            texto.setStyle("-fx-font-size:4em;");
                            texto.setTextAlignment(TextAlignment.CENTER);
                            texto.setText(historia[storyCounter]);
                            storyCounter++;
                            break;
                        case 7:
                            imagen.setImage(images[storyCounter - 2]);
                            siguienteParte(storyCounter, 60, 3.1);
                            storyCounter++;
                            break;
                        case 8:
                            texto.setText("");
                            imagen.setImage(images[storyCounter - 2]);
                            storyCounter++;
                            break;
                        case 9:
                            imagen.setImage(images[storyCounter - 2]);
                            storyCounter++;
                            break;
                        case 10:
                            imagen.setImage(images[storyCounter - 2]);
                            storyCounter++;
                            break;
                        case 11:
                            IsabelTheGame.soundHandler.stop("flower");
                            IsabelTheGame.soundHandler.play("noise");
                            texto.setVisible(false);
                            imagen.setVisible(false);
                            titulo.setVisible(true);
                            storyCounter++;
                            break;
                        case 12:
                            iniciarJuego();
                            break;
                    }
                }
            }
        });
    }

    private void siguienteParte(int i, int speed, double size)
    {
        canNext = false;
        texto.setStyle("-fx-font-size:" + size + "em;");
        charIndex = 0;
        cadenaA = "";
        texto.setText("");
        Timeline tmr = new Timeline();
        tmr.getKeyFrames().add(new KeyFrame(Duration.millis(speed), (event) ->
        {
            char[] chars = historia[i].toCharArray();
            if (charIndex < chars.length)
            {
                cadenaA = cadenaA.concat(String.valueOf(chars[charIndex]));
                charIndex++;
                texto.setText(cadenaA);
            } else
            {
                tmr.stop();
                canNext = true;
            }
        }));
        tmr.setCycleCount(Timeline.INDEFINITE);
        tmr.play();
    }

}
