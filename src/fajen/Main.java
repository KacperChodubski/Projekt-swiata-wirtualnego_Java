package fajen;

import fajen.Organisms.Animals.Bubal;
import fajen.Organisms.Animals.Ship;
import fajen.Organisms.Animals.Wolf;
import fajen.Organisms.Plants.SosnowskiBorcht;
import fajen.Organisms.Plants.WolfBerry;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class Main
{
    public static Dimension MAIN_WINDOW_DIMENSION = new Dimension(800, 800);

    public static void main(String[] args)
    {
        int h = 20;
        int w = 20;
        World world = new World(w,h);
        world.setWorldDimension(MAIN_WINDOW_DIMENSION);
        MainWindow mainWindow = new MainWindow(MAIN_WINDOW_DIMENSION, world);
    }
}