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
    public static Dimension MAIN_WINDOW_DIMENSION = new Dimension(800, 800);

    public static void main(String[] args)
    {
        short h = 80;
        short w = 80;
        World world = new World(w,h);
        world.setWorldDimension(MAIN_WINDOW_DIMENSION);
        int sizeOfTileY = world.getWorldDimension().height / world.map.getSizeY();
        int sizeOfTileX = world.getWorldDimension().width / world.map.getSizeX();
        world.setSizeOfTile(new Dimension(sizeOfTileX, sizeOfTileY));

        MainWindow mainWindow = new MainWindow(MAIN_WINDOW_DIMENSION, world);
    }
}