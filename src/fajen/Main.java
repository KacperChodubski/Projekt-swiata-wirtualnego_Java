package fajen;

import fajen.Organisms.Ship;

import java.awt.*;

public class Main
{
    public static Dimension MAIN_WINDOW_DIMENSION = new Dimension(800, 800);

    public static void main(String[] args)
    {
        World world = new World();
        short sizeOfTile = (short) (MAIN_WINDOW_DIMENSION.height / world.map.getSizeY());
        world.setSizeOfTile(sizeOfTile);
        Ship ship = new Ship(world, new Point(0, 0));
        Ship ship2 = new Ship(world, new Point(1, 1));
        //Ship ship3 = new Ship(world, new Point(1, 1));
        MainWindow mainWindow = new MainWindow(MAIN_WINDOW_DIMENSION, world);
    }
}