package ProjectModel;

import java.awt.*;

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