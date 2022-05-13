package fajen.Organisms.Plants;

import fajen.Organisms.IMad;
import fajen.Organisms.Organism;
import fajen.Organisms.Plant;
import fajen.World;

import java.awt.*;

public class Milt extends Plant implements IMad
{
    private static final Color COLOR = Color.yellow;
    private static final int NUMBER_OF_SPREADING = 3;

    public Milt(World world, Point position)
    {
        super(world, COLOR, position);
    }


    @Override
    public Organism cloning(Point position)
    {
        return new Milt(this.world, position);
    }

    @Override
    public int getLevelOfMadness()
    {
        return NUMBER_OF_SPREADING;
    }
}
