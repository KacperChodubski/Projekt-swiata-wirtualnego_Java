package fajen.Organisms.Plants;

import fajen.Organisms.IToxic;
import fajen.Organisms.Organism;
import fajen.Organisms.Plant;
import fajen.World;

import java.awt.*;

public class WolfBerry extends Plant implements IToxic
{
    private static final Color COLOR = Color.decode("#61068f");
    private static final short STRENGTH = 99;

    public WolfBerry(World world, Point position)
    {
        super(world, COLOR, position);
        this.strength = STRENGTH;
    }


    @Override
    public Organism cloning(Point position)
    {
        return new WolfBerry(this.world, position);
    }
}
