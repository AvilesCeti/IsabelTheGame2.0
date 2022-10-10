package Persistencia;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author avile
 */
public class GameSettings {

    public static DoubleProperty musicVolume = new SimpleDoubleProperty(100);
    public static DoubleProperty soundEVolume = new SimpleDoubleProperty(100);
    public static boolean isFullScreen = false;
    public static boolean nothingChecked = false;
    public static boolean hasBeenWatched = false;
    public static boolean hasFirstFalled = false;
}
