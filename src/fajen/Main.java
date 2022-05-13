package fajen;

import fajen.Organisms.Ship;
import fajen.Organisms.Wolf;

import java.awt.*;

public class Main
{
    public static Dimension MAIN_WINDOW_DIMENSION = new Dimension(800, 800);

    public static void main(String[] args)
    {
        short h = 20;
        short w = 20;
        World world = new World(w,h);
        short sizeOfTile = (short) (MAIN_WINDOW_DIMENSION.height / world.map.getSizeY());
        world.setSizeOfTile(sizeOfTile);
        Ship ship = new Ship(world, new Point(0, 0));
        Ship ship2 = new Ship(world, new Point(1, 1));
        Wolf wolf = new Wolf(world, new Point(10, 10));
        Wolf wolf2 = new Wolf(world, new Point(11, 10));
        world.addOrganism(ship);
        world.addOrganism(ship2);
        world.addOrganism(wolf);
        world.addOrganism(wolf2);
        MainWindow mainWindow = new MainWindow(MAIN_WINDOW_DIMENSION, world);
    }
}