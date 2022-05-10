package fajen;

import fajen.Organisms.Sheep;

import java.awt.*;

public class Main
{
    public static Dimension MAIN_WINDOW_DIMENSION = new Dimension(800, 800);

    public static void main(String[] args)
    {
        World world = new World();
        short sizeOfTile = (short) (MAIN_WINDOW_DIMENSION.height / world.map.getSizeY());
        world.setSizeOfTile(sizeOfTile);
        Sheep sheep = new Sheep(world, new Point(0, 0));
        Sheep sheep2 = new Sheep(world, new Point(1, 1));
        Sheep sheep3 = new Sheep(world, new Point(2, 1));
        MainWindow mainWindow = new MainWindow(MAIN_WINDOW_DIMENSION, world);
    }
}