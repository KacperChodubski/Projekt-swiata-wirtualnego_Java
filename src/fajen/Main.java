package fajen;

import fajen.Organisms.Animals.Bubal;
import fajen.Organisms.Animals.Ship;
import fajen.Organisms.Animals.Wolf;
import fajen.Organisms.Plants.SosnowskiBorcht;
import fajen.Organisms.Plants.WolfBerry;

import java.awt.*;
import java.util.Set;

public class Main
{
    public static Dimension MAIN_WINDOW_DIMENSION = new Dimension(1000, 1000);

    public static void main(String[] args)
    {
        short h = 75;
        short w = 75;
        World world = new World(w,h);
        world.setWorldDimension(MAIN_WINDOW_DIMENSION);
        short sizeOfTile = ( (short) (world.getWorldDimension().height / world.map.getSizeY()));
        Set<Class> cos = world.findAllClassesUsingClassLoader("Organisms.Animals");
        world.setSizeOfTile(sizeOfTile);
        Ship ship = new Ship(world, new Point(0, 0));
        //Ship ship2 = new Ship(world, new Point(1, 1));
        Wolf wolf = new Wolf(world, new Point(10, 10));
        //Wolf wolf2 = new Wolf(world, new Point(11, 10));
        WolfBerry wolfBerry  = new WolfBerry(world, new Point(15,15));
        Bubal bubal = new Bubal(world, new Point(8, 8));
        SosnowskiBorcht sosnowskiBorcht = new SosnowskiBorcht(world, new Point(3, 3));
        world.addOrganism(ship);
        //world.addOrganism(ship2);
        world.addOrganism(wolf);
        //world.addOrganism(wolf2);
        world.addOrganism(wolfBerry);
        world.addOrganism(bubal);
        world.addOrganism(sosnowskiBorcht);

        MainWindow mainWindow = new MainWindow(MAIN_WINDOW_DIMENSION, world);
    }
}