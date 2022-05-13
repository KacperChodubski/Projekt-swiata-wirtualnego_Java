package fajen.Organisms.Plants;

import fajen.Organisms.Organism;
import fajen.Organisms.Plant;
import fajen.World;

import java.awt.*;

public class Grass extends Plant
{
    private static final Color COLOR = Color.green;

    public Grass(World world, Point position)
    {
        super(world, COLOR, position);
    }


    @Override
    public Organism cloning(Point position)
    {
        return new Grass(this.world, position);
    }
}
